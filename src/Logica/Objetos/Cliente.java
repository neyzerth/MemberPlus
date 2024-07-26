package Logica;

import java.util.Date;

public class Cliente extends Persona {
    // ATRIBUTOS
    private int idCliente;

    // CONSTRUCTORES
    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellidoPa, String apellidoMa,
            String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
            Date fecNac, int idCliente) {
        // El constructor le faltaban atributos, y el id de persona se transfiere
        super(id, nombre, apellidoMa, apellidoPa, colonia, calle, numExt, numInt, telefono, correo, cp, fecNac);
        this.idCliente = idCliente;
    }
    // METODOS

    // GETTERS AND SETTERS
    public int getIdCliente() {
        return this.idCliente;
    }
    // elimine el setter de idCliente
}