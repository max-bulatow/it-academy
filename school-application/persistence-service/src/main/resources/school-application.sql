create table student_group
(
	id 					int4 generated always as identity primary key,
	name 				varchar(10) 
);

create table student
(
    id          int4 generated always as identity primary key,
    first_name  varchar(30) not null,
    last_name   varchar(30) not null
);

create table teacher
(
    id          int4 generated always as identity primary key,
    first_name  varchar(30) not null,
    last_name   varchar(30) not null
);

create table subject
(
    id          int4 generated always as identity primary key,
    name        varchar(50) not null,

    constraint uq__subject__name unique (name)
);

create table assessment
(
    id                  int4 generated always as identity primary key,
    assessment          int4 not null
);

