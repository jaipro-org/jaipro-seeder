CREATE OR REPLACE FUNCTION jaipro.get_specialist_public_information(p_specialist_id uuid)
    RETURNS TABLE
            (
                full_name   text,
                photo       text,
                professions text,
                rating      text
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select s."name" || ' ' || s.last_name as full_name,
                         sc.profile_photo ->> 'url'     as photo,
                         t.professions                  as professions,
                         s.ratings ->> 'point'          as rating
                  from specialist s
                           join work_location wl on wl.specialist_id = s.specialist_id
                           join specialist_specialization ss on ss.specialist_id = s.specialist_id
                           join specialist_cv sc on sc.specialist_id = s.specialist_id
                           join (select t1.specialist_id, string_agg(distinct t2.name, ', ') professions
                                 from specialist_specialization t1
                                          join profession t2 on t1.profession_id = t2.profession_id
                                 group by t1.specialist_id) as t on t.specialist_id = s.specialist_id
                  where s.verified
                    and s.specialist_id = p_specialist_id
                  group by s.specialist_id, s.name, s.last_name, sc.profile_photo, s.ratings, sc.about, t.professions);
end
$function$
;