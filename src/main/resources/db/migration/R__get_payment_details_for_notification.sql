CREATE OR REPLACE FUNCTION jaipro.get_payment_details_for_notification(p_payment_id integer)
    RETURNS TABLE
            (
                modality      integer,
                payment_type  integer,
                status        integer,
                method_id     integer,
                customer_id   uuid,
                specialist_id uuid,
                profession    character varying,
                district      character varying
            )
    LANGUAGE plpgsql
AS
$function$
BEGIN
    RETURN QUERY (SELECT p.modality,
                         p.type,
                         p.status,
                         p.method_id,
                         sr.customer_id,
                         sr.specialist_id,
                         pf.name,
                         dt.name
                  from payment p
                           inner join service_request sr on p.service_request_id = sr.service_request_id
                           inner join profession pf on sr.profession_id = pf.profession_id
                           inner join district dt on sr.district_id = dt.district_id
                  where payment_id = p_payment_id);
END;
$function$