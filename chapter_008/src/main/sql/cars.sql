create database car_garage;

create table transmission(id serial primary key, name character varying(2000));
create table engine(id serial primary key, name character varying(2000));
create table gear_shift(id serial primary key, name character varying(2000));
create table car(id serial primary key, name character varying(2000), transmission integer references transmission(id) not null, engine integer references engine(id) not null, gear_shift integer references gear_shift(id) not null);

insert into transmission(name) values ('transmission_1');
insert into transmission(name) values ('transmission_2');
insert into transmission(name) values ('transmission_3');

insert into engine(name) values ('engine_1');
insert into engine(name) values ('engine_2');
insert into engine(name) values ('engine_3');

insert into gear_shift(name) values ('gear_shift_1');
insert into gear_shift(name) values ('gear_shift_2');
insert into gear_shift(name) values ('gear_shift_3');

insert into car(name, transmission, engine, gear_shift) values ('car1', 1, 2, 3);
insert into car(name, transmission, engine, gear_shift) values ('car2', 1, 2, 1);
insert into car(name, transmission, engine, gear_shift) values ('car3', 2, 1, 1);


SELECT * FROM car;

SELECT t.name, c.name
FROM car AS c
RIGHT OUTER JOIN transmission AS t ON c.transmission = t.id
WHERE c.transmission is null;

SELECT e.name, c.name
FROM car AS c
RIGHT OUTER JOIN engine AS e ON c.engine = e.id
WHERE c.engine is null;

SELECT g.name, c.name, g.id
FROM car AS c
RIGHT OUTER JOIN gear_shift AS g ON c.gear_shift = g.id
WHERE c.gear_shift is null;


SELECT g.name, c.name, g.id
FROM car AS c
RIGHT OUTER JOIN gear_shift AS g ON c.gear_shift = g.id
WHERE c.gear_shift is null;
