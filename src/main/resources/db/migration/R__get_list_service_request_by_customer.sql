CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_by_customer(p_customer_id uuid, p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id                uuid,
                profession_name   character varying,
                detail            character varying,
                creation_date     timestamp without time zone,
                proposals_counter integer,
                rating            integer,
                enabled_rating    boolean,
                rating_done       boolean
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select sr.service_request_id as id,
                         p."name"              as profession_name,
                         sr.detail,
                         sr.creation_date,
                         sr.proposals_counter,
                         sr."condition"        as rating,
                         sr.enabled_rating,
                         sr.rating_done
                  from service_request sr
                           join profession p on sr.profession_id = p.profession_id
                  where sr.customer_id = p_customer_id
                  order by sr.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;