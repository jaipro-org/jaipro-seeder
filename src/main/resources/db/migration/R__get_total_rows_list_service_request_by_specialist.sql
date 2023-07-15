CREATE OR REPLACE FUNCTION jaipro.get_total_rows_list_service_request_by_specialist(p_specialist_id uuid, p_status integer)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
begin
    return (select count(*)
            from (select sr.service_request_id
                  from specialist_specialization ss
                           join service_request sr on ss.profession_id = sr.profession_id
                           join customer c on sr.customer_id = c.customer_id
                           join profession p on sr.profession_id = p.profession_id
                  where ss.specialist_id = p_specialist_id
                    and (p_status = 0 or sr.status = p_status)
                  group by sr.service_request_id, c."name", c.last_name, p."name", sr.detail, sr.creation_date,
                           sr.status) as t);
end
$function$
;