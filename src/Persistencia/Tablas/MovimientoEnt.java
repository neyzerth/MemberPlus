package Persistencia.Tablas;

public class MovimientoEnt extends Query {

    public MovimientoEnt() {
        super("movimiento",
                "idMovimiento",
                "fecMovimiento",
                "estado",
                "comentario",
                "usuario",
                "tarjeta",
                "tipoMovimiento");
    }   
}
