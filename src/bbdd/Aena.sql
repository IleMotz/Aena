
DROP DATABASE IF EXISTS Aena;
CREATE DATABASE IF NOT EXISTS Aena;

USE Aena;

CREATE TABLE airplane (
idAirplane int PRIMARY KEY,
plateNumber varchar(45)
 );
 
 INSERT INTO airplane VALUES (0,"QWE-9874"), (1,"ASD-3214");
 
 SELECT * FROM airplane;
 
CREATE TABLE airport (
idAirport int,
name varchar(45),
PRIMARY KEY(idAirport)
 );
 

INSERT INTO airport VALUES (0,"Hondarribi"), (1,"Bilbo");

SELECT * FROM airport;

CREATE TABLE gate (
idGate int,
number int,
status varchar(45),
idAirport int,
PRIMARY KEY(idGate),
FOREIGN KEY(idAirport) REFERENCES airport(idAirport)
 );
 
 INSERT INTO gate VALUES 
 	(0,1,"libre",0),
 	(1,2,"libre",0),
 	(2,3,"libre",0),
 	(3,4,"ocupada",0),
 	(4,1,"libre",1),
 	(5,2,"libre",1),
 	(6,3,"libre",1),
 	(7,4,"ocupada",1);
 
 	SELECT * FROM gate;
 	
 	SELECT airport.name AS AirportName, gate.number AS GateNumber, gate.status AS GateStatus 
 	FROM airport, gate 
 	WHERE airport.idAirport = gate.idAirport; 
 
 	ALTER TABLE airplane ADD COLUMN totalNumSeats int NOT NULL AFTER plateNumber;
 	ALTER TABLE airplane ADD COLUMN totalNumSeatsToBook int NOT NULL AFTER plateNumber;
	ALTER TABLE airplane ADD COLUMN numSeatsConfirmed int NOT NULL AFTER plateNumber;
	ALTER TABLE airplane ADD COLUMN numSeatsBooked int NOT NULL AFTER plateNumber;
 		
CREATE TABLE boardingpass (
idboardingpass int,
idAirplane int,
code varchar(45) default '',
name varchar(45) default '',
boarded boolean default false,
PRIMARY KEY(idboardingpass),
FOREIGN KEY(idAirplane) REFERENCES airplane(idAirplane)
 );
