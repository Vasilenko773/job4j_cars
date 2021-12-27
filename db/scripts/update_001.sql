create table cars
(
    id         serial primary key,
    mark       text,
    body_style text
);

create table advertisements
(
    id          serial primary key,
    description text,
    car_id      int references cars (id)
);

create table users
(
    id       serial primary key,
    name     text,
    password text
);

create table users_cars
(
    id       serial primary key,
    users_id int references users (id),
    cars_id  int references cars (id)
);