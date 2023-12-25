CREATE OR REPLACE FUNCTION jaipro.get_detail_service_proposal_by_id(p_id uuid)
    RETURNS TABLE
            (
                id                 uuid,
                service_request_id uuid,
                status_service     integer,
                status_proposal    integer,
                customer           text,
                requirement        character varying,
                location           character varying,
                application_date   timestamp without time zone,
                min_cost           integer,
                max_cost           integer,
                quotation_date     timestamp without time zone
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select sp.service_proposal_id         as id,
                         sr.service_request_id          as service_request_id,
                         sr.status                      as status_service,
                         sp.status_proposal             as status_proposal,
                         c."name" || ' ' || c.last_name as customer,
                         p."name"                       as requirement,
                         d."name"                       as "location",
                         sr.creation_date               as application_date,
                         sp.min_cost,
                         sp.max_cost,
                         sr.creation_date               as quotation_date
                  from service_proposal sp
                           join service_request sr on sp.service_request_id = sr.service_request_id
                           join customer c on sr.customer_id = c.customer_id
                           join profession p on sr.profession_id = p.profession_id
                           join district d on sr.district_id = d.district_id
                  where sp.service_proposal_id = p_id);
end
$function$
;