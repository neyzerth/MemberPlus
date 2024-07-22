--CREAR REGISTROS EN LA BASE DE DATOS
INSERT INTO persona (idPersona, nombre, apellidoMa, apellidoPa, fecNac, colonia, calle, numExt, numInt, cp, telefono, correo) VALUES 
(1, 'Jane', 'Smith', 'Doe', '1999-11-10', 'Centro', 'Calle Primera', 123, NULL, '22100', '6641112223', 'jane.doe@example.com'),
(2, 'Mike', 'Brown', 'Jones', '1980-02-15', 'Chapultepec', 'Avenida Revolucion', 456, NULL, '22200', '6642223334', 'mike.jones@emailprovider.com'),
(3, 'Lucy', 'Williams', 'Liu', '1990-03-20', 'La Joya', 'Boulevard Agua Caliente', 789, NULL, '22300', '6643334445', 'lucy.liu@domain.org'),
(4, 'Mark', 'Martinez', 'Taylor', '2000-04-20', 'El Dorado', 'Callejon del Sol', 101, NULL, '22400', '6644445556', 'salesrep@companyname.com'),
(5, 'Sophia', 'Davis', 'Wilson', '2001-05-05', 'Los Alamos', 'Avenida de la Paz', 202, NULL, '22500', '6645556667', 'gamer101@emailservice.net'),
(6, 'Liam', 'Garcia', 'Johnson', '2003-06-12', 'San Angel', 'Calle Lazaro Cardenas', 303, NULL, '22600', '6646667778', 'petlover@emailhosting.biz'),
(7, 'Olivia', 'Rodriguez', 'Martinez', '2005-07-18', 'La Cumbre', 'Boulevard Diaz Ordaz', 404, NULL, '22700', '6647778889', 'consultant123@emailprovider.co.uk'),
(8, 'Emma', 'Hernandez', 'Brown', '1979-08-22', 'Bella Vista', 'Callejon de los Rios', 505, NULL, '22800', '6648889990', 'anna.brown@freemail.com'),
(9, 'Noah', 'Lopez', 'Clark', '1988-09-07', 'Las Palmas', 'Avenida Constitucion', 606, NULL, '22900', '6649990001', 'ducklover@gmail.com'),
(10, 'Ava', 'Gonzalez', 'Harris', '1982-10-09', 'Villa del Prado', 'Calle Benito Juarez', 707, NULL, '23000', '6640001112', 'mutunui.joahna@hotmail.com'),
(11, 'Joao', 'Gomes', 'Silva', '2000-01-10', 'Parnell', 'Queen Street', 701, NULL, '19091', '6631246722', 'emily.johnson@example.com'),
(12, 'Francisco', 'Carvalho', 'Santos', '2001-02-15', 'Thorndon', 'Lambton Quay', 121, NULL, '99999', '6638907654', 'michael.brown@mailservice.com'),
(13, 'Santiago', 'Lima', 'Ferreira', '2002-03-20', 'Fendalton', 'Riccarton Road', 1314, NULL, '22204', '6637011909', 'sarah.williams@webmail.com'),
(14, 'Afonso', 'Moreira', 'Pereira', '2003-04-25', 'Mount Eden', 'Ponsonby Road', 4151, NULL, '22322', '6634207779', 'john.smith@domain.org'),
(15, 'Matias', 'Fernandes', 'Costa', '2004-05-30', 'Kelburn', 'Willis Street', 5654, NULL, '99988', '6635018869', 'laura.martin@emailprovider.com'),
(16, 'Maria', 'Nogueira', 'Oliveira', '2005-06-05', 'Riccarton', 'Victoria Street', 568, NULL, '22334', '6634449088', 'james.davis@company.com'),
(17, 'Leonor', 'Teixeira', 'Martins', '2006-07-10', 'Remuera', 'Khyber Pass Road', 89, NULL, '22255', '6635558767', 'anna.lee@freemail.net'),
(18, 'Matilde', 'Pinto', 'Sousa', '2007-08-15', 'Karori', 'Colombo Street', 65, NULL, '77789', '663537393', 'robert.jones@online.com'),
(19, 'Beatriz', 'Rodrigues', 'Ribeiro', '2008-09-20', 'Merivale', 'Dominion Road', 444, NULL, '20912', '6631245678', 'jessica.taylor@inbox.com'),
(20, 'Mariana', 'Araujo', 'Almeida', '2009-10-25', 'Newmarket', 'Cuba Street', 323, NULL, '30098', '6633054586', 'david.clark@service.net');

INSERT INTO cliente VALUES
(1, 11),
(2, 12),
(3, 13),
(4, 14),
(5, 15),
(6, 16),
(7, 17),
(8, 18),
(9, 19),
(10, 20);

INSERT INTO rol VALUES
(1, 'administrador', 'Control total'),
(2, 'cajero', 'Empleado que se encarga sobre las ventas'),
(3, 'supervisor', 'Encargado de revisar niveles con sus beneficios'),
(4, 'gerente', 'Encargado de la gestion de usuarios');

DELETE FROM usuario;
ALTER TABLE usuario AUTO_INCREMENT = 1;


INSERT INTO usuario VALUES
(1, 'Juan', 'utt1', 'GAAJ920101XXX', 11, 1),
(2, 'Adriana', 'utt2', 'RAAA890908XXX', 13, 3),
(3, 'Miguel', 'utt3', 'TOMG860109XXX', 12, 2),
(4, 'Laura', 'utt4', 'GULG930210XXX', 14, 4),
(5, 'Lauro', 'utt5', 'ROJJ850303XXX', 16, 3),
(6, 'Carlos', 'utt6', 'MUGL950504XXX', 15, 2),
(7, 'Ana', 'utt7', 'LOAA910706XXX', 17, 1),
(8, 'Francisco', 'utt8', 'PEAF840807XXX', 19, 4),
(9, 'Miguel', 'utt9', 'PKJJ850303XXX', 18, 2),
(10, 'Ashley', 'utt10', 'FKD12E1ZXXX', 20, 1);

INSERT INTO tarjeta VALUES 
(DEFAULT,'1234567890123456', '2023-10-01', '2024-10-01', 1, 532.00, 12, 1),
(DEFAULT,'9876543210987654', '2023-10-17', '2024-10-17', 1, 651.00, 88, 2),
(DEFAULT,'2468135780246913', '2023-09-06', '2024-09-06', 1, 123.00, 22, 4),
(DEFAULT,'1357246891358024', '2023-08-16', '2024-08-16', 1, 358.00, 990, 8),
(DEFAULT,'8642975318642075', '2023-07-01', '2024-07-01', 1, 313.00, 123, 3),
(DEFAULT,'9753864220751864', '2023-06-21', '2024-06-21', 1, 3.00, 22, 6),
(DEFAULT,'3210987654321098', '2023-04-27', '2024-04-27', 1, 273.00, 44, 7),
(DEFAULT,'7890432187652109', '2023-03-14', '2024-03-14', 1, 146.00, 11, 9),
(DEFAULT,'4321876509876543', '2023-09-22', '2024-09-22', 1, 831.00, 32, 5),
(DEFAULT,'1238128923812938', '2024-05-14', '2024-05-14', 0, 786.00, 221, 10);

DELETE FROM tarjeta;
ALTER TABLE tarjeta AUTO_INCREMENT = 1;
INSERT INTO tarjeta (numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel) VALUES 
('1234567890123456', '2023-10-01', '2024-10-01', 1, 532.00, 12, 1, 1),
('9876543210987654', '2023-10-17', '2024-10-17', 1, 651.00, 88, 2, 2),
('2468135780246913', '2023-09-06', '2024-09-06', 1, 123.00, 22, 4, 3),
('1357246891358024', '2023-08-16', '2024-08-16', 1, 358.00, 990, 8, 4),
('8642975318642075', '2023-07-01', '2024-07-01', 1, 313.00, 123, 3, 5),
('9753864220751864', '2023-06-21', '2024-06-21', 1, 3.00, 22, 6, 1),
('3210987654321098', '2023-04-27', '2024-04-27', 1, 273.00, 44, 7, 2),
('7890432187652109', '2023-03-14', '2024-03-14', 1, 146.00, 11, 9, 3),
('4321876509876543', '2023-09-22', '2024-09-22', 1, 831.00, 32, 5, 4),
('1238128923812938', '2024-05-14', '2024-05-14', 0, 786.00, 221, 10, 5);




-- Actualizamos la columna nivel
UPDATE tarjeta SET nivel = 1 WHERE idTarjeta = 1;
UPDATE tarjeta SET nivel = 2 WHERE idTarjeta = 2;
UPDATE tarjeta SET nivel = 3 WHERE idTarjeta = 3;
UPDATE tarjeta SET nivel = 4 WHERE idTarjeta = 4;
UPDATE tarjeta SET nivel = 1 WHERE idTarjeta = 5;
UPDATE tarjeta SET nivel = 2 WHERE idTarjeta = 6;
UPDATE tarjeta SET nivel = 3 WHERE idTarjeta = 7;
UPDATE tarjeta SET nivel = 4 WHERE idTarjeta = 8;
UPDATE tarjeta SET nivel = 1 WHERE idTarjeta = 9;
UPDATE tarjeta SET nivel = 2 WHERE idTarjeta = 10;


ALTER TABLE compra AUTO_INCREMENT = 1;
INSERT INTO compra (fecha, porcPunto, descuento, tarjeta, total) VALUES
('2024-05-02', 5, 3, 1, 543.4),
('2024-05-18', 10, 6, 2, 234.0),
('2024-05-16', 8, 2, 3, 864.0),
('2024-05-18', 11, 9, 4, 535.5),
('2024-06-13', 9, 3, 5, 212.8),
('2024-06-27', 13, 6, 6, 600.2),
('2024-06-28', 7, 3, 7, 677.9),
('2024-07-04', 15, 5, 8, 540.7),
('2024-07-05', 7, 8, 9, 232.0),
('2024-07-06', 2, 1, 10, 100.7);

INSERT INTO nivel VALUES
(1, 'Basica', 400, 50),
(2, 'Oro', 550, 200),
(3, 'Platino', 1500, 300),
(4, 'Empresarial', 1800, 500),
(5, 'Black', 5000, 500);

INSERT INTO beneficio VALUES
(1, 'Basico', '2017-01-01', '2100-12-31', 5, 1),
(2, 'Buen fin', '2024-11-20', '2024-11-28', 15, 3),
(3, 'Sorpresa mensual', '2024-01-30', '2024-01-30', 10, 2),
(4, 'Descuento relampago', '2024-07-30', '2024-07-31', 20, 4),
(5, 'Premium', '2017-01-01', '2100-08-20', 50, 6),
(6, 'Regalo anual', '2017-01-01', '2100-12-31', 25, 5),
(7, 'Estandar', '2017-01-01', '2100-12-31', 10, 2),
(8, 'Recompensa dorada', '2024-10-20', '2024-10-21', 40, 8),
(9, 'Descuento de verano', '2024-07-15', '2024-08-05', 15, 3),
(10, 'Oferta de fiestas', '2024-12-01', '2024-12-20', 30, 10);


INSERT INTO tipo_Movimiento VALUES
(1, 'alta', 'Cliente nuevo'),
(2, 'renovacion', 'Renovacion tarjeta 9876 5432 1098 7654'),
(3, 'cancelacion', 'Tarjeta cancelada'),
(4, 'compra', 'Compra tarjeta 9753 8642 2075 1864');



INSERT INTO movimiento VALUES
(1, '2005-01-20', 'Iniciado', 'El pago ha sido procesado y esta en su primera fase de verificacion.', 1, 1, 2),
(2, '2003-02-15', 'Pausa', 'La transaccion ha sido temporalmente detenida debido a una revision de seguridad.', 4, 10, 3),
(3, '2007-03-10', 'Finalizado', 'El pago ha sido completado exitosamente y confirmado.', 2, 4, 2),
(4, '2001-04-05', 'Pausa', 'La operacion esta en espera mientras se revisan los detalles de la cuenta.', 3, 3, 1),
(5, '2004-05-25', 'Finalizado', 'La transaccion ha sido finalizada y el pago se ha registrado correctamente.', 5, 7, 4),
(6, '2006-06-18', 'Finalizado', 'El proceso de pago ha concluido y ha sido confirmado.', 7, 9, 2),
(8, '2008-08-08', 'Iniciado', 'La solicitud de pago esta en curso y se esta procesando.', 8, 8, 1),
(9, '2009-09-30', 'Pausa', 'La operacion esta suspendida momentaneamente a la espera de autorizacion.', 9, 5, 4),
(10, '2010-10-22', 'Pausa', 'La transaccion se encuentra en espera hasta que se resuelvan algunos detalles.', 10, 6, 4);



INSERT INTO nivel_Beneficio VALUES
(1, 7),
(2, 3),
(3, 9),
(4, 5),
(5, 2);

INSERT INTO compra_Beneficio VALUES
(10, 10),
(9, 4),
(2, 6),
(1, 8);