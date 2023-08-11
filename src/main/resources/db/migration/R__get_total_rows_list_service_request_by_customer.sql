CREATE OR REPLACE FUNCTION jaipro.get_total_rows_list_service_request_by_customer(p_customer_id uuid, p_status integer)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
declare
    state_cancelled integer := 3;
    state_finished integer := 7;
begin
    return (select count(*)
            from service_request sr
                     join profession p on sr.profession_id = p.profession_id
            where sr.customer_id = p_customer_id
            and (
                (p_status = 1 and sr.status not in (state_cancelled, state_finished)) or
                (p_status = 2 and sr.status in (state_cancelled, state_finished))
            ));
end
$function$
;