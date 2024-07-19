-- Active: 1721365889881@@localhost@3306@member_plus
--CREATE DATABASE member_plus;

--use member_plus;

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
)
-- SQLBook: Code
CREATE Table rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
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

CREATE TABLE beneficio (
    idBeneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecInicio Date NOT NULL,
    fecVen DATE NOT NULL,
    porcentajePuntos int,
    porcentajeCashBack int
)

CREATE table tipo_movimiento(
    idTipoMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(100)
)


--No corre
create table movimiento(
    idMovimiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecMovimiento DATE NOT NULL,
    estado VARCHAR(30),
    comentario VARCHAR(100),
    usuario int not null,
    tarjeta BIGINT(16) not null,
    tipoMovimiento int not null,
    FOREIGN KEY (usuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(numTarjeta),
    FOREIGN KEY (tipoMovimiento) REFERENCES tipo_movimiento(idTipoMovimiento)
);

--Checar como se hace estas tablas
CREATE TABLE nivel_beneficio (
    nivel int not null,
    beneficio int not null,
    PRIMARY KEY(nivel,beneficio),
    FOREIGN KEY (nivel) REFERENCES nivel(idNivel),
    FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio)
);


Create Table compra_beneficio (
    compra int not null,
    beneficio int not null,
    PRIMARY KEY(compra,beneficio),
    FOREIGN KEY (compra) REFERENCES compra(idCompra),
    FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio)
);
