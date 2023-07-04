CREATE OR REPLACE FUNCTION jaipro.get_total_rows_list_service_request_by_customer(p_customer_id uuid)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
begin
    return (select count(*)
            from service_request sr
                     join profession p on sr.profession_id = p.profession_id
            where sr.customer_id = p_customer_id);
end
$function$
;