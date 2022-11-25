CREATE TABLE IF NOT EXISTS account
(
    id                              SERIAL PRIMARY KEY,
    name                            TEXT NOT NULL,
    lastname                        TEXT NOT NULL,
    email                           TEXT NOT NULL UNIQUE,
    password                        TEXT,
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
    id          SERIAL PRIMARY KEY,
    name        text NOT NULL,
    lastname    text NOT NULL,
    email       text NOT NULL UNIQUE ,
    password    text,
    phone_number       text NOT NULL,
    speciality  text    NOT NULL,
    description text    NOT NULL
);


