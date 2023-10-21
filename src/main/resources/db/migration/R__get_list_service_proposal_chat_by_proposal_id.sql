CREATE OR REPLACE FUNCTION jaipro.get_list_service_proposal_chat_by_proposal_id(p_service_proposal_id uuid, p_total_messages_showed integer, p_page_size integer)
 RETURNS TABLE(message character varying, creation_date timestamp without time zone, from_specialist boolean, from_customer boolean, rows integer)
 LANGUAGE plpgsql
AS $function$
begin
    return query (select 
						spc.message,
						spc.creation_date,
						spc.from_specialist,
						spc.from_customer,
						CAST(count(*) over() as int) as rows
				  from service_proposal_chat spc 
				  where spc.service_proposal_id = p_service_proposal_id
                  order by spc.creation_date desc
                  limit p_page_size offset (p_total_messages_showed));
end
$function$
;