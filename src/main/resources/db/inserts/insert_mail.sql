insert into jaipro.mail (mail_id, subject, body, description, inputs)
values (1, 'Jaipro, recuperación contraseña',
        '<p>Ingresa a este link para que cambies tu contraseña: <a href="__DOMINIO__/auth/actualizar-password?internal=__BASE64__">Cambiar</a> <br> Tomar en consideración que el enlace caducará en 24 horas</p>',
        'Correo para iniciar el proceso de actualizacion de password', '[]') ON CONFLICT DO NOTHING;
insert into jaipro.mail (mail_id, subject, body, description, inputs)
values (2, 'Jaipro, se cambio su contraseña con éxito', '<p>Ir al login: <a href="%s/login">Jaipro</a></p>',
        'Correo de confirmacion de cambio de password exitosa', '[]') ON CONFLICT DO NOTHING;
insert into jaipro.mail (mail_id, subject, body, description, inputs)
values (3, 'Jaipro, verificar correo para finalizar el registro',
        '<h1> Ya falta poco para que puedas enterarte de todo el contenido que tenemos para ti </h1> <h2> Verificar correo: <a href="%s/p/visitante/registro/verificar-correo/%s?sc=%s">Verificar</a></h2>',
        'Correo para verificarion el email nuevo registrado', '[]') ON CONFLICT DO NOTHING;

