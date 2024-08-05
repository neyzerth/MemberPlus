-- SQLBook: Code
-- Active: 1722578640281@@127.0.0.1@3306@member_plus
DROP DATABASE member_plus;
CREATE DATABASE member_plus;
USE member_plus;

CREATE TABLE persona (
    idPersona INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(15) NOT NULL,
    apellidoPa varchar(15) NOT NULL,
    apellidoMa varchar(15),
    fecNac DATE NOT NULL,
    colonia VARCHAR(30),
    calle VARCHAR(30),
    numExt INT, 
    numInt INT,
    cp varchar(10),
    telefono VARCHAR(12) NOT NULL,
    correo VARCHAR(40)
);

Create table cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    persona INT NOT NULL,
    Foreign Key (persona) REFERENCES persona (idPersona) ON DELETE CASCADE
);
-- SE TIENE QUE CREARA ANTES QUE USUARIO
CREATE Table rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(100) 
);


CREATE Table usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombreUsuario VARCHAR(20) not null,
    contrasena VARCHAR(20) not NULL,
    rfc VARCHAR(15),
    persona INT NOT NULL,
    rol int NOT null,
    FOREIGN KEY (rol) REFERENCES rol (idRol) ON DELETE CASCADE,
    Foreign Key (persona) REFERENCES persona (idPersona) ON DELETE CASCADE
);

CREATE TABLE nivel (
    idNivel INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(20) NOT NULL,
    anualidad int NOT NULL, 
    costoApertura int NOT NULL
);

CREATE TABLE tarjeta (
    idTarjeta INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    numTarjeta VARCHAR (25) NOT NULL,
    fecExp DATE NOT NULL,
    fecVen DATE NOT NULL,
    activo BIT NOT NULL,
    saldo FLOAT NOT NULL,
    puntos INT  NOT NULL,
    cliente INT NOT NULL,
    nivel INT NOT NULL,
    FOREIGN KEY (cliente) REFERENCES cliente (idcliente) ON DELETE CASCADE,
    FOREIGN KEY (nivel) REFERENCES nivel(idNivel) ON DELETE CASCADE
);


Create table compra (
    idCompra INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecha DATE NOT NULL,
    puntos int NULL,
    descuento FLOAT NULL,
    cashback FLOAT NULL,
    tarjeta int not null,
    subtotal FLOAT NULL,
    total FLOAT NOT NULL,
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta) ON DELETE CASCADE
);

CREATE TABLE beneficio (
    idBeneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecInicio Date NOT NULL,
    fecVen DATE NOT NULL,
    porcentajePuntos int,
    porcentajeCashBack int,
    descuento INT
);


CREATE table tipo_movimiento(
    idTipoMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(100)
);

create table movimiento(
    idMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecMovimiento DATE NOT NULL,
    estado varchar(30),
    comentario VARCHAR(100),
    usuario int not null,
    tarjeta int not null,
    tipoMovimiento int not null,
    FOREIGN KEY (usuario) REFERENCES usuario(idUsuario) ON DELETE CASCADE,
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta) ON DELETE CASCADE,
    FOREIGN KEY (tipoMovimiento) REFERENCES tipo_Movimiento(idTipoMovimiento) ON DELETE CASCADE
);


CREATE TABLE nivel_Beneficio (
    nivel int not null,
    beneficio int not null,
    PRIMARY KEY(nivel,beneficio),
    FOREIGN KEY (Nivel) REFERENCES nivel(idNivel) ON DELETE CASCADE,
    FOREIGN KEY (Beneficio) REFERENCES beneficio(idBeneficio) ON DELETE CASCADE
);


Create Table compra_Beneficio (
    compra int not null,
    beneficio int not null,
    PRIMARY KEY(compra,beneficio),
    FOREIGN KEY (compra) REFERENCES compra(idCompra) ON DELETE CASCADE,
    FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio) ON DELETE CASCADE
);

