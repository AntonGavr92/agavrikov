CREATE TABLE user_music (user_id INTEGER REFERENCES users_task(id),
                         music_type_id INTEGER REFERENCES music_types_task(id));