package Persistencia.Tablas;

public class MovimientoEnt extends Query {

    public MovimientoEnt() {
        super("movimiento",
                "idMovimiento",
                "fecMovimiento",
                "estado",
                "comentario",
                "usuario",
                "tarjeta",
                "tipoMovimiento");
    }

    // Obtener un movimiento específico por su ID
    public Object[] obtenerMovimientoPorIdDB(int idMovimiento) {
        return ejecutarSelectPorID(idMovimiento);
    }

    // Verificar si un movimiento ya existe en la base de datos por su estado y usuario
    //el idUsuario sera la fk
    public boolean existeMovimiento(String estado, int idUsuario) {
        // Object[] valores = { estado, idUsuario };
        return existeRegistro(getNomColumna(3), estado) && existeRegistro(getNomColumna(5), idUsuario);
    }

    // Actualizar un movimiento
    public boolean actualizarMovimientoDB(int idMovimiento, String fechaMovimiento, String estado, String comentario, int idUsuario, int idTarjeta, int idTipoMovimiento) {
        Object[] valores = { idMovimiento, fechaMovimiento, estado, comentario, idUsuario, idTarjeta, idTipoMovimiento };
        String query = update(valores, idMovimiento);
        return ejecutarUpdate(query);
    }

    // Insertar un movimiento
    public boolean insertarMovimientoDB(int idMovimiento, String fechaMovimiento, String estado, String comentario, int idUsuario, int idTarjeta, int idTipoMovimiento) {
        Object[] valores = { idMovimiento, fechaMovimiento, estado, comentario, idUsuario, idTarjeta, idTipoMovimiento };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un movimiento
    public boolean eliminarMovimientoDB(int idMovimiento) {
        String query = delete(idMovimiento);
        return ejecutarDelete(query);
    }

    // Método para obtener movimientos por estado desde la base de datos
    public Object[] obtenerPorEstadoDB(String estado) {
        return ejecutarSelectUno(getNomColumna(3), estado);
    }
}
