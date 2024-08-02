package Logica.Objetos;

import java.sql.Date;
import Persistencia.Tablas.ClienteEnt;
import Persistencia.Tablas.TarjetaEnt;

//La clase cliente representa a un cliente que hereda de la clase persona
//contiene informacion adicional especifica del cliente como su identificador unico

public class Cliente extends Persona {
    // ATRIBUTOS
    private int idCliente;

    // CONSTRUCTORES
    public Cliente(){}

    public Cliente(int idCliente, Persona persona){
        super(persona);
        this.idCliente = idCliente;
    }

    //Constructor con atributos como en Base de datos
    public Cliente(int idCliente, int IdPersona) {
        super(IdPersona);
        this.idCliente = idCliente;
    }

    //Constructor con todos los atributos y los de persona
    public Cliente( int idCliente, int idPersona, String nombre, String apellidoPa, String apellidoMa,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
        Date fecNac
    ) {
        super(idPersona, nombre, apellidoMa, apellidoPa, fecNac, colonia, calle, numExt, numInt, telefono, correo, cp);
        this.idCliente = idCliente;
    }

    // ------------- COMUNICACION A PERSISTENCIA --------------
    // METODOS ESTATICOS
    public static Cliente importarClientes(Object [] datos ){
        Persona persona = new Persona((int) datos[1]); //[1] es el segundo atributo, llave foranea de persona en tabla cliente
        
        Cliente cliente = new Cliente(
            (int) datos[0], //el primer dato siempre sera el ID PK
            persona
        );            
        return cliente;
    }

    //IMPORTAR TODOS LOS REGISTROS DE CLIENTES DE BD
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

    public  Tarjeta importarTarjeta(){
        TarjetaEnt tarjetaBd = new TarjetaEnt();
        Object [] datos = tarjetaBd.obtenerTarjetaPorCliente(this.getIdCliente());

        return Tarjeta.importarTarjeta(datos);
    }

    //CRUD
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
    public static boolean eliminarCliente(int id){
        ClienteEnt clienteBd = new ClienteEnt();
        return clienteBd.eliminarClienteDB(id);
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
}