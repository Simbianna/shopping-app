alter table users
    add column enabled bool not null default true;

update users
set enabled = true;

alter table users
    add column registration_date timestamp default now();

update users
set registration_date = now();