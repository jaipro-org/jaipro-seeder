insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1, 'PENDIENTE_1',
        '<p>Ingresa a este link para que cambies tu contraseña: <a href="__DOMINIO__/auth/actualizar-password?internal=__BASE64__">Cambiar</a> <br> Tomar en consideración que el enlace caducará en 24 horas</p>',
        'Correo para iniciar el proceso de actualizacion de password', false, 1, ARRAY [''], 'master', now())
ON CONFLICT DO NOTHING;
insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (2, 'PENDIENTE_2', '<p>Ir al login: <a href="%s/login">Jaipro</a></p>',
        'Correo de confirmacion de cambio de password exitosa', false, 1, ARRAY [''], 'master', now())
ON CONFLICT DO NOTHING;
insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (3, 'PENDIENTE_3',
        '<h1> Ya falta poco para que puedas enterarte de todo el contenido que tenemos para ti </h1> <h2> Verificar correo: <a href="%s/p/visitante/registro/verificar-correo/%s?sc=%s">Verificar</a></h2>',
        'Correo para verificarion el email nuevo registrado', false, 1, ARRAY [''], 'master', now())
ON CONFLICT DO NOTHING;

-- Notification for service request status

insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1001, 'SERVICE_REQUEST_PUBLISHED',
        'Su proyecto fue publicado exitosamente',
        'Cuando el proyecto es publicado en la plataforma', false, 1, ARRAY [''], 'master', now())
ON CONFLICT DO NOTHING;
insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1002, 'SERVICE_REQUEST_PROPOSAL',
        'Recibió una propuesta su proyecto de busqueda de ${PROFESSION_NAME} para el distrito de ${DISTRICT_NAME}',
        'Cuando el proyecto publicado recibe una propuesta', true, 1, ARRAY ['PROFESSION_NAME', 'DISTRICT_NAME'], 'master', now())
ON CONFLICT DO NOTHING;

insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1003, 'PROPOSAL_STATUS',
        'Su propuesta realizada al proyecto de ${PROFESSION_NAME} para el distrito de ${DISTRICT_NAME} fue ${PROPOSAL_STATUS_NAME}.',
        'Cuando la propuesta del especialista es aceptada o rechazada', true, 1,
        ARRAY ['PROFESSION_NAME', 'DISTRICT_NAME', 'PROPOSAL_STATUS_NAME'], 'master', now())
ON CONFLICT DO NOTHING;
insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1004, 'SERVICE_REQUEST_OBSERVED',
        'Su proyecto de ${PROFESSION_NAME} ha estado inactivo durante 7 dias por tanto se le notifica que pasara a revision',
        'Cuando el proyecto despues de tener un estado Aceptado no es promovido al siguiente estado durante los proximos 7 dias',
        true, 1, ARRAY ['PROFESSION_NAME'], 'master', now())
ON CONFLICT DO NOTHING;

insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1005, 'SERVICE_REQUEST_CONFIRM_WORK',
        'El cliente acaba de confirmar que realizo un trabajo de forma exitosa.',
        'Cuando el cliente confirma que el trabajo se realizado correctamente', true, 1,
        ARRAY [''], 'master', now())
ON CONFLICT DO NOTHING;

insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1006, 'SERVICE_REQUEST_PAYMENT_STATUS',
        'El pago por el proyecto de ${PROFESSION_NAME} para el distrito de ${DISTRICT_NAME} ha sido ${PAYMENT_STATUS}.',
        'Cuando el especialista recibe una actualizacion del estado del cobro del servicio (Aprobado/Observado)',
        true, 1, ARRAY ['PROFESSION_NAME', 'DISTRICT_NAME', 'PAYMENT_STATUS_NAME'], 'master', now())
ON CONFLICT DO NOTHING;

insert into jaipro.notification (notification_id, notification_type, content, description, flag_trigger_email, to_profile_type, inputs,
                                 created_by, creation_date)
values (1007, 'SERVICE_REQUEST_CHAT',
        'Tienes un nuevo mensaje de ${USER_FULL_NAME}.',
        'Cuando el cliente/especialista recibe un nuevo mensaje',
        true, 1, ARRAY ['USER_FULL_NAME'], 'master', now())
ON CONFLICT DO NOTHING;
