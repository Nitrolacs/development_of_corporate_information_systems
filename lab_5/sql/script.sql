CREATE DATABASE db_for_java;
CREATE TABLE Bike(
    id serial PRIMARY KEY,
    price double precision NOT NULL,
    numberOfSpeeds integer NOT NULL,
    name varchar NOT NULL,
    type varchar NOT NULL,
    frameMaterial varchar NOT NULL
);