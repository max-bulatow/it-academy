create table if not exists model_type
(
    id   int2 generated always as identity primary key,
    name varchar(50) unique not null
);

create table if not exists transport_type
(
    id   int2 generated always as identity primary key,
    name varchar(50) unique not null
);

create table if not exists client
(
    id         int4 generated always as identity primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null
);

create table if not exists transport
(
    id                int4 generated always as identity primary key,
    model_type_id     int2 not null,
    transport_type_id int2 not null,
    client_id         int4,

    constraint fk_transport_model_type_id foreign key (model_type_id) references model_type (id),
    constraint fk_transport_transport_type_id foreign key (transport_type_id) references transport_type (id),
    constraint fk_transport_client_id foreign key (client_id) references client (id)
);

insert into client (first_name, last_name) values
('Ivan', 'Ivanov'),
('Petr', 'Petrov'),
('Andrey', 'Andreev'),
('Maksim', 'Maksimov'),
('Anton', 'Antonov'),
('Sergey', 'Sergeev');

insert into model_type (name) values
('Transporter T5'),
('Sprinter 264'),
('Ninja ZX-14'),
('Mazda CX7'),
('BMW M5'),
('Audi Q7');

insert into transport_type (name) values
('AUTOMOBILE'),
('MOTORCYCLE'),
('MINIBUS');