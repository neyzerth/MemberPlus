package Persistencia.Tablas;

import Persistencia.Query;

public class NivelEnt extends Query {
    public NivelEnt() {
        super("nivel",
                "idNivel",
                "nombre",
                "anualidad",
                "costoApertura");
    }

    // Obtener un nivel específico por su ID
    public Object[] obtenerNivelPorIdDB(int idNivel) {
        return ejecutarSelectPorID(idNivel);
    }

    // Verificar si un nivel ya existe en la base de datos por nombre
    public boolean existeNivel(String nombreNivel) {
        return existeRegistro(getNomColumna(1), nombreNivel);
    }

    // Actualizar un nivel
    public boolean actualizarNivelDB(int idNivel, String nombre, int anualidad, int costoApertura) {
        Object[] valores = { idNivel, nombre, anualidad, costoApertura };
        String query = update(valores, idNivel);
        return ejecutarUpdate(query);
    }

    // Insertar un nivel
    public boolean insertarNivelDB(int idNivel, String nombre, int anualidad, int costoApertura) {
        Object[] valores = { idNivel, nombre, anualidad, costoApertura };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un nivel
    public boolean eliminarNivelDB(int idNivel) {
        String query = delete(idNivel);
        return ejecutarDelete(query);
    }

    // Método para obtener un nivel por nombre desde la base de datos
    public Object[] obtenerPorNombreDB(String nombreNivel) {
        return ejecutarSelectUno(getNomColumna(1), nombreNivel);
    }
}
