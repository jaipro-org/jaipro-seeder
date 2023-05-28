--drop function jaipro.search_specialists(text, text, text, integer, integer);

CREATE OR REPLACE FUNCTION jaipro.search_specialists(p_id_categories text, p_id_specializations text,
                                                     p_id_ubigeums text, p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                specialist_id uuid,
                full_name     text,
                photo         text,
                assessment    text,
                about         character varying,
                professions   text
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (
        select s.specialist_id,
               s."name" || ' ' || s.last_name as full_name,
               sc.profile_photo ->> 'url'     as photo,
               s.ratings ->> 'point'          as assessment,
               sc.about,
               t.professions
        from specialist s
                 join work_location wl on wl.specialist_id = s.specialist_id
                 join specialist_specialization ss on ss.specialist_id = s.specialist_id
                 join specialist_cv sc on sc.specialist_id = s.specialist_id
                 join (
            select t1.specialist_id, string_agg(distinct t2.name, ', ') professions
            from specialist_specialization t1
                     join profession t2 on t1.profession_id = t2.profession_id
            group by t1.specialist_id) as t on t.specialist_id = s.specialist_id
        where s.verified
          and (p_id_ubigeums = '' or wl.district_id = any (cast(p_id_ubigeums as int[])))
          and (p_id_specializations = '' or ss.specialization_id = any (cast(p_id_specializations as int[])))
          and (p_id_categories = '' or ss.profession_id = any (cast(p_id_categories as int[])))
        group by s.specialist_id, s.name, s.last_name, sc.profile_photo, s.ratings, sc.about, t.professions
        order by s.specialist_id
        limit p_page_size offset ((p_page - 1) * p_page_size)
    );
end
$function$
;
