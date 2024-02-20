CREATE OR REPLACE FUNCTION jaipro.get_list_received_payment_by_specialist(p_specialist_id uuid, p_payment_id integer, p_last_days integer, p_page integer, p_page_size integer)
 RETURNS TABLE(payment_id integer, amount double precision, bank character varying, status integer, creation_date text, rows integer)
 LANGUAGE plpgsql
AS $function$
declare
    v_payment_type_service    integer := 1;
    v_payment_type_honorary   integer := 3;
    v_comission_value         float8;
begin

    return query (select
                         p.payment_id as payment_id,
                         case p.type
                             when v_payment_type_honorary then (select tp.amount
                                                                from payment tp
                                                                where tp.service_request_id = p.service_request_id
                                                                  and tp.type = v_payment_type_service)
                             else v_comission_value
                         end                                  as amount,
                         b.name as bank,
                         p.status                                 as status,
                         TO_CHAR(p.creation_date, 'dd-mm-yyyy')   as creation_date,
                         CAST(count(*) over () as int)            as rows
                  from payment p
				  inner join service_request sr on p.service_request_id = sr.service_request_id
				  left join bank b on p.bank_id = b.bank_id
                  where sr.specialist_id = p_specialist_id
                  and (p_payment_id is null or p.payment_id = p_payment_id)
                  and (p_last_days IS NULL or
				  	p.creation_date > (now()::date::timestamp - (p_last_days || ' DAYS')::interval))
			  	  and (p.type = v_payment_type_honorary)
                  order by p.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));

end
$function$
;
