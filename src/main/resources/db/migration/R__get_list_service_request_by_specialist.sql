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
   return query (select
   						sp.service_proposal_id as id,
   						c."name" || ' ' || c.last_name as petitioner,
   						p."name"                       as category,
   						sr.detail                      as description,
   						sp.creation_date               as quotation_date,
   						sp.status_proposal as status
   				  from service_request sr
                      join service_proposal sp on sr.service_request_id = sp.service_request_id
                      join customer c on sr.customer_id = c.customer_id
                      join profession p on sr.profession_id = p.profession_id
   				  where sp.specialist_id = p_specialist_id
   				  and (p_status = '' or sp.status_proposal = any (cast(p_status as int[])))
                     order by sp.creation_date desc
                     limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;