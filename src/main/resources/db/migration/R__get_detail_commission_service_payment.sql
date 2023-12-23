CREATE OR REPLACE FUNCTION jaipro.get_detail_commission_service_payment(p_payment_id uuid)
 RETURNS TABLE(service_request_id uuid, customer text, profession character varying, district character varying, quotation_date text, total_amount integer, payment_id uuid, amount double precision, modality integer, method_id integer, bacnk_id integer)
 LANGUAGE plpgsql
AS $function$
declare
    v_type_payment_service integer := 2;
   	v_proposal_id uuid;
	v_accepted_status_proposal integer := 2;
begin

	select
		sp.service_proposal_id
		into v_proposal_id
	from payment p
	inner join service_proposal sp on p.service_request_id = sp.service_request_id
	where p.payment_id = p_payment_id
	and sp.status_proposal = v_accepted_status_proposal;

	return query (
		select
			sr.service_request_id,
			c."name" || ' ' || c.last_name as customer,
			p."name" as profession,
			d."name" as district,
			to_char(sp.creation_date, 'dd/mm/yyyy') as quotation_date,
			sp.max_cost as total_amount,
			p2.payment_id,
			p2.amount,
			p2.modality,
			p2.method_id,
			p2.bank_id
		from service_proposal sp
		inner join service_request sr on sp.service_request_id = sr.service_request_id
		inner join customer c on sr.customer_id = c.customer_id
		inner join profession p on sr.profession_id = p.profession_id
		inner join district d on sr.district_id = d.district_id
		left join payment p2 on sr.service_request_id = p2.service_request_id and p2."type" = v_type_payment_service
		where sp.service_proposal_id = v_proposal_id
	);
end
$function$
;