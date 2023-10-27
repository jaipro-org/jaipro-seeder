CREATE OR REPLACE FUNCTION jaipro.get_list_service_proposal_chat_by_proposal_id(p_service_proposal_id uuid, p_message_id uuid, p_page_size integer)
 RETURNS TABLE(id uuid, message character varying, creation_date timestamp without time zone, from_specialist boolean, from_customer boolean, rows integer)
 LANGUAGE plpgsql
AS $function$
declare
	v_creation_date timestamp;
	v_total_rows integer;
begin

	if(p_message_id is null) then
		return query (
			select
				spc.service_proposal_chat_id as id,
				spc.message,
				spc.creation_date,
				spc.from_specialist,
				spc.from_customer,
				CAST(count(*) over() as int) as rows
			from service_proposal_chat spc
			where spc.service_proposal_id = p_service_proposal_id
			order by spc.creation_date desc
            limit p_page_size
		);
	end if;

	select
		temp1.creation_date, temp1.rows into
		v_creation_date, v_total_rows
	from (
		select
			spc.service_proposal_chat_id,
			spc.creation_date,
			CAST(count(*) over() as int) as rows
		from service_proposal_chat spc
		where spc.service_proposal_id = p_service_proposal_id
	) as temp1
	where temp1.service_proposal_chat_id = p_message_id;

    return query (select
    					spc.service_proposal_chat_id as id,
						spc.message,
						spc.creation_date,
						spc.from_specialist,
						spc.from_customer,
						v_total_rows as rows
				  from service_proposal_chat spc
				  where spc.service_proposal_id = p_service_proposal_id
				  and spc.creation_date < v_creation_date
                  order by spc.creation_date desc
                  limit p_page_size);
end
$function$
;
