INSERT INTO jaipro.service_type (service_type_id, name, description, enabled) VALUES (1, 'Reparacion', 'Servicio por default', true) ON CONFLICT DO NOTHING;