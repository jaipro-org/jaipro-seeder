CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_open(p_professions text, p_id_districts text,
                                                                p_specialist_id uuid,
                                                                p_last_days integer, p_page integer,
                                                                p_page_size integer)
    RETURNS TABLE
            (
                id              uuid,
                customer_name   text,
                profession_name character varying,
                detail          character varying,
                creation_date   timestamp without time zone,
                status          text,
                rows            integer
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select sr.service_request_id            as id,
                         cus.name || ' ' || cus.last_name as customer_name,
                         p.name                           as profession_name,
                         sr.detail,
                         sr.creation_date,
                         case sr.status
                             when 1 then 'Abierto'
                             when 2 then 'Reservado'
                             else ''
                             end                          as status,
                         (count(*) over ())::int          as rows
                  from service_request sr
                           join profession p on sr.profession_id = p.profession_id
                           join customer cus on cus.customer_id = sr.customer_id
                  where (sr.status = 1
                      OR sr.status = 2)
                    and (p_professions = '' or p.profession_id = any (cast(p_professions as int[])))
                    and (p_id_districts = '' or sr.district_id = any (cast(p_id_districts as int[])))
                    and (p_last_days IS NULL or
                         sr.creation_date > (now()::date::timestamp - (p_last_days||' DAYS')::interval))
                    and (specialist_id is null or specialist_id = p_specialist_id)
                  order by sr.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;