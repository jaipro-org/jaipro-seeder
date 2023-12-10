--DROP FUNCTION jaipro.get_list_notification_by_user(integer,integer,uuid,boolean);
CREATE OR REPLACE FUNCTION jaipro.get_list_notification_by_user(
    p_page integer,
    p_page_size integer,
    p_user_id uuid default null,
    p_read boolean default null)
    RETURNS TABLE
            (
                id            uuid,
                user_id       uuid,
                content       character varying,
                image_url     character varying,
                link          character varying,
                type          integer,
                read          boolean,
                creation_date timestamp,
                modified_date timestamp,
                rows          integer
            )
    LANGUAGE plpgsql
AS
$function$
begin
    return query (select ntf.user_notification_id      as id,
                         ntf.user_id,
                         ntf.content,
                         ntf.image_url,
                         ntf.link,
                         ntf.type,
                         ntf.read,
                         ntf.creation_date,
                         ntf.modified_date,
                         cast(count(*) over () as int) as rows
                  from user_notification ntf
                  where ntf.user_id = p_user_id
                    AND ntf.read = p_read
                  order by ntf.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));
end
$function$
;