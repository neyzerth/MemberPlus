<<<<<<< HEAD
-- SQLBook: Code
-- Active: 1721336085259@@127.0.0.1@3306@memberplus
CREATE DATABASE memberPlus;

use memberPlus;

=======
-- Active: 1721365889881@@localhost@3306@member_plus
--DROP DATABASE member_plus;
CREATE DATABASE member_plus;

use member_plus;
>>>>>>> b10acbfb68be3f117dc271deb956ab1b15ad1616

CREATE TABLE persona (
    idPersona INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(15) NOT NULL,
    apellidoMa varchar(15) NOT NULL,
    apellidoPa varchar(15),
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
    Foreign Key (persona) REFERENCES persona (idPersona)
<<<<<<< HEAD
)
=======
);
>>>>>>> b10acbfb68be3f117dc271deb956ab1b15ad1616
-- SQLBook: Code
CREATE Table rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
<<<<<<< HEAD
    descripcion VARCHAR(100) NOT NULL
);

-- Se tiene que primero crear la tabla rol para hacer la fk
CREATE Table usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombreUsuario VARCHAR(20) not null,
    contrasena VARCHAR(20) not NULL,
    rfc VARCHAR(15),
    persona INT NOT NULL,
    rol int NOT null,
    FOREIGN KEY (rol) REFERENCES rol (idRol),
    Foreign Key (persona) REFERENCES persona (idPersona)
);

CREATE TABLE tarjeta (
    numTarjeta BIGINT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecExp DATE NOT NULL,
    fecVen DATE NOT NULL,
    activo BIT NOT NULL,
    saldo FLOAT NOT NULL,
    puntos INT  NOT NULL,
    cliente INT NOT NULL,
    FOREIGN KEY (cliente) REFERENCES cliente (idcliente)
)


Create table compra (
    idCompra INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecha DATE NOT NULL,
    porcPunto int,
    descuento int,
    tarjeta BIGINT(16) not null,
    total FLOAT NOT NULL,
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(numTarjeta) 
)


CREATE TABLE nivel (
    idNivel INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(20) NOT NULL,
    anualidad int NOT NULL, 
    costoApertura int NOT NULL
)

CREATE TABLE beneficios (
    idBeneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecInicio Date NOT NULL,
    fecVen DATE NOT NULL,
    porcentajePuntos int,
    porcentajeCashBack int
)

CREATE table tipoMoviento(
    idTipoMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(100)
)


--No corre
create table movimiento(
    idMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecMovimiento DATE NOT NULL,
    estado BIT,
    comentario VARCHAR(100),
    usuario int not null,
    tarjeta BIGINT(16) not null,
    tipoMovimiento int not null,
    FOREIGN KEY (usuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(numTarjeta),
    FOREIGN KEY (tipoMovimiento) REFERENCES tipoMovimiento(idTipoMovimiento)
)

--Checar como se hace estas tablas
CREATE TABLE nivelBeneficio (
    nivel int not null,
    beneficio int not null,
    PRIMARY KEY(nivel,beneficio),
    FOREIGN KEY (Nivel) REFERENCES nivel(idNivel),
    FOREIGN KEY (Beneficio) REFERENCES beneficios(idBeneficio)
);


Create Table compraBeneficio (
    compra int not null,
    beneficio int not null,
    PRIMARY KEY(compra,beneficio),
    FOREIGN KEY (compra) REFERENCES compra(idCompra),
    FOREIGN KEY (beneficio) REFERENCES beneficios(idBeneficio)
);
=======
    descripcion VARCHAR(100) 
);

CREATE Table usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombreUsuario VARCHAR(20) not null,
    contrasena VARCHAR(20) not NULL,
    rfc VARCHAR(15),
    persona INT NOT NULL,
    rol int NOT null,
    FOREIGN KEY (rol) REFERENCES rol (idRol),
    Foreign Key (persona) REFERENCES persona (idPersona)
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
    FOREIGN KEY (cliente) REFERENCES cliente (idcliente)
);

Create table compra (
    idCompra INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecha DATE NOT NULL,
    porcPunto int,
    descuento int,
    tarjeta int not null,
    total FLOAT NOT NULL,
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta) 
);

CREATE TABLE nivel (
    idNivel INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(20) NOT NULL,
    anualidad int NOT NULL, 
    costoApertura int NOT NULL
);

CREATE TABLE beneficio (
    idBeneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecInicio Date NOT NULL,
    fecVen DATE NOT NULL,
    porcentajePuntos int,
    porcentajeCashBack int
);

CREATE table tipo_Movimiento(
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
    FOREIGN KEY (usuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta),
    FOREIGN KEY (tipoMovimiento) REFERENCES tipo_Movimiento(idTipoMovimiento)
);

CREATE TABLE nivel_Beneficio (
    nivel int not null,
    beneficio int not null,
    PRIMARY KEY(nivel,beneficio),
    FOREIGN KEY (Nivel) REFERENCES nivel(idNivel),
    FOREIGN KEY (Beneficio) REFERENCES beneficio(idBeneficio)
);

Create Table compra_Beneficio (
    compra int not null,
    beneficio int not null,
    PRIMARY KEY(compra,beneficio),
    FOREIGN KEY (compra) REFERENCES compra(idCompra),
    FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio)
);
>>>>>>> b10acbfb68be3f117dc271deb956ab1b15ad1616
