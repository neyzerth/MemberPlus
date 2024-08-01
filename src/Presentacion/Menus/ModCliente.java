package Presentacion.Menus;

import Logica.Objetos.Cliente;
import Logica.Objetos.Persona;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

public class ModCliente extends Menu{

    public ModCliente(){
        super("Cliente", "Clientes");
    }

    public static void desplegarMenu() {
        ModCliente modCliente = new ModCliente();
        modCliente.menu();
    }

    @Override
    public boolean registrar(){

        Persona persona = ModPersona.datosPersona();
        Cliente cliente = new Cliente();
        try {

            if(persona.insertarPersona()){
                persona.setIdPersona();
                cliente = new Cliente(0, persona);
            }

            if( cliente.insertarCliente()){
                Cliente.importarClientes(cliente.getIdCliente());
                return true;
            }

        } catch (Exception e) {
            Texto.esperarEnter("DATO NO VALIDO");
        }
        return false;
    }

    @Override
    public boolean actualizar(int id){
        Cliente cliente = Cliente.importarClientes(id);
        if(cliente == null)
            return false;
        
        Persona persona = ModPersona.datosPersona();
        try {
            
            persona.setIdPersona(cliente.getIdPersona());
            if(!persona.actualizarPersona())
                return false;

            cliente = new Cliente(id, persona);

            if( cliente.actualizarCliente()){
                tabla(id);
                return true;
            }
                
        } catch (Exception e) {
            Texto.esperarEnter("DATO NO VALIDO");
        }
        return false;
    }

    @Override
    public boolean eliminar(int id){
        return Cliente.eliminarCliente(id);
    }

    @Override
    public void tabla(){
        Tabla tabla = new Tabla("ID", "Nombre Completo", "Correo", "Telefono");
        Cliente[] clientes = Cliente.importarClientes();

        for (Cliente cliente : clientes) {
            tabla.agregarFila(
                cliente.getIdCliente(),
                cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
                cliente.getCorreo(),
                cliente.getTelefono()
            );
        }
        tabla.imprimirTablaSimple();
    }

    public boolean tabla(int id) {
        Tabla tabla = new Tabla("ID", "Nombre Completo","Fecha de nacimiento", "Direccion", "Telefono", "Correo");
        Cliente cliente = Cliente.importarClientes(id);

        if (!Cliente.validarCliente(id))
            return false;

        tabla.agregarFila(
            cliente.getIdCliente(),
            cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
            cliente.getFecNac(),
            cliente.getCalle() + " " + cliente.getNumExt() + " " + cliente.getNumInt() + " " + cliente.getColonia() + " " + cliente.getCp(),
            cliente.getTelefono(),
            cliente.getCorreo()
        );

        tabla.imprimirTablaSimple();
        return true;

    }



}
