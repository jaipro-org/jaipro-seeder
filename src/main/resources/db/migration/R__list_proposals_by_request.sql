CREATE OR REPLACE FUNCTION jaipro.list_proposals_by_request(p_service_request_id uuid, p_page integer, p_page_size integer)
 RETURNS TABLE
                (
                    service_request_id          uuid,
                    service_proposal_id         uuid,
                    specialist_id               uuid,
                    full_name                   text,
                    photo                       text,
                    professions                 text,
                    ratings                     jsonb,
                    proposal                    character varying,
                    creation_date               timestamp without time zone,
                    rows                        integer
                )
 LANGUAGE plpgsql
AS $function$
declare
	v_state_open integer := 1;
	v_state_accepted integer := 4;
begin
    if (v_state_open = (select status from service_request sr where sr.service_request_id = p_service_request_id)) then
    	    return query (select
    							sp.service_request_id,
    							sp.service_proposal_id,
    							sp.specialist_id,
    							sp.prof_name || ' ' || sp.prof_last_name as full_name,
    							sc.profile_photo->>'url' as photo,
    							t.professions,
    							s.ratings,
    							sp.proposal,
    							sp.creation_date,
    							CAST(count(*) over() as int) as rows
    						from service_proposal sp
    						join specialist s on sp.specialist_id = s.specialist_id
    						join specialist_cv sc on sc.specialist_id = s.specialist_id
    						join (select
    								t1.specialist_id,
    								string_agg(distinct t2.name, ', ') as professions
    							  from specialist_specialization t1
    							  join profession t2 on t1.profession_id = t2.profession_id
    							  group by t1.specialist_id) as t on t.specialist_id = s.specialist_id
    						where sp.service_request_id = p_service_request_id
    						and sp.status_proposal = v_state_open
    						order by sp.creation_date desc
    						limit p_page_size offset ((p_page - 1) * p_page_size));
    END IF;

    return query (select
    						sp.service_request_id,
    						sp.service_proposal_id,
    						sp.specialist_id,
    						sp.prof_name || ' ' || sp.prof_last_name as full_name,
    						sc.profile_photo->>'url' as photo,
    						t.professions,
    						s.ratings,
    						sp.proposal,
    						sp.creation_date,
    						CAST(count(*) over() as int) as rows
    					from service_proposal sp
    					join specialist s on sp.specialist_id = s.specialist_id
    					join specialist_cv sc on sc.specialist_id = s.specialist_id
    					join (select
    							t1.specialist_id,
    							string_agg(distinct t2.name, ', ') as professions
    						  from specialist_specialization t1
    						  join profession t2 on t1.profession_id = t2.profession_id
    						  group by t1.specialist_id) as t on t.specialist_id = s.specialist_id
    					where sp.service_request_id = p_service_request_id
    					and sp.status_proposal = v_state_accepted
    					order by sp.creation_date desc
    					limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;
