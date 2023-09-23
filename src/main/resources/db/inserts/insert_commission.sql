insert into jaipro.commission (commission_id, commission_type, comission_value, creation_date)
values (1, 1, 5, now()) ON CONFLICT DO NOTHING;