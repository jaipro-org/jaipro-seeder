CREATE OR REPLACE FUNCTION jaipro.get_list_payment_observations(p_payment_id uuid, p_page integer, p_page_size integer)
	RETURNS TABLE(id integer, observation character varying, creation_date text, rows integer)
	LANGUAGE plpgsql
AS $function$
begin
    return query (
    	select
    		po.payment_observation_id as id,
    		po.observation,
    		TO_CHAR(po.creation_date, 'DD-MM-YYYY HH24:MI') as creation_date,
    		(count(*) over ())::int as rows
    	from payment_observation po
    	where po.payment_id = p_payment_id
    	order by po.creation_date desc
    	limit p_page_size offset ((p_page - 1) * p_page_size)
	);
end
$function$
;