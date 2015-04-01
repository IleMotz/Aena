CREATE DATABASE Aena;

USE Aena;

CREATE TABLE airplane (
idAirplane int PRIMARY KEY,
matricula varchar(45)
 );
 
 INSERT INTO airplane VALUES (0,"QWE-9874"), (1,"ASD-3214");
 
 SELECT * FROM airplane;