package Persistencia.Tablas;

public class ClienteENT extends Query {
    public ClienteENT() {
        super("cliente",
                "idCliente",
                "persona"); 
    }

    // Obtener un cliente específico por su ID
    public Object[] obtenerClientePorIdDB(int idCliente) {
        return ejecutarSelectPorID(idCliente);
    }

    // Verificar si un cliente ya existe en la base de datos por persona
    public boolean existeCliente(int idPersona) {
        return existeRegistro(getNomColumna(1), idPersona);
    }

    // Actualizar un cliente
    public boolean actualizarClienteDB(int idCliente, int idPersona) {
        Object[] valores = { idCliente, idPersona };
        String query = update(valores, idCliente);
        return ejecutarUpdate(query);
    }

    // Insertar un cliente
    public boolean insertarClienteDB(int idCliente, int idPersona) {
        Object[] valores = { idCliente, idPersona };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un cliente
    public boolean eliminarClienteDB(int idCliente) {
        String query = delete(idCliente);
        return ejecutarDelete(query);
    }

    // Método para obtener un cliente por persona desde la base de datos
    public Object[] obtenerPorPersonaDB(int idPersona) {
        return ejecutarSelectUno(getNomColumna(1), idPersona);
    }
}
