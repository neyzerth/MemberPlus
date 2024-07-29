package Logica.Objetos;

import java.util.Date;

import Persistencia.Tablas.ClienteEnt;

public class Cliente extends Persona {
    // ATRIBUTOS
    private int idCliente;

    // CONSTRUCTORES
    public Cliente(int idCliente, int IdPersona) {
        super(IdPersona);
        this.idCliente = idCliente;
    }

    public Cliente( int idCliente, int id, String nombre, String apellidoPa, String apellidoMa,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
        Date fecNac
    ) {
        // El constructor le faltaban atributos, y el id de persona se transfiere
        super(id, nombre, apellidoMa, apellidoPa, fecNac, colonia, calle, numExt, numInt, telefono, correo, cp);
        this.idCliente = idCliente;
    }
    // METODOS

    public static Cliente [] importarClientes(){
        ClienteEnt clientesBd = new ClienteEnt();
        Cliente [] clientes = new Cliente [clientesBd.obtenerCantRegistros()];

        Object [][] datos = clientesBd.ejecutarSelect();
        for (int i = 0; i < clientes.length; i++) {
            Object[] dato = datos[i];
            clientes[i] = new Cliente(
                (int) dato[0],
                (int) dato[1]
            );            
        }
        return clientes;
    }
    
    public static Cliente importarClientes(int id){
        ClienteEnt clientesBd = new ClienteEnt();

        Object [] datos = clientesBd.ejecutarSelectPorID(id);
        Cliente cliente = new Cliente(
            (int) datos[0],
            (int) datos[1]
        );            

        return cliente;
    }

    public static boolean validarCliente(int id){
        ClienteEnt cliente = new ClienteEnt();
        return cliente.existeRegistro(id);
    }
    

    // GETTERS AND SETTERS
    public int getIdCliente() {
        return this.idCliente;
    }
    // elimine el setter de idCliente
}