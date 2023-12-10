CREATE OR REPLACE FUNCTION jaipro.save_specialist_in_service_request(p_service_request_id uuid,
                                                                     p_service_proposal_id uuid, p_specialist_id uuid)
    RETURNS INTEGER
    LANGUAGE plpgsql
AS
$function$
declare
    v_state_accepted integer := 4;
    v_state_canceled integer := 3;
begin

    update jaipro.service_request
    set specialist_id = p_specialist_id,
        status        = v_state_accepted
    where service_request_id = p_service_request_id;

    update jaipro.service_proposal
    set status_proposal = v_state_canceled
    where service_request_id = p_service_request_id
      and service_proposal_id <> p_service_proposal_id;

    update jaipro.service_proposal
    set status_proposal = v_state_accepted
    where service_proposal_id = p_service_proposal_id;

    return 1;

exception
    when others then
        return -1;
end
$function$
;