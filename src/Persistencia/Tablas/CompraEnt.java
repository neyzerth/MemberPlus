package Persistencia.Tablas;

public class CompraEnt extends Query{
    public CompraEnt() {
        super("compra",
                "idCompra",
                "fecha",
                "porcPunto",
                "descuento",
                "tarjeta",
                "total"
                );
    }
    
}
