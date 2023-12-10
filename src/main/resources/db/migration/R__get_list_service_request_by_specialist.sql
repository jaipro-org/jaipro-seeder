CREATE OR REPLACE FUNCTION jaipro.get_list_service_request_by_specialist(p_specialist_id uuid, p_status text,
                                                                         p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id              uuid,
                petitioner      text,
                category        character varying,
                description     character varying,
                quotation_date  timestamp without time zone,
                status_service  integer,
                status_proposal integer,
                status_payment  integer,
                min_cost        integer,
                max_cost        integer,
                method_id       integer,
                modality        integer,
                amount          double precision,
                operation_date  text,
                rows            integer
            )
    LANGUAGE plpgsql
AS
$function$
declare
    v_type_payment_service integer := 1;
begin
    return query (select sp.service_proposal_id                  as id,
                         c."name" || ' ' || c.last_name          as petitioner,
                         p."name"                                as category,
                         sr.detail                               as description,
                         sp.creation_date                        as quotation_date,
                         sr.status                               as status_service,
                         sp.status_proposal                      as status_proposal,
                         p2.status                               as status_payment,
                         sp.min_cost                             as min_cost,
                         sp.max_cost                             as max_cost,
                         p2.method_id                            as method_id,
                         p2.modality                             as modality,
                         p2.amount                               as amount,
                         to_char(p2.creation_date, 'dd/mm/yyyy') as operation_date,
                         CAST(count(*) over () as int)           as rows
                  from service_request sr
                           join service_proposal sp on sr.service_request_id = sp.service_request_id
                           join customer c on sr.customer_id = c.customer_id
                           join profession p on sr.profession_id = p.profession_id
                           left join payment p2 on sr.service_request_id = p2.service_request_id
                      and p2."type" = v_type_payment_service
                  where sp.specialist_id = p_specialist_id
                    and (p_status = '' or sp.status_proposal = any (cast(p_status as int[])))
                  order by sp.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;
