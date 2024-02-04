CREATE OR REPLACE FUNCTION jaipro.get_list_payment_back_office(p_payment_id integer, p_types_ids text,
                                                               p_last_days integer, p_page integer, p_page_size integer)
    RETURNS TABLE
            (
                id             integer,
                operation_code character varying,
                modality       text,
                method         text,
                type           text,
                issued_by      text,
                amount         double precision,
                commission     double precision,
                honorary       double precision,
                creation_date  text,
                status         integer,
                rows           integer
            )
    LANGUAGE plpgsql
AS
$function$
declare
    v_payment_modality_online      integer := 1;
    v_payment_modality_cash        integer := 2;

    v_payment_type_service         integer := 1;
    v_payment_type_commission      integer := 2;
    v_payment_type_honorary        integer := 3;

    v_payment_method_yape          integer := 1;
    v_payment_method_plin          integer := 2;
    v_payment_method_transferencia integer := 3;
    v_payment_method_efectivo      integer := 4;

    v_commission_value             float8;
begin

    return query (select p.payment_id                           as id,
                         p.operation_number                     as operation_code,
                         (case p.modality
                              when v_payment_modality_cash then 'Efectivo'
                              when v_payment_modality_online then 'Online'
                              else ''
                             end)                               as modality,
                         (case p.method_id
                              when v_payment_method_yape then 'Yape'
                              when v_payment_method_plin then 'Plin'
                              when v_payment_method_transferencia then 'Transferencia o depósito'
                              when v_payment_method_efectivo then 'Efectivo'
                              else ''
                             end)                               as method_id,
                         (case p."type"
                              when v_payment_type_service then 'Servicio'
                              when v_payment_type_commission then 'Comisión'
                              when v_payment_type_honorary then 'Honorario'
                              else ''
                             end)                               as type,
                         sp.name || ' ' || sp.last_name         as issued_by,
                         (case p."type"
                              when v_payment_type_service then p.amount
                              else (select tp.amount
                                    from payment tp
                                    where tp.service_request_id = p.service_request_id
                                      and tp.type = v_payment_type_service)
                             end)                               as amount,
                         (case p."type"
                              when v_payment_type_commission then p.amount
                              else v_commission_value
                             end)                               as commission,
                         (case p."type"
                              when v_payment_type_honorary then p.amount
                              else v_commission_value
                             end)                               as honorary,
                         TO_CHAR(p.creation_date, 'dd-mm-yyyy') as creation_date,
                         p.status,
                         CAST(count(*) over () as int)          as rows
                  from payment p
                           inner join service_request sr on p.service_request_id = sr.service_request_id
                           inner join specialist sp on sr.specialist_id = sp.specialist_id
                  where ((p_payment_id = 0 or p_payment_id IS NULL) or p.payment_id = p_payment_id)
                    and (p_types_ids = '' or p.type = any (cast(p_types_ids as int[])))
                    and (p_last_days IS NULL or
                         p.creation_date > (now()::date::timestamp - (p_last_days || ' DAYS')::interval))
                  order by p.creation_date desc
                  limit p_page_size offset ((p_page - 1) * p_page_size));

end
$function$
;
