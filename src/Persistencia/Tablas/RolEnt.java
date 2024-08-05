package Persistencia.Tablas;

import Persistencia.Query;

public class RolEnt extends Query {

    public RolEnt() {
        super("rol",
                "idRol",
                "nombre",
                "descripcion");
    }

    // Obtener un rol específico por su ID
    public Object[] obtenerRolPorIdDB(int idRol) {
        return ejecutarSelectPorID(idRol);
    }

      // Verificar si un rol ya existe en la base de datos por nombre
    public boolean existeRol(String nombreRol) {
        return existeRegistro(getNomColumna(1), nombreRol);
    }
    
    //Actualizar Rol
    public boolean actualizarRolDB(int idRol, String nombre, String descripcion) {
        Object[] valores = { idRol,nombre, descripcion };
        String query = update(valores, idRol);
        return ejecutarUpdate(query);
    }
    
    
    // Insertar un rol
    public boolean insertarRolDB(String nombre, String descripcion) {
        Object[] valores = {nombre, descripcion };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un rol
    public boolean eliminarRolDB(int idRol) {
        String query = delete(idRol);
        return ejecutarDelete(query);
    }

    //método para obtener un rol por nombre desde la base de datos
    public Object[] obtenerPorNombreDB(String nombreRol) {
        return ejecutarSelectUno(getNomColumna(1), nombreRol);
    }

}

