CREATE OR REPLACE FUNCTION jaipro.get_list_sent_payment_by_specialist(p_specialist_id uuid, p_payment_id integer, p_last_days integer, p_page integer, p_page_size integer)
 RETURNS TABLE(payment_id integer, modality text, type text, customer text, amount double precision, commission double precision, creation_date text, expiration_date text, status integer, rows integer)
 LANGUAGE plpgsql
AS $function$
declare
    v_payment_modality_online integer := 1;
    v_payment_modality_cash   integer := 2;
    v_payment_type_service    integer := 1;
    v_payment_type_commission integer := 2;
    v_payment_type_honorary   integer := 3;
    v_comission_value         float8;
begin

    return query (select
					  	p.payment_id as payment_id,
						case p.modality
					    	when v_payment_modality_cash then 'Efectivo'
					        when v_payment_modality_online then 'Online'
					        else ''
						end as modality,
						case p.type
					    	when v_payment_type_service then 'Servicio'
					        when v_payment_type_commission then 'Comisión'
					        else ''
						end as type,
						c."name" || ' ' || c.last_name as customer,
						case p.type
							when v_payment_type_service then p.amount
					        when v_payment_type_commission then (select tp.amount
					        									 from payment tp
					                                             where tp.service_request_id = p.service_request_id
					                                             and tp.type = v_payment_type_service)
							else v_comission_value
						end as amount,
						case p.type
					    	when v_payment_type_commission then p.amount
					        else v_comission_value
					    end as commission,
					    TO_CHAR(p.creation_date, 'dd-mm-yyyy')   as creation_date,
					    TO_CHAR(p.expiration_date, 'dd-mm-yyyy') as expiration_date,
					    p.status                                 as status,
					    CAST(count(*) over () as int)            as rows
				  from payment p
				  inner join service_request sr on p.service_request_id = sr.service_request_id
				  inner join customer c on sr.customer_id = c.customer_id
				  where sr.specialist_id = p_specialist_id
				  and (p_payment_id is null or p.payment_id = p_payment_id)
				  and (p_last_days IS NULL or
					p.creation_date > (now()::date::timestamp - (p_last_days || ' DAYS')::interval))
				  and (p.type <> v_payment_type_honorary)
				  order by p.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));

end
$function$
;