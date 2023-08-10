insert into jaipro.rating_option (rating_option_id, name) values (1, 'Hubo mucho retraso en el trabajo') ON CONFLICT DO NOTHING;
insert into jaipro.rating_option (rating_option_id, name) values (2, 'Hubo un cambio imprevisto en la cotización') ON CONFLICT DO NOTHING;
insert into jaipro.rating_option (rating_option_id, name) values (3, 'No hubo un trato tan amable') ON CONFLICT DO NOTHING;
insert into jaipro.rating_option (rating_option_id, name) values (4, 'El trabajo no quedo como se esperaba') ON CONFLICT DO NOTHING;
insert into jaipro.rating_option (rating_option_id, name) values (5, 'Otro') ON CONFLICT DO NOTHING;