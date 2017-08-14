CREATE TABLE users_task (id SERIAL PRIMARY KEY,
                    name CHARACTER VARYING (100),
                    email CHARACTER VARYING (100),
                    login CHARACTER VARYING (100),
                    password CHARACTER VARYING (100),
                    date_created BIGINT,
                    role_id INTEGER REFERENCES roles_task(id),
                    adress_id INTEGER REFERENCES addresses_task(id));