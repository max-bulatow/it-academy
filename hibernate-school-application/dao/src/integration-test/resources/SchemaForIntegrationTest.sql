create table if not exists address
(
	id              int4 generated always as identity primary key,
	city            varchar(30) not null,
	street          varchar(50) not null,
	building_number varchar(10) not null
);

insert into address (city, street, building_number) values
('Brest', 'Dzerjinskogo', '50'),
('Brest', 'Masherova', '30');


create table if not exists school
(
	id              int4 generated always as identity primary key,
	name            varchar(250) not null,
	address_id      int4 not null,

	constraint fk__school__address__id foreign key (address_id) references address (id)
);

insert into school (name, address_id) values
('IT-Academy', 1),
('IT-Academy Teen', 1);

create table if not exists subject
(
	id              int4 generated always as identity primary key,
	name            varchar(50) not null,

	constraint uq__subject__name unique (name)
);

insert into subject (name) values
('Java core');

create table if not exists school_subject
(
    id              int4 generated always as identity primary key,
    school_id       int4 not null,
    subject_id      int4 not null,

    constraint fk__school_subject__school__id foreign key (school_id) references school (id),
    constraint fk__school_subject__subject__id foreign key (subject_id) references subject (id)
);

create table if not exists teacher
(
	id              int4 generated always as identity primary key,
	first_name      varchar(30) not null,
	last_name       varchar(30) not null
);

create table if not exists school_teacher
(
    id              int4 generated always as identity primary key,
    school_id       int4 not null,
    teacher_id      int4 not null,

    constraint fk__school_teacher__school__id foreign key (school_id) references school (id),
    constraint fk__school_teacher__teacher__id foreign key (teacher_id) references teacher (id)
);

create table if not exists parent
(
	id              int4 generated always as identity primary key,
	first_name      varchar(30) not null,
	last_name       varchar(30) not null
);

create table if not exists student
(
	id              int4 generated always as identity primary key,
	first_name      varchar(30) not null,
	last_name       varchar(30) not null
);

create table if not exists school_student
(
    id              int4 generated always as identity primary key,
    school_id       int4 not null,
    student_id      int4 not null,

    constraint fk__school_student__school__id foreign key (school_id) references school (id),
    constraint fk__school_student__student__id foreign key (student_id) references student (id),
    constraint uq__school_student__student_id__school_id unique (student_id, school_id)
);

create table if not exists student_parent
(
    id              int4 generated always as identity primary key,
    student_id      int4 not null,
    parent_id       int4 not null,

    constraint fk__student_parent__student__id foreign key (student_id) references student (id),
    constraint fk__student_parent__parent__id foreign key (parent_id) references parent (id)
);

create table if not exists student_group
(
	id              int4 generated always as identity primary key,
	name            varchar(30) not null,
	school_id       int4 not null,
	group_owner_id  int4 not null,

	constraint fk__student_group__school__id foreign key (school_id) references school (id),
	constraint fk__student_group__teacher__id foreign key (group_owner_id) references teacher (id)
);

create table if not exists student_group_student
(
    id                  int4 generated always as identity primary key,
    student_group_id    int4 not null,
    student_id          int4 not null,

    constraint fk__student_group_student__student_group__id foreign key (student_group_id) references student_group (id),
    constraint fk__student_group_student__student_id foreign key (student_id) references student (id),
    constraint uq__student_group_student__student_group_id__student__id unique (student_group_id, student_id)
);

create table if not exists student_group_subject
(
	id 					int4 generated always as identity primary key,
	student_group_id	int4 not null,
	subject_id			int4 not null,

	constraint fk__student_group_subject__student_group__id foreign key (student_group_id) references student_group (id),
	constraint fk__student_group_subject__subject__id foreign key (subject_id) references subject (id)
);

create table if not exists student_group_teacher
(
	id 					int4 generated always as identity primary key,
	student_group_id	int4 not null,
	teacher_id			int4 not null,

	constraint fk__student_group_teacher__student_group__id foreign key (student_group_id) references student_group (id),
	constraint fk__student_group_teacher__teacher__id foreign key (teacher_id) references teacher (id)
);

create table if not exists schedule
(
	id                  int4 generated always as identity primary key,
	start_date          timestamp with time zone not null,
	end_date            timestamp with time zone not null,
	school_id           int4 not null,

	constraint fk__schedule__school__id foreign key (school_id) references school (id)
);

create table if not exists group_room
(
	id                  int4 generated always as identity primary key,
	student_group_id    int4 not null,
	room_owner_id       int4 not null,
	school_id           int4 not null,

	constraint fk__group_room__student_group__id foreign key (student_group_id) references student_group (id),
	constraint fk__group_room__teacher__id foreign key (room_owner_id) references teacher (id),
	constraint fk__group_room__school__id foreign key (school_id) references school (id)
);

create table if not exists lesson
(
	id                  int4 generated always as identity primary key,
	teacher_id          int4 not null,
	student_group_id    int4 not null,
	subject_id          int4 not null,
	group_room_id       int4 not null,
	schedule_id         int4 not null,

	constraint fk__lesson__teacher__id foreign key (teacher_id) references teacher (id),
	constraint fk__lesson__student_group__id foreign key (student_group_id) references student_group (id),
	constraint fk__lesson__subject__id foreign key (subject_id) references subject (id),
	constraint fk__lesson__group_room__id foreign key (group_room_id) references group_room (id),
	constraint fk__lesson__schedule__id foreign key (schedule_id) references schedule (id)
);

create table if not exists assessment
(
	id                  int4 generated always as identity primary key,
	lesson_id           int4 not null,
	student_id          int4 not null,
	assessment          int2 not null,

	constraint fk__assessment__lesson__id foreign key (lesson_id) references lesson (id),
	constraint fk__assessment__student__id foreign key (student_id) references student (id)
);

create table if not exists attend
(
	id                  int4 generated always as identity primary key,
	lesson_id           int4 not null,
	student_id          int4 not null,
	visited             boolean not null,

	constraint fk__attend__lesson__id foreign key (lesson_id) references lesson (id),
	constraint fk__attend__student__id foreign key (student_id) references student (id)
);