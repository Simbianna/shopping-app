drop table if exists products cascade ;
drop table if exists users cascade ;
drop table if exists users_roles cascade ;
drop table if exists authorities cascade ;
drop table if exists roles cascade ;

CREATE TABLE products
(
    id    serial,
    title varchar(100),
    price numeric(6, 2)
);

INSERT INTO products (title, price)
VALUES ('Bread', 40),
       ('Milk', 80);

CREATE TABLE users
(
    id       serial,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(80)  NOT NULL,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users(username, password, name, email)
VALUES ('admin', '$2a$10$Ncl/5ufStKs7VszxoOjOw.0uePNJjyy/iOyL9XkTHM59UobY9TOpa', 'Alex', 'alex@alex.com'),
       ('user2', '{bcrypt}123', 'Joey', 'jalex@alex.com'),
       ('user3', '{bcrypt}123', 'Matt', 'malex@alex.com'),
       ('user4', '{bcrypt}123', 'Leo', 'lalex@alex.com'),
       ('user5', '{bcrypt}123', 'Kim', 'kalex@alex.com');

CREATE TABLE roles
(
    id   serial,
    name varchar(50) default NULL,
    PRIMARY KEY (id)
);


INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_USER');

CREATE TABLE users_roles
(
    user_id int not null,
    role_id int not null,

    primary key (user_id, role_id),

    CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
        references users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
        references roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (3, 2),
       (4, 3);