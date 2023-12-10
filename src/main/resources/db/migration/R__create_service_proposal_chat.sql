create or replace function jaipro.create_service_proposal_chat(p_service_proposal_id uuid, p_message text,
                                                               p_flag_specialist bool, p_user text)
    returns integer
    language plpgsql
as
$function$
declare
    v_service_request_id uuid;
    v_customer_id        uuid;
    v_specialist_id      uuid;
    v_from_specialist    bool := false;
    v_from_customer      bool := false;
begin

    select sp.service_request_id,
           sr.customer_id,
           sp.specialist_id
    into
        v_service_request_id, v_customer_id, v_specialist_id
    from service_proposal sp
             inner join service_request sr on sr.service_request_id = sp.service_request_id
    where sp.service_proposal_id = p_service_proposal_id;

    if (v_service_request_id is null) then
        return -1;
    end if;

    if (p_flag_specialist = true) then
        v_from_specialist = true;
    else
        v_from_customer = true;
    end if;

    insert into service_proposal_chat(service_proposal_id, customer_id, message, from_specialist, from_customer,
                                      created_by, creation_date, specialist_id)
    values (p_service_proposal_id, v_customer_id, p_message, v_from_specialist, v_from_customer, p_user, now(),
            v_specialist_id);

    return 1;
end
$function$
;