--create database almacen2d;
--use almacen2d;
CREATE TABLE cliente (
    num int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(30) not null,
    nomcont varchar(20),
    apePaterno varchar(30) not null,
    apeMaterno varchar(50),
    telefono char(15) not null,
    limCredito float not null
);

Create TABLE ciudad (
    codigo varchar(5) PRIMARY KEY,
    nombre varchar(20) not null UNIQUE
);

Create TABLE sucursal (
    codigo varchar(5) PRIMARY KEY,
    nombre varchar(50) not null UNIQUE,
    numtel varchar(20) not null,
    dirCalle varchar(30) not null,
    dirNumero varchar(30) not null,
    dirColonia varchar(30) not null,
    ciudad varchar(5) not null,
    FOREIGN KEY (ciudad) REFERENCES ciudad (codigo)
);
CREATE table rep_vtas (
    num int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(30) not NULL,
    primerApellido varchar(30) NOT NULL,
    segundoApellido varchar(30),
    fechaContrato date NOT NULL,
    director int,
    sucursal varchar(5) NOT NULL,
    Foreign Key (sucursal) REFERENCES sucursal(codigo)
);
alter Table rep_vtas
add constraint FK_repVtas_director
FOREIGN KEY(director) REFERENCES rep_vtas(num);

CREATE TABLE producto (
    codigo varchar(5) PRIMARY KEY,
    nombre varchar(20) not null,
    descripcion varchar(200),
    precioVenta float not null
);
CREATE TABLE pedido(
    num INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecha DATE not NULL,
    canTotalProd INT,
    subTotal FLOAT,
    IVA FLOAT,
    total FLOAT,
    totalConInt FLOAT,
    sucursal VARCHAR(5) NOT NULL,
    cliente INT NOT NULL,
    repVtas INT NOT NULL,
    Foreign Key (sucursal) REFERENCES sucursal(codigo), 
    Foreign Key (repVtas) REFERENCES rep_vtas(num), 
    Foreign Key (cliente) REFERENCES cliente(num)
);
create table metas (
    num INT PRIMARY KEY AUTO_INCREMENT,
    fechaInicio DATE NOT NULL,
    fecahFinal DATE NOT NULL,
    montoMeta FLOAT NOT NULL,
    montoVtas FLOAT,
    sucursal VARCHAR(5),
    repVtas INT,
    Foreign Key (sucursal) REFERENCES sucursal(codigo),
    Foreign Key (repVtas) REFERENCES rep_vtas(num)
);
CREATE TABLE pago (
    num INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    numPago INT,
    montoPago FLOAT NOT NULL,
    concepto VARCHAR(30),
    saldo FLOAT,
    pedido INT NOT NULL,
    Foreign Key (pedido) REFERENCES pedido(num)
);
CREATE TABLE almacen (
    sucursal VARCHAR(5),
    producto VARCHAR(10),
    stock INT NOT NULL,
    PRIMARY KEY (sucursal, producto),
    Foreign Key (sucursal) REFERENCES sucursal(codigo),
    Foreign Key (producto) REFERENCES producto(codigo)
);
CREATE TABLE ped_prod(
    pedido INT,
    producto VARCHAR(10),
    cantidad INT NOT NULL,
    importe FLOAT,
    PRIMARY KEY (pedido, producto),
    Foreign Key (pedido) REFERENCES pedido(num),
    Foreign Key (producto) REFERENCES producto(codigo)
);
CREATE TABLE  num_Tel (
    repVtas int,
    num int NOT NULL,
    numTel CHAR,
    PRIMARY KEY(repVtas, num),
    Foreign Key (repVtas) REFERENCES rep_vtas(num)
);

INSERT INTO ciudad VALUES ('mxli', 'Mexicali');
INSERT INTO ciudad VALUES
('ros', 'Rosarito'),
('tj', 'Tijuana'),
('ens', 'Ensenada'),
('tct', 'Tecate'),
('sfp', 'San Felipe'),
('sqn', 'San Quintin');

INSERT INTO producto values
('pulpa','pulparindo','caja de 20pz chicas',100),
('migue','polvo Miguelito','caja con 100 sobres',50),
('maza','Mazapan de la rosa','dulce de cacahuate caja con 12',80),
('pgoma','Picagoma vero','gomitas enchiladas bolsa con 100',60)

UPDATE producto
set precioVenta = 30
WHERE codigo = 'migue'

INSERT INTO cliente VALUES
(NULL, 'Yadira',null ,'Salazar',null ,1500,'6647894561'),
(Null,'Dulceria Alfredo','Neyzer','Toledo','Herrera',2500,'6637010919'),
(NULL,'Dulceria Zepeda','Carlos','Zepeda','Cortez',12000,'6645678909')

UPDATE cliente
set apeMaterno = 'Cortes'
WHERE num = 3

INSERT INTO sucursal VALUES 
('send','plaza sendero','6647851221','Carretera libre tijuana','234','El refugio','tj'),
('pabel','Plaza pabellon','6616123140','Calle reforma','300','parcelas','ros'), 
('macro','Macro Plaza','6643508099','Blvd. Insurgentes','18015','rio','tj');

INSERT INTO rep_vtas (nombre, primerApellido, segundoApellido, fechaContrato, director, sucursal) VALUES
('Carlos', 'Gomez', 'Perez', '2022-01-15', NULL, 'macro'),
('David', 'Martin', 'Fernandez', '2019-09-25', NULL, 'pabel');

INSERT INTO rep_vtas (nombre, primerApellido, segundoApellido, fechaContrato, director, sucursal) VALUES
('Ana', 'Lopez', 'Martinez', '2021-03-22', 1, 'pabel'),
('Miguel', 'Hernandez', 'Ramirez', '2023-05-30', 1, 'send'),
('Laura', 'Garcia', 'Sanchez', '2020-07-19', 2, 'macro');

INSERT INTO metas (fechaInicio, fecahFinal, montoMeta, montoVtas, sucursal, repVtas) VALUES
('2024-01-01', '2024-01-31', 10000.00, 9000.00, 'macro', 1),
('2024-02-01', '2024-02-28', 15000.00, 12000.00, 'pabel', 2),
('2024-03-01', '2024-03-31', 20000.00, 18000.00, 'send', 3),
('2024-04-01', '2024-04-30', 25000.00, 23000.00, 'macro', 4),
('2024-05-01', '2024-05-31', 30000.00, 27000.00, 'pabel', 5);



SELECT * FROM producto WHERE precioVenta<100;
SELECT * FROM rep_vtas;

--EJERCICIO DE CONSULTAS
-- 1. Mostrar la lista de sucursales
SELECT * FROM sucursal;

-- 2. Mostrar la lista de sucursales con sus metas
SELECT s.nombre AS sucursales, m.montoMeta AS Meta FROM Metas AS m
INNER JOIN sucursal AS s ON m.sucursal = s.codigo WHERE sucursal IS NOT NULL; 

-- 3. Mostrar lista de vendedores de una sucursal
SELECT * FROM rep_vtas;

-- 4. Mostrar nombre, monto de metas, monto de ventas, y sus fechas de inicio y fin del vendedor numero 3
SELECT CONCAT(
    IFNULL (CONCAT(rv.nombre, ' '),''),
    IFNULL (CONCAT(rv.primerApellido, ' '),''),
    IFNULL (CONCAT(rv.segundoApellido, ' '),'')
) AS 'Representante de ventas',
m.montoMeta AS 'Monto meta', 
m.montoVtas AS 'Monto Ventas', 
DATE_FORMAT(m.fechaInicio, "%d-%m-%y") AS 'fecha inicio', 
DATE_FORMAT(m.fecahFinal, "%d-%m-%y") AS 'fecha final' 
FROM rep_vtas AS rv 
INNER JOIN metas AS m ON m.repVtas = rv.num 
WHERE rv.num = 3;

--'
-- 5 .Mostrar el  nombre de los vendedores que superen el monto de venta de $10,000
SELECT CONCAT(
    IFNULL (CONCAT(rv.nombre, ' '),''),
    IFNULL (CONCAT(rv.primerApellido, ' '),''),
    IFNULL (CONCAT(rv.segundoApellido, ' '),'')
) AS 'Representante de ventas', 
m.montoVtas AS 'Monto de ventas' 
FROM rep_vtas AS rv
INNER JOIN metas AS m ON m.repVtas = rv.num 
WHERE m.montoVtas > 10000;
