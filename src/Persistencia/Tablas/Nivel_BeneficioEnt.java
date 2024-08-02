package Persistencia.Tablas;

public class Nivel_BeneficioEnt extends Query {
    public Nivel_BeneficioEnt() {
        super("nivel_Beneficio",
                "nivel",
                "beneficio");
    }

    // Obtener un registro específico por el ID de nivel 
    public Object[] obtenerRegistroPorIdDB(int nivel, int beneficio) {
        Object[] valores = { nivel};
        return ejecutarSelectPorID(nivel);
    }

    // Verificar si un registro ya existe en la base de datos por los ID nivel
    public boolean existeRegistro(int nivel, int beneficio) {
        Object[] valores = { nivel };
        return existeRegistro(getNomColumna(0), nivel);
    }

    // Insertar un nuevo registro
    public boolean insertarRegistroDB(int nivel, int beneficio) {
        Object[] valores = { nivel, beneficio };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un registro
    public boolean eliminarRegistroDB(int nivel, int beneficio) {
        Object[] valores = { nivel };
        String query = delete(nivel);
        return ejecutarDelete(query);
    }

    // Método para obtener un registro por el ID de nivel 
    public Object[] obtenerPorIdsDB(int nivel, int beneficio) {
        Object[] valores = { nivel };
        return ejecutarSelectUno(getNomColumna(0), nivel);
    }
}
