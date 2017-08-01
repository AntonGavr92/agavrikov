CREATE TABLE comments (id SERIAL PRIMARY KEY,
                    id_item CHARACTER VARYING (2000) references items(id),
                    text CHARACTER VARYING (5000));