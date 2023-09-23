create or replace function jaipro.get_list_payment_by_specialist(p_specialist_id uuid, p_profession_ids text, p_last_days integer, p_district_ids text, p_page integer, p_page_size integer)
	returns table(code character varying, type text, customer text, amount float8, commission float8, broadcast_date text, expiration_date text, status integer, rows integer)
	LANGUAGE plpgsql
as $function$
declare
	v_payment_type_commission integer := 8;
	v_commission_type integer := 1;
	v_commission_value float8;
begin

	select
		c.commission_value into v_commission_value
	from jaipro.commission c
	where c.commission_type = v_commission_type;

	return query(
		select
			p.operation_number as code,
			case p.modality
				when 1 then 'Efectivo'
				when 2 then 'Online'
				else ''
			end as type,
			c."name" || ' ' || c.last_name as customer,
			p.amount,
			case
				when p2.amount is null then (p.amount * (v_commission_value * 0.01))
				else (p2.amount)
			end as commission,
			TO_CHAR(p.creation_date, 'dd/mm/yyyy') as broadcast_date,
			TO_CHAR(p.expiration_date, 'dd/mm/yyyy') as expiration_date,
			p.status as status,
			CAST(count(*) over() as int) as rows
		from payment p
		join customer c on p.customer_id = c.customer_id
		left join payment p2 on p.service_request_id = p2.service_request_id
							and p2."type" = v_payment_type_commission
		join service_request sr on p.service_request_id = sr.service_request_id
		where p."type" not in (v_payment_type_commission)
		and sr.specialist_id = p_specialist_id
		and (p_profession_ids = '' or p.profession_id = any (cast(p_profession_ids as int[])))
		and (p_district_ids = '' or p.profession_id = any (cast(p_district_ids as int[])))
		and (p_last_days IS NULL or
                         p.creation_date > (now()::date::timestamp - (p_last_days||' DAYS')::interval))
		order by p.creation_date desc
		limit p_page_size offset ((p_page - 1) * p_page_size)
	);

end
$function$
;
