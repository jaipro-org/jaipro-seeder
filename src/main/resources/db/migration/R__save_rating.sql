CREATE OR REPLACE function jaipro.save_rating(p_service_request_id uuid, p_qualification float4, p_comment text, p_user text, p_rating_option_id integer)
	RETURNS integer
 	LANGUAGE plpgsql
AS $function$
declare
	v_enabled_rating boolean;
	v_rating_done boolean;
begin

	select
		enabled_rating, rating_done into v_enabled_rating, v_rating_done
	from service_request sr
	where sr.service_request_id = p_service_request_id;

	if (v_enabled_rating is null) then
		return -1;
	end if;

	if (v_rating_done = true)	then
		return -2;
	end if;

	--if (v_enabled_rating = false) then return -3 end if;

	insert into service_rating(service_request_id, profession_id, qualification, "comment", created_by, creation_date, specialist_id, rating_option_id)
	select
		sr.service_request_id,
		sr.profession_id,
		p_qualification,
		p_comment,
		p_user,
		now(),
		sr.specialist_id,
		p_rating_option_id
	from service_request sr
	where sr.service_request_id = p_service_request_id;

	update service_request
		set rating_done = true,
			modified_by = p_user,
			modified_date = now()
	where service_request_id = p_service_request_id;

	return 1;
end
$function$
;