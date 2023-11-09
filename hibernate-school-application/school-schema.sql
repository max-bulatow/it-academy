create table address 
(
	id int4 generated always as identity primary key,
	city varchar(30) not null,
	street varchar(50) not null,
	building_number varchar(10) not null
);

create table school
(
	id int4 generated always as identity primary key,
	name varchar(250) not null,
	address_id int4 not null,
	
	constraint fk__school__address__id foreign key (address_id) references address (id)
);

create table parent 
(
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table teacher
(
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table schoolGroup 
(
	id int4 generated always as identity primary key,
	name varchar(20) not null,
	teacher_id int4 not null,
	school_id int4 not null,
	
	constraint fk__schoolGroup__school__id foreign key (school_id) references school (id)	
);

create table groupRoom
(
	id int4 generated always as identity primary key,
	schoolGroup_id int4 not null,
	teacher_id int4 not null,
	school_id int4 not null,

	constraint fk__groupRoom__school__id foreign key (school_id) references school (id),
	constraint fk__groupRoom__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id),
	constraint fk__groupRoom__teacher__id foreign key (teacher_id) references teacher (id)
);

create table schedule
(
	id int4 generated always as identity primary key,
	startDate timestamp with time zone not null,
	endDate timestamp with time zone not null,
	schoolGroup_id int4 unique not null,
	school_id int4 not null,
	
	constraint fk__schedule__school__id foreign key (school_id) references school (id),
	constraint fk__schedule__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id)
);

create table lesson
(
	id int4 generated always as identity primary key,
	teacher_id int4 not null,
	schoolGroup_id int4 not null,
	groupRoom_id int4 not null,
	schedule_id int4 not null,
	
	constraint fk__lesson__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id),
	constraint fk__lesson__groupRoom__id foreign key (groupRoom_id) references groupRoom (id),
	constraint fk__lesson__teacher__id foreign key (teacher_id) references teacher (id),
	constraint fk__lesson__schedule__id foreign key (schedule_id) references schedule (id)
);

create table student
(
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	parent_id int4 not null,
	schoolGroup_id int4 not null,
	lesson_id int4 not null,
	
	constraint fk__student__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id),
	constraint fk__student__parent__id foreign key (parent_id) references parent (id),
	constraint fk__student__lesson__id foreign key (lesson_id) references lesson (id)
);

create table attend
(
	id int4 generated always as identity primary key,
	visited boolean not null,
	lesson_id int4 not null,
	student_id int4 not null,
	
	constraint fk__attend__lesson__id foreign key (lesson_id) references lesson (id),
	constraint fk__attend__student__id foreign key (student_id) references student (id)
);

create table subject
(
	id int4 generated always as identity primary key,
	name varchar(50) not null,
	schoolGroup_id int4 not null,
	lesson_id int4 not null,
	school_id int4 not null,
	
	constraint uq__subject__name unique (name),
	constraint fk__subject__school__id foreign key (school_id) references school (id),
	constraint fk__subject__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id),
	constraint fk__subkect__lesson__id foreign key (lesson_id) references lesson (id)
);

create table assessment
(
	id int4 generated always as identity primary key,
	assessment int2 not null,
	created timestamp with time zone not null,
	student_id int4 not null,
	teacher_id int4 not null,
	subject_id int4 not null,
	lesson_id int4 not null,
	
	constraint fk__assessment__student__id foreign key (student_id) references student (id),
	constraint fk__assessment__teacher__id foreign key (teacher_id) references teacher (id),
	constraint fk__assessment__subject__id foreign key (subject_id) references subject (id),
	constraint fk__assessment__lesson__id foreign key (lesson_id) references lesson (id)
);

create table school_teacher 
(
	id int4 generated always as identity primary key,
	school_id int4 not null,
	teacher_id int4 not null,
	
	constraint fk__school_teacher__school__id foreign key (school_id) references school (id),
	constraint fk__school_teacher__teacher_id foreign key (teacher_id) references teacher (id),
	constraint uq__school_teacher__school_id_teacher_id unique (school_id, teacher_id)
);

create table school_student
(
	id int4 generated always as identity primary key,
	school_id int4 not null,
	student_id int4 not null,
	
	constraint fk__school_student__school__id foreign key (school_id) references school (id),
	constraint fk__school_student__student__id foreign key (student_id) references student (id),
	constraint uq__school_student__school_id_student_id unique (school_id, student_id)
);

create table teacher_schoolGroup
(
	id int4 generated always as identity primary key,
	teacher_id int4 not null,
	schoolGroup_id int4 not null,
	
	constraint fk__teacher_schoolGroup__teacher__id foreign key (teacher_id) references teacher (id),
	constraint fk__teacher_schoolGroup__schoolGroup__id foreign key (schoolGroup_id) references schoolGroup (id),
	constraint uq__teacher_schoolGroup__teacher_id_schoolGroup_id unique (teacher_id, schoolGroup_id)
);

create table teacher_subject
(
	id int4 generated always as identity primary key,
	teacher_id int4 not null,
	subject_id int4 not null,
	
	constraint fk__teacher_subject__teacher__id foreign key (teacher_id) references teacher (id),
	constraint fk__teacher_subject__subject__id foreign key (subject_id) references subject (id),
	constraint uq__teacher_subject__teacher_id_subject_id unique (teacher_id, subject_id)
);	
