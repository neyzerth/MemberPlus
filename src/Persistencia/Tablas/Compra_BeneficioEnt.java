package Persistencia.Tablas;
import Persistencia.Query;

public class Compra_BeneficioEnt extends Query {
    public Compra_BeneficioEnt() {
        super("compra_beneficio",
                "compra",
                "beneficio");
    }

    // Insertar un nuevo registro
    public boolean insertarCompra_BeneficioDB(int idCompra, int idBeneficio) {
        Object[] valores = { idCompra, idBeneficio };
        String query = insertConId(valores);
        return ejecutarInsert(query);
    }

}
