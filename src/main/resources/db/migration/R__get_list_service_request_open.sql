CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_open(p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id              uuid,
                customer_name   text,
                profession_name character varying,
                detail          character varying,
                creation_date   timestamp without time zone,
                status          text
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
                             end                          as status
                  from service_request sr
                           join profession p on sr.profession_id = p.profession_id
                           join customer cus on cus.customer_id = sr.customer_id
                  where sr.status = 1 OR sr.status = 2
                  order by sr.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;