CREATE OR REPLACE FUNCTION jaipro.get_service_request_detail_for_notification(p_service_request_id uuid)
 RETURNS TABLE
            (
                id                  uuid,
                customer_id         uuid,
                customer_name       text,
                customer_photo_url  text,
                district_name       character varying,
                profession_name     character varying,
                specialist_id       uuid,
                detail              character varying,
                creation_date       timestamp without time zone,
                status              integer,
                rating_done         boolean
            )
 LANGUAGE plpgsql
AS $function$
begin
    return query (select
    					sr.service_request_id as id,
                        sr.customer_id as customer_id,
                        c.name || ' ' || c.last_name as customer_name,
                        c.profile_photo ->> 'url' as customer_photo_url,
                        d.name as district_name,
                        p.name as profession_name,
                        sr.specialist_id,
						sr.detail,
						sr.creation_date,
						sr.status,
						sr.rating_done
				  from service_request sr
				  join profession p on sr.profession_id = P.profession_id
                  join customer c on sr.customer_id = c.customer_id
                  join district d on sr.district_id = d.district_id
				  where sr.service_request_id = p_service_request_id);
end
$function$
;
