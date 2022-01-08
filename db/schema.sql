create table accidents
(
    id      serial primary key,
    name    text,
    text    text,
    address text,
    type_id int references types (id)
);

create table types
(
    id   serial primary key,
    name text
);

create table rules
(
    id   serial primary key,
    name text
);

create table accident_rule
(
    id          serial primary key,
    accident_id int references accidents (id),
    rule_id     int references rules (id)
);