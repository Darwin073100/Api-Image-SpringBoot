create database files;

create table image(
    "id" serial not null,
    "nombre" varchar(50)null,
    constraint pkImage primary key("id")
);