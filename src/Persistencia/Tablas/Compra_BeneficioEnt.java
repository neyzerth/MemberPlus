package Persistencia.Tablas;
import Persistencia.Query;

public class Compra_BeneficioEnt extends Query {
    public Compra_BeneficioEnt() {
        super("compraBeneficio",
                "compra",
                "beneficio");
    }

    // Obtener un registro específico por sus IDs de compra y beneficio
    public Object[] obtenerCompra_BeneficioPorIdDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        return ejecutarSelectPorID(idCompra);
    }

    // Verificar si un registro ya existe en la base de datos por los IDs
    public boolean existeRegistroCompra_Beneficio(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra};
        return existeRegistro(getNomColumna(0), idCompra);
    }

    // Insertar un nuevo registro
    public boolean insertarCompra_BeneficioDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra, idBeneficio };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un registro
    public boolean eliminarCompra_BenefcioDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        String query = delete(idCompra);
        return ejecutarDelete(query);
    }

    // Método para obtener un registro por el ID de compra 
    public Object[] obtenerCompra_BeneficioPorNombreDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra };
        return ejecutarSelectUno(getNomColumna(0), idCompra);
    }
}
