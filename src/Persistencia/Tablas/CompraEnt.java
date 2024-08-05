package Persistencia.Tablas;

import Persistencia.Query;
import java.sql.Date;

public class CompraEnt extends Query {
    public CompraEnt() {
        super("compra",
            "idCompra",
            "fecha",
            "puntos",
            "descuento",
            "cashback",
            "tarjeta",
            "subtotal",
            "total"
        );
    }

    // Obtener una compra específica por su ID
    public Object[] obtenerCompraPorIdDB(int idCompra) {
        return ejecutarSelectPorID(idCompra);
    }

    // Verificar si una compra ya existe en la base de datos por fecha y total
    //En fecha era String pero lo cambie a Date
    public boolean existeCompra(Date fecha,String tarjeta) {
        return existeRegistro(getNomColumna(2), fecha) && existeRegistro(getNomColumna(5), tarjeta);
    }

    // Actualizar una compra
    public boolean actualizarCompraDB(int idCompra, Date fecha, int puntos, float descuento, float cashback, int idTarjeta, float subtotal, float total) {
        Object[] valores = { idCompra, fecha, puntos, descuento, cashback,idTarjeta, subtotal, total };
        String query = update(valores, idCompra);
        return ejecutarUpdate(query);
    }

    // Insertar una compra
    public boolean insertarCompraDB( Date fecha, int puntos, float descuento, float cashback, int idTarjeta, float subtotal, float total) {
        Object[] valores = { fecha, puntos, descuento, cashback,idTarjeta, subtotal, total };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar una compra
    public boolean eliminarCompraDB(int idCompra) {
        String query = delete(idCompra);
        return ejecutarDelete(query);
    }

    // Método para obtener una compra por fecha y total desde la base de datos
    public Object[] obtenerPorFechaYTotalDB(String fecha, float total) {
        Object[] valores = { fecha, total };
        return ejecutarSelectUno(getNomColumna(2), valores);
    }
}
