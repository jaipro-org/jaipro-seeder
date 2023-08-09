CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_by_specialist(p_specialist_id uuid, p_status text,
                                                                         p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id             uuid,
                petitioner     text,
                category       character varying,
                description    character varying,
                quotation_date timestamp without time zone,
                status         integer
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select sr.service_request_id          as id,
                         c."name" || ' ' || c.last_name as petitioner,
                         p."name"                       as category,
                         sr.detail                      as description,
                         sr.creation_date               as quotation_date,
                         sr.status
                  from specialist_specialization ss
                           join service_request sr on ss.profession_id = sr.profession_id
                           join customer c on sr.customer_id = c.customer_id
                           join profession p on sr.profession_id = p.profession_id
                  where ss.specialist_id = p_specialist_id
                    and (p_status = '' or sr.status = any (cast(p_status as int[])))
                  group by sr.service_request_id, c."name", c.last_name, p."name", sr.detail, sr.creation_date,
                           sr.status
                  order by sr.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;