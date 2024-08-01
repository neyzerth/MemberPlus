package Persistencia.Tablas;

import Persistencia.Query;

public class CompraEnt extends Query {
    public CompraEnt() {
        super("compra",
                "idCompra",
                "fecha",
                "porcPunto",
                "descuento",
                "tarjeta",
                "total");
    }

    // Obtener una compra específica por su ID
    public Object[] obtenerCompraPorIdDB(int idCompra) {
        return ejecutarSelectPorID(idCompra);
    }

    // Verificar si una compra ya existe en la base de datos por fecha y total
    public boolean existeCompra(String fecha,String tarjeta) {
        return existeRegistro(getNomColumna(2), fecha) && existeRegistro(getNomColumna(5), tarjeta);
    }

    // Actualizar una compra
    public boolean actualizarCompraDB(int idCompra, String fecha, int porcPunto, int descuento, int idTarjeta, float total) {
        Object[] valores = { idCompra, fecha, porcPunto, descuento, idTarjeta, total };
        String query = update(valores, idCompra);
        return ejecutarUpdate(query);
    }

    // Insertar una compra
    public boolean insertarCompraDB(int idCompra, String fecha, int porcPunto, int descuento, int idTarjeta, float total) {
        Object[] valores = { idCompra, fecha, porcPunto, descuento, idTarjeta, total };
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
