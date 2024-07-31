package Logica.Objetos;

import java.sql.Date;

import Persistencia.Tablas.ClienteEnt;

public class Cliente extends Persona {
    // ATRIBUTOS
    private int idCliente;

    // CONSTRUCTORES
    public Cliente(){}

    public Cliente(int idCliente, Persona persona){
        super(persona);
        this.idCliente = idCliente;
    }

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
    public static Cliente importarClientes(Object [] datos ){
        Persona persona = new Persona((int) datos[1]);

        Cliente cliente = new Cliente(
            (int) datos[0],
            persona
        );            
        return cliente;
    }

    public static Cliente [] importarClientes(){
        ClienteEnt clientesBd = new ClienteEnt();
        Cliente [] clientes = new Cliente [clientesBd.obtenerCantRegistros()];
        Object [][] datos = clientesBd.ejecutarSelect();

        for (int i = 0; i < clientes.length; i++) {
            Object[] dato = datos[i];
            
            clientes[i] = importarClientes((int) dato[0]);      
        }
        return clientes;
    }
    
    public static Cliente importarClientes(int id){
        ClienteEnt clientesBd = new ClienteEnt();

        Object [] datos = clientesBd.ejecutarSelectPorID(id);

        return importarClientes(datos);  

    }
    public boolean insertarCliente(){
        ClienteEnt cliente = new ClienteEnt();
        if(validarPersona(this.getIdPersona()))
            return cliente.insertarClienteDB(this.getIdPersona());

        return false;
    }
    public boolean actualizarCliente(){
        ClienteEnt cliente = new ClienteEnt();
        if(validarPersona(this.getIdPersona()))
            return cliente.actualizarClienteDB(this.idCliente, this.getIdPersona());

        return false;
    }

    public static boolean validarCliente(int id){
        ClienteEnt cliente = new ClienteEnt();
        return cliente.existeRegistro(id);
    }
    

    // GETTERS AND SETTERS
    public int getIdCliente() {
        if(this.idCliente > 0)
            return this.idCliente;
        
        ClienteEnt clienteBd = new ClienteEnt();

        Object [] datos = clienteBd.ejecutarSelectPorAtributos(
            this.getIdPersona()
        );
        Cliente cliente = Cliente.importarClientes(datos);

        this.idCliente = cliente.getIdCliente();

        return this.idCliente;
    }
    // elimine el setter de idCliente
}