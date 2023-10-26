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
CREATE TABLE t_user (
    id serial PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE t_role(
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE user_role (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (role_id) REFERENCES t_role(id)
);