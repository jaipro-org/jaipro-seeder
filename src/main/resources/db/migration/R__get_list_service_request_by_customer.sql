CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_by_customer(p_customer_id uuid, p_status integer, p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id                uuid,
                profession_name   character varying,
                detail            character varying,
                creation_date     timestamp without time zone,
                status            text,
                enabled_rating    boolean,
                rating_done       boolean,
                is_finished       boolean
            )
    LANGUAGE plpgsql
AS
$function$
declare
    state_cancelled integer := 3;
    state_finished integer := 7;
begin
    return query (select sr.service_request_id as id,
                         p."name"              as profession_name,
                         sr.detail,
                         sr.creation_date,
                         case sr.status
                            when 1 then 'Propuestas: ' || sr.proposals_counter
                            when 2 then 'Reservado'
                            when 3 then 'cancelado'
                            when 4 then 'Propuesta Aceptada'
                            when 5 then 'Observado'
                            when 6 then 'Pendiente de Pago'
                            when 7 then 'Finalizado'
                            else ''
                         end as status,
                         sr.enabled_rating,
                         sr.rating_done,
                         (case sr.status when 7 then true else false end) as is_finished
                  from service_request sr
                           join profession p on sr.profession_id = p.profession_id
                  where sr.customer_id = p_customer_id
                  and (
                    (p_status = 1 and sr.status not in (state_cancelled, state_finished)) or
                    (p_status = 2 and sr.status in (state_cancelled, state_finished))
                  )
                  order by sr.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;