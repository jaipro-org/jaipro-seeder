CREATE OR REPLACE FUNCTION jaipro.get_list_specialist_back_office(p_fullname text, p_status text, p_last_days integer, p_page integer, p_page_size integer)
 RETURNS TABLE(dni character varying, name character varying, last_name character varying, phone character varying, email character varying, professions text, locations text, create_at text, id_status integer, status text, rows int)
 LANGUAGE plpgsql
AS $function$
begin
	return query (
		select
			s."document" as dni,
			s."name" as nombres,
			s.last_name as apellidos,
			s.phone as telefono,
			s.email as correo,
			string_agg(p."name", ' | ') as profesiones,
			string_agg(d."name", ' | ')  as locaciones_trabajo,
			TO_CHAR(s.creation_date, 'dd-mm-yyyy') as fecha_creacion,
			s.verified_status as id_status,
			(case s.verified_status
				when 1 then 'Pendiente Validación'
				when 2 then 'Aprobado'
				when 3 then 'Observado'
				else ''
			end) as status,
			CAST(count(*) over () as int) as rows
		from specialist s
		inner join work_location wl on s.specialist_id = wl.specialist_id
		inner join district d on wl.district_id = d.district_id
		inner join specialist_specialization ss on s.specialist_id = ss.specialist_id
		inner join profession p on ss.profession_id = p.profession_id
		where (p_fullname is null
			or p_fullname = ''
			or upper(s.name || s.last_name) like '%' || Upper(p_fullname) || '%')
		and (p_status = '' or s.verified_status = any (cast(p_status as int[])))
		and (p_last_days IS NULL
			or s.creation_date > (now()::date::timestamp - (p_last_days || ' DAYS')::interval))
		group by s."document", s."name", s.last_name, s.phone, s.email,
			s.creation_date, s.verified_status, s.verified_status
		order by s.creation_date desc
		limit p_page_size offset ((p_page - 1) * p_page_size)
	);
end
$function$
;
