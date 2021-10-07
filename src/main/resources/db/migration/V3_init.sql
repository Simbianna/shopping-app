alter table users
    add column enabled bool not null default true;

update users
set enabled = true;

alter table users
    add column registration_date timestamp default now();

update users
set registration_date = now();

create unique index products_id_uindex
    on products (id);

create unique index users_id_uindex
    on users (id);

alter table products
    add views bigint default 0;

update products
set views = 0;