INSERT INTO account(id, name, lastname, personal_code, email, password, phone_number, address)
VALUES (1, 'User', 'Good', '5010101010101',
        'doctor.good@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '+37061111111',
        'Vilnius, Lithuania');

INSERT INTO user_role(id, name)
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO user_to_user_role(id, user_id, user_role_id)
VALUES (1, 1, 1),
       (2, 1, 2);

INSERT INTO doctor(id, name, lastname, email, password, personal_code, phone_number, description, user_id)
VALUES (1, 'Dr. Doctor', 'Better',
        'doctor.better@gmail.com', '$2a$10$7UFly6370W0.JIzjy/X4GuyseOaoD48PXQFxyx.yMx9NgWzkreIdu', '5010101010101',
        '+37061111111', 'Doctor description', 1);

INSERT INTO user_to_doctor(id, user_id, doctor_id)
VALUES (1, 1, 1);
