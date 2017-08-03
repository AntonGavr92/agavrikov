CREATE TABLE users_s (id SERIAL PRIMARY KEY,
                    name CHARACTER VARYING (2000),
                    email CHARACTER VARYING (2000),
                    login CHARACTER VARYING (1000),
                    date_created BIGINT);