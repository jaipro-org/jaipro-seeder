CREATE OR REPLACE FUNCTION jaipro.work_confirm(p_service_request_id uuid, p_user text)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
declare
	v_state_pending_payment integer := 6;
	v_state_payment_registry integer := 1;
begin

	update jaipro.service_request
		set status = v_state_pending_payment,
			modified_by = p_user,
			modified_date = now()
	where service_request_id = p_service_request_id;

	insert into jaipro.payment (service_request_id, customer_id, profession_id, status, created_by, creation_date)
	select
		service_request_id,
		customer_id,
		profession_id,
		v_state_payment_registry,
		p_user,
		now()
	from jaipro.service_request
	where service_request_id = p_service_request_id;


	return 1;

exception
	when others then
		return -1;
end
$function$
;
