drop table if exists users_roles cascade;
drop table if exists authorities cascade;
drop table if exists roles cascade;

CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);