package Persistencia.Tablas;


public class Compra_BeneficioEnt extends Query {
    public Compra_BeneficioEnt() {
        super("compraBeneficio",
                "compra",
                "beneficio");
    }

    // Obtener un registro específico por sus IDs de compra y beneficio
    public Object[] obtenerRegistroPorIdDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        return ejecutarSelectPorID(idCompra);
    }

    // Verificar si un registro ya existe en la base de datos por los IDs
    public boolean existeRegistro(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra};
        return existeRegistro(getNomColumna(0), idCompra);
    }

    // Insertar un nuevo registro
    public boolean insertarRegistroDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra, idBeneficio };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un registro
    public boolean eliminarRegistroDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        String query = delete(idCompra);
        return ejecutarDelete(query);
    }

    // Método para obtener un registro por el ID de compra 
    public Object[] obtenerPorIdsDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        return ejecutarSelectUno(getNomColumna(0), idCompra);
    }
}
