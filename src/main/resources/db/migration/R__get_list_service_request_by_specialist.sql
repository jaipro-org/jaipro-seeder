CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_by_specialist(p_specialist_id uuid, p_status text, p_page integer, p_page_size integer)
 RETURNS TABLE(id uuid, petitioner text, category character varying, description character varying, quotation_date timestamp without time zone, status integer, status_payment integer, type_payment integer, amount float8, operation_date text, rows integer)
 LANGUAGE plpgsql
AS $function$
declare
	v_type_payment_commission integer := 8;
begin
   return query (select
   						sp.service_proposal_id as id,
   						c."name" || ' ' || c.last_name as petitioner,
   						p."name"                       as category,
   						sr.detail                      as description,
   						sp.creation_date               as quotation_date,
   						sp.status_proposal 			   as status,
   						p2.status as status_payment,
    					p2."type" as type_payment,
    					p2.amount as amount,
    					to_char(p2.creation_date, 'dd/mm/yyyy') as operation_date,
    					CAST(count(*) over() as int) as rows
   				  from service_request sr
                      join service_proposal sp on sr.service_request_id = sp.service_request_id
                      join customer c on sr.customer_id = c.customer_id
                      join profession p on sr.profession_id = p.profession_id
                      left join payment p2 on sr.service_request_id = p2.service_request_id
                      					and p2."type" <> v_type_payment_commission
   				  where sp.specialist_id = p_specialist_id
   				  and (p_status = '' or sp.status_proposal = any (cast(p_status as int[])))
                  order by sp.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;
