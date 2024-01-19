CREATE OR REPLACE FUNCTION jaipro.get_detail_payment_honorarium_back_office(p_payment_id integer)
 RETURNS TABLE(amount double precision, specialist text, bank character varying, account_number character varying, cci character varying)
 LANGUAGE plpgsql
AS $function$
begin

	return query(
		select
			p.amount,
			s."name" || ' ' || s.last_name as specialist,
			b.short_name as bank,
			sba.account_number,
			sba.cci
		from payment p
		inner join service_request sr on p.service_request_id = sr.service_request_id
		inner join specialist s on sr.specialist_id = s.specialist_id
		inner join specialist_bank_account sba on s.specialist_id = sba.specialist_id
										and sba.preferred = true
		inner join bank b on sba.bank_id = b.bank_id
		where p.payment_id = p_payment_id
	);

end
$function$
;