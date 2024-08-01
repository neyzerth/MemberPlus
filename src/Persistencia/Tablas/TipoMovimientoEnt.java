package Persistencia.Tablas;

import Persistencia.Query;

public class TipoMovimientoEnt extends Query {

    public TipoMovimientoEnt() {
        super("tipo_movimiento",
                "idTipoMovimiento",
                "nombre",
                "descripcion");
    }

    // Obtener un tipo de movimiento específico por su ID
    public Object[] obtenerTipoMovimientoPorIdDB(int idTipoMovimiento) {
        return ejecutarSelectPorID(idTipoMovimiento);
    }

    // Verificar si un tipo de movimiento ya existe en la base de datos por nombre
    public boolean existeTipoMovimiento(String nombreTipoMovimiento) {
        return existeRegistro(getNomColumna(1), nombreTipoMovimiento);
    }

    // Actualizar un tipo de movimiento
    public boolean actualizarTipoMovimientoDB(int idTipoMovimiento, String nombre, String descripcion) {
        Object[] valores = { idTipoMovimiento, nombre, descripcion };
        String query = update(valores, idTipoMovimiento);
        return ejecutarUpdate(query);
    }

    // Insertar un tipo de movimiento
    public boolean insertarTipoMovimientoDB( String nombre, String descripcion) {
        Object[] valores = { nombre, descripcion };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un tipo de movimiento
    public boolean eliminarTipoMovimientoDB(int idTipoMovimiento) {
        String query = delete(idTipoMovimiento);
        return ejecutarDelete(query);
    }

    // Método para obtener un tipo de movimiento por nombre desde la base de datos
    public Object[] obtenerPorNombreDB(String nombreTipoMovimiento) {
        return ejecutarSelectUno(getNomColumna(1), nombreTipoMovimiento);
    }
}

