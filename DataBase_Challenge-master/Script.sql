-- Create Database
CREATE DATABASE IF NOT EXISTS PERSONASS;
USE PERSONASS;

-- Create Company Table
CREATE TABLE IF NOT EXISTS Company (
                                       idCompany INT PRIMARY KEY,
                                       name VARCHAR(45) NOT NULL,
    phoneNumber VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    address VARCHAR(45)
    );

-- Create Employee Table
CREATE TABLE IF NOT EXISTS Employee (
                                        idEmployee INT NOT NULL,
                                        firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    phoneNumber VARCHAR(45) NOT NULL,
    address VARCHAR(45) NULL,
    Salary DOUBLE NOT NULL,
    birthDate DATE NOT NULL,
    idCompany INT NOT NULL,
    PRIMARY KEY (idEmployee),
    FOREIGN KEY (idCompany) REFERENCES Company(idCompany)
    );

-- Create Children Table
CREATE TABLE IF NOT EXISTS Children (
                                        idChild INT AUTO_INCREMENT PRIMARY KEY,
                                        idEmployee INT NOT NULL,
                                        fullName VARCHAR(45) NOT NULL,
    birthDate DATE NOT NULL,
    gender VARCHAR(20),
    FOREIGN KEY (idEmployee) REFERENCES Employee(idEmployee)
    );

-- Create Institution Table
CREATE TABLE IF NOT EXISTS Institution (
                                           idInstitution INT AUTO_INCREMENT PRIMARY KEY,
                                           institutionName VARCHAR(45) NOT NULL,
    studies VARCHAR(45) NOT NULL,
    idEmployee INT NOT NULL,
    FOREIGN KEY (idEmployee) REFERENCES Employee(idEmployee)
    );

-- Insert Data
INSERT INTO Company (idCompany, `name`, phoneNumber, email, address)
VALUES
    ('1', 'Carulla', '54136579', 'carulla@gmail.com', 'Carrera 72 #45-67'),
    ('2', 'Jumbo', '54335486', 'jumbo@gmail.com', 'Calle 81 #23-45'),
    ('3', 'Colsubsidio', '487257', 'colsubsidio@gmail.com', 'Avenida 12 #34-56'),
    ('4', 'La 14', '7822365', 'la14@gmail.com', 'Carrera 36 #78-90'),
    ('5', 'PriceSmart', '12345', 'pricemart@gmail.com', 'Calle 26');

INSERT INTO Employee (idEmployee, firstName, lastName, email, phoneNumber, address, Salary, birthDate, idCompany)
VALUES
    ('74', 'Juan', 'Castillo', 'juan@gmail.com', '523657563', '96 Toohey rd', '2900', '1985-05-23', '5'),
    ('83', 'Pedro', 'Lopez', 'pedro@gmail.com', '6952335', '15 Adelade st', '3800', '1982-04-16', '5'),
    ('92', 'Maria', 'Perez', 'maria@gmail.com', '45623688', '87 Woolbor st', '3500', '1984-09-17', '3'),
    ('95', 'Luis', 'Gonzalez', 'luis@gmail.com', '78962426', '42 George av.', '4100', '1979-02-06', '4'),
    ('64', 'Ana', 'Lea', 'ana@gmail.com', '6324584', '37 George st.', '3050', '1990-05-07', '1');

INSERT INTO Children (idChild, idEmployee, fullName, birthDate, gender)
VALUES
    ('1', '74', 'Juan', '2006-11-14', 'Male'),
    ('2', '95', 'Sofía', '2009-05-24', 'Female'),
    ('3', '83', 'Sumara', '2016-02-16', 'Male'),
    ('4', '74', 'Luis', '2016-07-28', 'Male'),
    ('5', '92', 'Sara', '2017-10-01', 'Female');

INSERT INTO Institution (institutionName, studies, idEmployee)
VALUES
    ('Universidad Distrital', 'Ingenieria electronica', '74'),
    ('Universidad de Xi an', 'Ferrovias', '83'),
    ('Universidad Javeriana', 'Bioingenieria', '92'),
    ('Universidad de los Andes', 'Economia', '95'),
    ('Universidad de America', 'Ingenieria quimica', '64');
