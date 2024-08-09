-- Active: 1723058837855@@127.0.0.1@3306@member_plus
--Consulta para las compras de un cliente
SELECT 
    CONCAT(p.nombre, ' ', COALESCE(p.apellidoPa, ''), ' ', p.apellidoMa) AS nombre_completo,
    t.numTarjeta,
    c.fecha, 
    c.porcPunto, 
    c.descuento, 
    c.total 
FROM  compra AS c
JOIN tarjeta AS t 
ON c.tarjeta = t.idTarjeta
JOIN cliente AS cli 
ON t.cliente = cli.idCliente
JOIN persona AS p 
ON cli.idCliente = p.idPersona
WHERE cli.idCliente = 1; 

--Consulta para mostrar cliente y sus tarjetas  con su nivel
SELECT 
    c.idcliente AS clienteID,
    CONCAT(p.nombre, ' ', p.apellidoPa, ' ', p.apellidoMa) AS clienteNombre,
    t.numTarjeta,
    n.nombre AS nivelNombre
FROM  cliente AS c
JOIN  persona AS p 
    ON c.idcliente = p.idPersona
JOIN tarjeta AS t
    ON c.idcliente = t.cliente
JOIN nivel AS n 
    ON t.nivel = n.idNivel  
WHERE 
    c.idcliente = 1;


--Consulta para movimientos que realiza un usuario
SELECT 
    u.idUsuario,
    u.nombreUsuario AS usuarioNombre,
    m.idMovimiento,
    m.fecMovimiento,
    m.estado,
    tm.descripcion,
    tm.nombre AS tipoMovimiento,
    t.numTarjeta
FROM movimiento AS m
JOIN  usuario  AS u 
    ON m.usuario = u.idUsuario
JOIN tipo_movimiento AS tm
    ON m.tipoMovimiento = tm.idTipoMovimiento
JOIN tarjeta AS t 
    ON m.tarjeta = t.idTarjeta
WHERE 
    u.idUsuario = 1;

--Consulta cliente que utlizo un beneficio
SELECT
    p.nombre AS nombre_cliente,
    b.nombre AS nombre_beneficio,
    co.fecha as fecha
FROM compra_Beneficio AS cb
JOIN beneficio AS b
    ON cb.beneficio = b.idBeneficio
JOIN compra AS co 
    ON cb.compra = co.idCompra
JOIN tarjeta AS t 
    ON co.tarjeta = t.idTarjeta
JOIN cliente AS cl 
    ON t.cliente = cl.idCliente
JOIN persona AS p 
    ON cl.persona = p.idPersona;


--Consulta para saber cuantas veces es utilizado un beficio
SELECT b.nombre, COUNT(cb.beneficio) AS veces_utilizado
FROM beneficio AS b
LEFT JOIN compra_Beneficio As cb
ON b.idBeneficio = cb.beneficio
GROUP BY b.idBeneficio
ORDER BY veces_utilizado DESC;


--Consulta moviento de una tarjeta (Segun yo este va a hacer como el historial)
SELECT 
    CONCAT(p.nombre, ' ', p.apellidoMa) AS nombre_persona,
    t.numTarjeta AS numero_tarjeta,
    tm.nombre AS tipo_movimiento,
    m.fecMovimiento, 
    m.estado, 
    m.comentario
FROM movimiento AS m
JOIN tipo_Movimiento AS tm ON m.tipoMovimiento = tm.idTipoMovimiento
JOIN usuario AS u ON m.usuario = u.idUsuario
JOIN persona AS p ON u.persona = p.idPersona
JOIN tarjeta AS t ON m.tarjeta = t.idTarjeta
WHERE m.tarjeta = 1; 









