INSERT INTO account(id, name, lastname, personal_code, email, password, phone_number, address)
VALUES (1, 'Admin', 'Adminauskas', '5010101010101',
        'admin.hospital.example@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu',
        '+37061111111',
        'Vilnius, Lithuania'),
       (2, 'Patient', 'Pacientauskas', '5020202020202',
        'patient.example@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '+37060000000',
        'Vilnius, Lithuania'),
       (3, 'Doctor', 'Daktarauskas', '5030303030303',
        'doctor.tip.hospital@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '+37062222222',
        'Vilnius, Lithuania'),
       (4, 'Test', 'Testauskas', '5040404040404',
        'test.example@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '+37063333333',
        'Vilnius, Lithuania');

INSERT INTO user_role(id, name)
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO user_to_user_role(id, user_id, user_role_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 1),
       (4, 3, 1),
       (5, 3, 2),
       (6, 4, 1);


INSERT INTO doctor(id, name, lastname, email, password, personal_code, phone_number, description, user_id)
VALUES (1, 'Dr. Doctor', 'Psychikos nestabilumu kurejas',
        'doctor.better@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '5010101010101',
        '+37061111111', 'Doctor description', 1);

INSERT INTO user_to_doctor(id, user_id, doctor_id)
VALUES (1, 1, 1);

INSERT INTO doctor_specialization(id, name, description)
VALUES (1, 'CARDIOLOGIST', 'Cardiologist description'),
       (2, 'DENTIST', 'Dentist description'),
       (3, 'DERMATOLOGIST', 'Dermatologist description'),
       (4, 'ENDOCRINOLOGIST', 'Endocrinologist description'),
       (5, 'GASTROENTEROLOGIST', 'Gastroenterologist description'),
       (6, 'GYNECOLOGIST', 'Gynecologist description'),
       (7, 'NEUROLOGIST', 'Neurologist description'),
       (8, 'OPHTHALMOLOGIST', 'Ophthalmologist description'),
       (9, 'OTOLARYNGOLOGIST', 'Otolaryngologist description'),
       (10, 'ORTHOPEDIST', 'Orthopedist description'),
       (11, 'PEDIATRICIAN', 'Pediatrician description'),
       (12, 'PSYCHIATRIST', 'Psychiatrist description'),
       (13, 'RADIOLOGIST', 'Radiologist description'),
       (14, 'SURGEON', 'Surgeon description'),
       (15, 'UROLOGIST', 'Urologist description'),
       (16, 'VENEREOLOGIST', 'Venereologist description'),
       (17, 'OTHER', 'Other description');

INSERT INTO doctor_to_specialization(id, doctor_id, specialization_id)
VALUES (1, 1, 12);


INSERT INTO work_schedule_template(id, day_of_week, start_hour, end_hour, is_day_off, doctor_id)
VALUES (1, 'MONDAY', '9:00', '17:00', false, 1),
       (2, 'TUESDAY', '9:00', '17:00', false, 1),
       (3, 'WEDNESDAY', '9:00', '17:00', false, 1),
       (4, 'THURSDAY', '9:00', '17:00', false, 1),
       (5, 'FRIDAY', '9:00', '17:00', false, 1),
       (6, 'SATURDAY', '9:00', '17:00', true, 1),
       (7, 'SUNDAY', '9:00', '17:00', true, 1);
