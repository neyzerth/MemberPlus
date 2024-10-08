package Persistencia.Tablas;

import Persistencia.Query;

public class UsuarioEnt extends Query {
    
    public UsuarioEnt() {
        super("usuario",
                "idUsuario",
                "nombreUsuario",
                "contrasena",
                "rfc",
                "persona",
                "rol");
    }

    // Verificar si un usuario ya existe en la base de datos por nombre de usuario y RFC
    public boolean existeUsuario(String nombreUsuario, String contrasena) {
        return existeRegistro(getColumnas(1, 2), nombreUsuario, contrasena);
    }

    // Obtener un usuario por su ID
    public Object[] obtenerUsuarioPorIdDB(int idUsuario) {
        return ejecutarSelectPorID(idUsuario);
    }
    // Obtener un usuario por su ID
    public Object[] obtenerUsuarioPorSesion(String nomUsuario, String contrasena) {
        return ejecutarSelectUno(selectUno(getNomColumna(1), getNomColumna(2)), nomUsuario, contrasena);
    }

    // Actualizar un usuario
    public boolean actualizarUsuarioDB(int idUsuario, String nombreUsuario, String contrasena, String rfc, int idPersona, int idRol) {
        Object[] valores = { idUsuario, nombreUsuario, contrasena, rfc, idPersona, idRol };
        String query = update(valores, idUsuario);
        return ejecutarUpdate(query);
    }

    // Insertar un usuario solo si el RFC no existe previamente
    public boolean insertarUsuarioDB(String nombreUsuario, String contrasena, String rfc, int idPersona, int idRol) {
            String query = insert(nombreUsuario, contrasena, rfc, idPersona, idRol);
            return ejecutarInsert(query);
    }

    // Eliminar un usuario
    public boolean eliminarUsuarioDB(int idUsuario) {
        String query = delete(idUsuario);
        return ejecutarDelete(query);
    }

    // Método para obtener un usuario por nombre de usuario desde la base de datos
    public Object[] obtenerPorNombreUsuarioDB(String nombreUsuario) {
        return ejecutarSelectUno(getNomColumna(2), nombreUsuario);
    }

}
