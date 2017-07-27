create database task;
create table users(id serial primary key, login character varying (2000), password character varying(2000));
create table roles(id serial primary key, name character varying(2000));
create table users_roles(id_user integer references users(id), id_role integer references roles(id));
create table access(id serial primary key, object character varying(2000), access character varying(2000));
create table role_access(id_role integer references roles(id), id_access integer references access(id));
create table status_request(id serial primary key, description character varying(2000));
create table category_request(id serial primary key, category character varying(2000));
create table request(id serial primary key, description character varying(2000), id_status integer references status_request(id));
alter table request add column id_category integer references category_request(id);
create table files_request(id serial primary key, file_path character varying(2000), id_request integer references request(id));

insert into status_request (description) values ('Accepted');
insert into status_request (description) values ('Сompleted');
insert into status_request (description) values ('Rejected');

insert into category_request (category) values ('Normal');
insert into category_request (category) values ('Hight');
insert into category_request (category) values ('Low');

insert into request (description, id_status, id_category) values ('first request', 3, 3);
insert into request (description, id_status, id_category) values ('second request', 2, 2);
insert into request (description, id_status, id_category) values ('Test', 1, 1);

insert into files_request (file_path, id_request) values ('C:/test.txt', 1);
insert into files_request (file_path, id_request) values ('C:/test2.txt', 2);
insert into files_request (file_path, id_request) values ('C:/test3.txt', 3);