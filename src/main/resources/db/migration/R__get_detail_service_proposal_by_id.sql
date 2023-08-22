CREATE OR REPLACE FUNCTION jaipro.get_detail_service_proposal_by_id(p_id uuid)
	RETURNS TABLE(
	                id                  uuid,
	                status              integer,
	                customer            text,
	                requirement         character varying,
	                location            character varying,
	                application_date    timestamp without time zone,
	                min_cost            integer,
	                max_cost            integer,
	                quotation_date      timestamp without time zone
	              )
	LANGUAGE plpgsql
AS $function$
begin
	return query (select
						sp.service_proposal_id as id,
						sp.status_proposal as status,
						c."name" || ' ' || c.last_name as customer,
						p."name" as requirement,
						d."name" as "location",
						sr.creation_date as application_date,
						sp.min_cost,
						sp.max_cost,
						sr.creation_date as quotation_date
					from service_proposal sp
					    join customer c on sp.customer_id = c.customer_id
                        join profession p on sp.profession_id = p.profession_id
                        join service_request sr on sp.service_request_id = sr.service_request_id
                        join district d on sr.district_id = d.district_id
					where sp.service_proposal_id = p_id);
end
$function$
;