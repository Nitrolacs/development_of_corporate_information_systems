CREATE DATABASE db_for_java
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
\c db_for_java;
CREATE TABLE Bike(
    id serial PRIMARY KEY,
    price double precision NOT NULL,
    numberOfSpeeds integer NOT NULL,
    name varchar NOT NULL,
    type varchar NOT NULL,
    frameMaterial varchar NOT NULL
);