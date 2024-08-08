package Persistencia.Tablas;
import Persistencia.Query;

public class Nivel_BeneficioEnt extends Query {
    public Nivel_BeneficioEnt() {
        super("nivel_Beneficio",
                "nivel",
                "beneficio");
    }


    public Object[][] obtenerBeneficioPorIdNivel(int nivel){
        BeneficioEnt beneficioEnt = new BeneficioEnt();
        Object [][] beneficios = ejecutarInnerJoin(
            beneficioEnt, 
            getNomColumna(1), 
            beneficioEnt.getNomColumna(0),
            getNomColumna(0),
            nivel
        );


        return beneficios;
    }

    // Insertar un nuevo registro
    public boolean insertarNivel_BeneficioDB(int nivel, int beneficio) {
        Object[] valores = { nivel, beneficio };
        String query = insertConId(valores);
        return ejecutarInsert(query);
    }
}
