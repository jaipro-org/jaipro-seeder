CREATE OR REPLACE FUNCTION jaipro.create_default_proposal(p_service_request_id uuid, p_specialist_id uuid, p_user text)
    RETURNS integer
    LANGUAGE plpgsql
AS
$function$
declare
    v_state_accepted integer := 2;
    v_prof_name      varchar;
    v_prof_last_name varchar;
begin

    select s."name",
           s.last_name
    into v_prof_name, v_prof_last_name
    from jaipro.specialist s
    where s.specialist_id = p_specialist_id;

    insert into jaipro.service_proposal(service_request_id, status_proposal, proposal, prof_name,
                                        prof_last_name, prof_public_url, min_cost, max_cost, created_by, creation_date,
                                        specialist_id)
    select p_service_request_id,
           v_state_accepted,
           '',
           v_prof_name,
           v_prof_last_name,
           '',
           0,
           0,
           p_user,
           now(),
           p_specialist_id
    from jaipro.service_request sr
    where sr.service_request_id = p_service_request_id;

    return 1;
end
$function$
;
