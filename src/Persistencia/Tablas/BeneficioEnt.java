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

}
