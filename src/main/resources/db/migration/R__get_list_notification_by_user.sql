--DROP FUNCTION jaipro.get_list_notification_by_user(integer,integer,uuid,uuid,boolean);
CREATE OR REPLACE FUNCTION jaipro.get_list_notification_by_user(
    p_page integer,
    p_page_size integer,
    p_customer_id uuid default null,
    p_specialist_id uuid default null,
    p_read boolean default null)
    RETURNS TABLE
            (
                notification_id uuid,
                specialist_id   uuid,
                customer_id     uuid,
                title           character varying,
                message         character varying,
                icon            character varying,
                type            integer,
                to_specialist   boolean,
                to_customer     boolean,
                read            boolean,
                creation_date   timestamp,
                modified_date   timestamp,
                rows            integer
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select ntf.notification_id,
                         ntf.specialist_id,
                         ntf.customer_id,
                         ntf.title,
                         ntf.message,
                         ntf.icon,
                         ntf.type,
                         ntf.to_specialist,
                         ntf.to_customer,
                         ntf.read,
                         ntf.creation_date,
                         ntf.modified_date,
                         cast(count(*) over () as int) as rows
                  from notification ntf
                  where (p_customer_id IS NULL OR ntf.customer_id = p_customer_id)
                    AND (p_specialist_id IS NULL OR ntf.specialist_id = p_specialist_id)
                    AND ntf.read = p_read
                  order by ntf.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;