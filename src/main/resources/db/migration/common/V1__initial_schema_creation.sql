CREATE TABLE IF NOT EXISTS account
(
    id                              SERIAL PRIMARY KEY,
    name                            TEXT NOT NULL,
    lastname                        TEXT NOT NULL,
    email                           TEXT NOT NULL UNIQUE,
    password                        TEXT,
    personal_code                   TEXT NOT NULL,
    phone_number                    TEXT NOT NULL,
    address                         TEXT NOT NULL,
    created_at                      TIMESTAMP DEFAULT NOW(),
    last_login                      TIMESTAMP DEFAULT NOW(),
    pass_change_token               TEXT UNIQUE,
    pass_change_token_creation_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_role
(
    id   SERIAL PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS user_to_user_role
(
    id           SERIAL PRIMARY KEY,
    user_id      int NOT NULL,
    user_role_id int NOT NULL,

    FOREIGN KEY (user_id) REFERENCES account (id),
    FOREIGN KEY (user_role_id) REFERENCES user_role (id)
);

CREATE TABLE IF NOT EXISTS email_template
(
    id      SERIAL PRIMARY KEY,
    name    text NOT NULL,
    body    text NOT NULL,
    subject text NOT NULL
);

CREATE TABLE IF NOT EXISTS email
(
    id        SERIAL PRIMARY KEY,
    user_id   INTEGER,
    subject   text      NOT NULL,
    body      text      NOT NULL,
    date_sent timestamp NOT NULL,
    status    text      NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor
(
    id              SERIAL PRIMARY KEY,
    name            text    NOT NULL,
    lastname        text    NOT NULL,
    email           text    NOT NULL UNIQUE,
    password        text,
    personal_code   text    NOT NULL UNIQUE,
    phone_number    text    NOT NULL,
    description     text    NOT NULL,
    user_id         INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS user_to_doctor
(
    id        SERIAL PRIMARY KEY,
    user_id   int NOT NULL,
    doctor_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES account (id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (id)
);

CREATE TABLE IF NOT EXISTS doctor_specialization
(
    id          SERIAL PRIMARY KEY,
    name        text NOT NULL UNIQUE,
    description text
);

CREATE TABLE IF NOT EXISTS doctor_to_specialization
(
    id                SERIAL PRIMARY KEY,
    doctor_id         INTEGER NOT NULL,
    specialization_id INTEGER NOT NULL,

    FOREIGN KEY (doctor_id) REFERENCES doctor (id),
    FOREIGN KEY (specialization_id) REFERENCES doctor_specialization (id)
);

CREATE TABLE IF NOT EXISTS work_day
(
    id         SERIAL PRIMARY KEY,
    work_date  date    NOT NULL,
    start_hour time    NOT NULL,
    end_hour   time    NOT NULL,
    is_holiday boolean DEFAULT FALSE,
    is_working boolean DEFAULT FALSE,
    doctor_id  INTEGER NOT NULL,
    FOREIGN KEY (doctor_id) references doctor (id)
);

CREATE TABLE IF NOT EXISTS work_schedule_template
(
    id          SERIAL PRIMARY KEY,
    day_of_week text    NOT NULL,
    start_hour  time    NOT NULL,
    end_hour    time    NOT NULL,
    is_working  boolean NOT NULL,
    doctor_id   INTEGER NOT NULL,
    FOREIGN KEY (doctor_id) references doctor (id)
);

CREATE TABLE IF NOT EXISTS appointment
(
    id          SERIAL PRIMARY KEY,
    description text,
    start_time  time    NOT NULL,
    end_time    time    NOT NULL,
    created_at  timestamp DEFAULT NOW(),
    status      text    NOT NULL,
    user_id     INTEGER NOT NULL,
    doctor_id   INTEGER NOT NULL,

    FOREIGN KEY (user_id) references account (id),
    FOREIGN KEY (doctor_id) references doctor (id)
);

CREATE TABLE IF NOT EXISTS specialization
(
    id          SERIAL PRIMARY KEY,
    name        text DEFAULT 'PENDING',
    description text
);

CREATE TABLE IF NOT EXISTS doctor_to_specialization
(
    id                SERIAL PRIMARY KEY,
    doctor_id         INTEGER NOT NULL,
    specialization_id INTEGER NOT NULL,

    FOREIGN KEY (doctor_id) REFERENCES doctor (id),
    FOREIGN KEY (specialization_id) REFERENCES specialization (id)
);





