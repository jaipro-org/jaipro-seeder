CREATE OR REPLACE FUNCTION jaipro.get_total_rows_list_service_request_by_specialist(p_specialist_id uuid, p_status text)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
begin
    return (select count(*)
            from (select sp.service_proposal_id
                  from service_request sr
                      join service_proposal sp on sr.service_request_id = sp.service_request_id
                      join customer c on sr.customer_id = c.customer_id
                      join profession p on sr.profession_id = p.profession_id
                  where sp.specialist_id = p_specialist_id
                  and (p_status = '' or sp.status_proposal = any (cast(p_status as int[])))
                  ) as t);
end
$function$
;