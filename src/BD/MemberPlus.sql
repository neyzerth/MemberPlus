-- Active: 1721702131855@@localhost@3306@member_plus

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
)

Create table cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    persona INT NOT NULL,
    Foreign Key (persona) REFERENCES persona (idPersona)
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

-- Agregar la columna 'nivel' a la tabla 'tarjeta'
ALTER TABLE tarjeta
ADD COLUMN nivel INT;

-- Añadir la clave foránea para la columna 'nivel'
ALTER TABLE tarjeta
ADD CONSTRAINT fk_nivel
FOREIGN KEY (nivel) REFERENCES nivel(idNivel);

CREATE TABLE nivel (
    idNivel INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre varchar(20) NOT NULL,
    anualidad int NOT NULL, 
    costoApertura int NOT NULL
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

CREATE TABLE beneficio (
    idBeneficio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    fecInicio Date NOT NULL,
    fecVen DATE NOT NULL,
    porcentajePuntos int,
    porcentajeCashBack int
    
);

ALTER TABLE beneficio
ADD descuento INT;  


drop table tipo_movimiento;
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
    FOREIGN KEY (usuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta),
    FOREIGN KEY (tipoMovimiento) REFERENCES tipo_Movimiento(idTipoMovimiento)
)


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



--Delete and update on cascada

--Usuario 
ALTER TABLE usuario
DROP FOREIGN KEY usuario_ibfk_1;  
ALTER TABLE usuario
ADD CONSTRAINT fk_usuario_rol
FOREIGN KEY (rol) REFERENCES rol(idRol)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE usuario
DROP FOREIGN KEY usuario_ibfk_2;  

ALTER TABLE usuario
ADD CONSTRAINT fk_usuario_persona
FOREIGN KEY (persona) REFERENCES persona(idPersona)
ON DELETE CASCADE
ON UPDATE CASCADE;

--Tarjeta 

ALTER TABLE tarjeta
DROP FOREIGN KEY tarjeta_ibfk_1;  

ALTER TABLE tarjeta
ADD CONSTRAINT fk_tarjeta_cliente
FOREIGN KEY (cliente) REFERENCES cliente(idCliente)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE tarjeta
DROP FOREIGN KEY fk_nivel;  

ALTER TABLE tarjeta
ADD CONSTRAINT fk_nivel
FOREIGN KEY (nivel) REFERENCES nivel(idNivel)
ON DELETE CASCADE
ON UPDATE CASCADE;




--Movimiento
ALTER TABLE movimiento
DROP FOREIGN KEY movimiento_ibfk_1;

ALTER TABLE movimiento
ADD CONSTRAINT fk_movimiento_usuario
FOREIGN KEY (usuario) REFERENCES usuario(idUsuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE movimiento
DROP FOREIGN KEY movimiento_ibfk_2;

ALTER TABLE movimiento
ADD CONSTRAINT fk_movimiento_tarjeta
FOREIGN KEY (tarjeta) REFERENCES tarjeta(idTarjeta)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE movimiento
DROP FOREIGN KEY movimiento_ibfk_3;

ALTER TABLE movimiento
ADD CONSTRAINT fk_movimiento_tipoMovimiento
FOREIGN KEY (tipoMovimiento) REFERENCES tipo_movimiento(idTipoMovimiento)
ON DELETE CASCADE
ON UPDATE CASCADE;

--Tablas de relacion M a M no se si maneja igual
ALTER TABLE compra_Beneficio
DROP FOREIGN KEY compra_beneficio_ibfk_1;

ALTER TABLE compra_Beneficio
ADD CONSTRAINT fk_compra_beneficio_compra
FOREIGN KEY (compra) REFERENCES compra(idCompra)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE compra_Beneficio
DROP FOREIGN KEY compra_beneficio_ibfk_2;

ALTER TABLE compra_Beneficio
ADD CONSTRAINT fk_compra_beneficio_beneficio
FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio)
ON DELETE CASCADE
ON UPDATE CASCADE;



--nivel-Beneficio
ALTER TABLE nivel_Beneficio
DROP FOREIGN KEY nivel_beneficio_ibfk_1;

ALTER TABLE nivel_Beneficio
ADD CONSTRAINT fk_nivel_beneficio_nivel
FOREIGN KEY (nivel) REFERENCES nivel(idNivel)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE nivel_Beneficio
DROP FOREIGN KEY nivel_beneficio_ibfk_2;

ALTER TABLE nivel_Beneficio
ADD CONSTRAINT fk_nivel_beneficio_beneficio
FOREIGN KEY (beneficio) REFERENCES beneficio(idBeneficio)
ON DELETE CASCADE
ON UPDATE CASCADE;





