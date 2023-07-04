CREATE OR REPLACE FUNCTION jaipro.get_specializations_by_specialist(p_specialist_id uuid)
    RETURNS TABLE
            (
                profession_id   integer,
                specializations text
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select ss.profession_id,
                         string_agg(distinct s.name, ', ') as specializations
                  from specialist_specialization ss
                           join specialization s on ss.specialization_id = s.specialization_id
                  where ss.specialist_id = p_specialist_id
                  group by ss.profession_id);
end
$function$
;