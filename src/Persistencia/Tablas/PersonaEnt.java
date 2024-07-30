package Persistencia.Tablas;

import java.sql.Date;

import Persistencia.Query;

public class PersonaEnt extends Query {
    
    public PersonaEnt() {
        super("persona",
                "idPersona",
                "nombre",
                "apellidoPa",
                "apellidoMa",
                "fecNac",
                "colonia",
                "calle",
                "numExt",
                "numInt",
                "cp",
                "telefono",
                "correo"
            );
    }

    // Obtener una persona específica por su ID
    public Object[] obtenerPersonaPorIdDB(int idPersona) {
        return ejecutarSelectPorID(idPersona);
    }

    public Object[] obtenerPersonaPorAtributos(
        String nombre, String apellidoPa, String apellidoMa, Date fecNac,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo
    ) {
        return ejecutarSelectPorAtributos(
            nombre, apellidoPa, apellidoMa, fecNac,
            colonia, calle, numExt, numInt, cp, telefono, correo
        );
    }

   // Verificar si una persona ya existe en la base de datos por nombre, apellidos y fecha de nacimiento
    public boolean existePersona(String nombre, String apellidoPa, String apellidoMa, Date fecNac) {
    return existeRegistro(getNomColumna(1), nombre) && 
    existeRegistro(getNomColumna(2), apellidoPa) &&
    existeRegistro(getNomColumna(3), apellidoMa) &&
    existeRegistro(getNomColumna(4), fecNac);
}

    // Actualizar una persona
    public boolean actualizarPersonaDB(
        int idPersona, String nombre, String apellidoPa, String apellidoMa, Date fecNac,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo
    ) {
        Object[] valores = { idPersona, nombre, apellidoPa, apellidoMa, fecNac, colonia, calle, numExt, numInt, cp,
                telefono, correo };
        String query = update(valores, idPersona);
        return ejecutarUpdate(query);
    }

    // Insertar una persona
    public boolean insertarPersonaDB(String nombre, String apellidoPa, String apellidoMa, Date fecNac,
            String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo
        ) {
        Object[] valores = { 
            nombre, apellidoPa, apellidoMa, fecNac,
            colonia, calle, numExt, numInt, cp, telefono, correo 
        };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar una persona
    public boolean eliminarPersonaDB(int idPersona) {
        String query = delete(idPersona);
        return ejecutarDelete(query);
    }

    // Método para obtener una persona por nombre y apellidos desde la base de datos
    public Object[] obtenerPorNombreYApellidosDB(String nombre, String apellidoPa, String apellidoMa) {
        Object[] valores = { nombre, apellidoPa, apellidoMa };
        return ejecutarSelectUno(getNomColumna(1), valores);
    }
}
