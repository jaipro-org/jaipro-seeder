CREATE OR REPLACE FUNCTION jaipro.get_service_request_detail(p_service_request_id uuid)
 RETURNS TABLE
            (
                id                  uuid,
                profession_name     character varying,
                detail              character varying,
                creation_date       timestamp without time zone,
                status              integer,
                gallery             jsonb
            )
 LANGUAGE plpgsql
AS $function$
begin
    return query (select
    					sr.service_request_id as id,
						p."name" as profession_name,
						sr.detail,
						sr.creation_date,
						sr.status,
						sr.gallery
				  from service_request sr
				  join profession p on sr.profession_id = P.profession_id
				  where sr.service_request_id = p_service_request_id);
end
$function$
;
