CREATE DATABASE member_plus;
use member plus;
CREATE TABLE persona(
    id_empleado INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    correo VARCHAR(40),
    fecNac DATE NOT NULL,
    nom_pila varchar(15) NOT NULL,
    nom_apellidoMa varchar(15) NOT NULL,
    nom_apellidoPa varchar(15)
);

CREATE TABLE tarjeta(
    numTarjeta BIGINT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fec_Exp DATE NOT NULL,
    fec_Ven DATE NOT NULL,
    activo BOOLEAN NOT NULL,
    saldo DOUBLE NOT NULL,
    puntos INT 10 NOT NULL
);

CREATE TABLE nivel(
    id_nivel INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(20) NOT NULL,
    anualidad DOUBLE NOT NULL,
    costo DOUBLE NOT NULL,
);
CREATE TABLE beneficios(
    id_beneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecVen DATE NOT NULL,
);