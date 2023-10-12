CREATE OR REPLACE FUNCTION jaipro.get_detail_service_proposal_chat_by_proposal_id(p_service_proposal_id uuid)
 RETURNS TABLE(customer text, customer_photo text, specialist text, specialist_photo text, work_detail character varying)
 LANGUAGE plpgsql
AS $function$
begin
    return query (select
						c."name" || ' ' || c.last_name as customer,
						c.profile_photo->>'url' as customer_photo,
						s."name" || ' ' || s.last_name as specialist,
						sc.profile_photo->>'url' as specialist_photo,
						sr.detail as work_detail
				  from jaipro.service_proposal sp
				  inner join jaipro.customer c on sp.customer_id = c.customer_id
				  inner join jaipro.specialist s on sp.specialist_id = s.specialist_id
				  inner join jaipro.specialist_cv sc on s.specialist_id = sc.specialist_id
				  inner join jaipro.service_request sr on sp.service_request_id = sr.service_request_id
				  where sp.service_proposal_id = p_service_proposal_id
				 );
end
$function$
;