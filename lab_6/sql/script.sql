CREATE DATABASE db_for_java;
\c db_for_java;
CREATE TABLE Bike(
    id serial PRIMARY KEY,
    price double precision NOT NULL,
    numberOfSpeeds integer NOT NULL,
    name varchar NOT NULL,
    type varchar NOT NULL,
    frameMaterial varchar NOT NULL
);
CREATE TABLE t_user(
    id serial PRIMARY KEY,
    username varchar NOT NULL,
    password varchar NOT NULL
);
CREATE TABLE t_role(
    id serial PRIMARY KEY,
    name varchar NOT NULL
);