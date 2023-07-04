CREATE OR REPLACE FUNCTION jaipro.get_total_rows_by_search_specialists(p_id_categories text, p_id_specializations text, p_id_ubigeums text)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
begin
    return (select count(*)
            from (select s.specialist_id
                  from specialist s
                           join work_location wl on wl.specialist_id = s.specialist_id
                           join specialist_specialization ss on ss.specialist_id = s.specialist_id
                           join specialist_cv sc on sc.specialist_id = s.specialist_id
                  where s.verified
                    and (p_id_ubigeums = '' or wl.district_id = any (cast(p_id_ubigeums as int[])))
                    and (p_id_specializations = '' or ss.specialization_id = any (cast(p_id_specializations as int[])))
                    and (p_id_categories = '' or ss.profession_id = any (cast(p_id_categories as int[])))
                  group by s.specialist_id) as t);
end
$function$
;