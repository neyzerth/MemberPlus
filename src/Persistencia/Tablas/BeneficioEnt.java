package Persistencia.Tablas;

public class BeneficioEnt extends Query {
    public BeneficioEnt() {
        super("beneficio",
                "idBeneficio",
                "nombre",
                "fecInicio",
                "fecVen",
                "porcentajePuntos",
                "porcentajeCashBack");
    }

    // Obtener un beneficio específico por su ID
    public Object[] obtenerBeneficioPorIdDB(int idBeneficio) {
        return ejecutarSelectPorID(idBeneficio);
    }

    // Verificar si un beneficio ya existe en la base de datos por nombre
    public boolean existeBeneficio(String nombreBeneficio) {
        return existeRegistro(getNomColumna(1), nombreBeneficio);
    }

    // Actualizar un beneficio
    public boolean actualizarBeneficioDB(int idBeneficio, String nombre, String fecInicio, String fecVen, int porcentajePuntos, int porcentajeCashBack) {
        Object[] valores = { idBeneficio, nombre, fecInicio, fecVen, porcentajePuntos, porcentajeCashBack };
        String query = update(valores, idBeneficio);
        return ejecutarUpdate(query);
    }

    // Insertar un beneficio
    public boolean insertarBeneficioDB(int idBeneficio, String nombre, String fecInicio, String fecVen, int porcentajePuntos, int porcentajeCashBack) {
        Object[] valores = { idBeneficio, nombre, fecInicio, fecVen, porcentajePuntos, porcentajeCashBack };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un beneficio
    public boolean eliminarBeneficioDB(int idBeneficio) {
        String query = delete(idBeneficio);
        return ejecutarDelete(query);
    }

    // Método para obtener un beneficio por nombre desde la base de datos
    public Object[] obtenerPorNombreDB(String nombreBeneficio) {
        return ejecutarSelectUno(getNomColumna(1), nombreBeneficio);
    }
}

