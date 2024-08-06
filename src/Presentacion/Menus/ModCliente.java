package Presentacion.Menus;

import Logica.Objetos.Cliente;
import Logica.Objetos.Nivel;
import Logica.Objetos.Persona;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

public class ModCliente extends Menu{
    private static Cliente cliente;

    public ModCliente(){
        super("Cliente", "Clientes");
    }

    public static void desplegarMenu() {
        ModCliente modCliente = new ModCliente();
        modCliente.menu();
    }

    @Override
    public void menuRegistrar(){
        Texto.limpiarPantalla();

        Cuadro modificar = new Cuadro(Color.morado(" Registrar Clientes" ));
        modificar.imprimirCuadro();
        
        //Metodo dentro de if despliega el metodo abstracto registrar y regresa un booleano
        if(!registrar()){
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" Error al registrar Cliente")));
            return;
        }

        System.out.println();
        Texto.esperarEnter((" ")+Color.verde("Cliente") + Color.verde(" registrado con exito"));
        System.out.println();

        SubmodNivel modNivel = new SubmodNivel();

        Tarjeta tarjeta = new Tarjeta(); 
        modNivel.tabla();

        System.out.println();
        int idNivel = Leer.entero(Color.cian(Color.negrita(" > ID del nivel a solicitar: ")));

        tarjeta.nivel = Nivel.importarNiveles(idNivel);

        tarjeta = new Tarjeta(cliente, tarjeta.getNivel());
        if(!tarjeta.insertarTarjeta()){
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" Error al registrar Tarjeta")));
            return;
        }
        
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        modTarjeta.tabla(tarjeta.getNumTarjeta());

        System.out.println();
        Texto.esperarEnter(Color.verde(Color.negrita(" Tarjeta registrada con exito")));
        //Si se inserto solo faltaria mostrar la tarjeta 
    }

    @Override
    public boolean registrar(){

        Persona persona = ModPersona.datosPersona();
        try {

            if(persona.insertarPersona()){
                persona.setIdPersona();
                cliente = new Cliente(0, persona);
            }

            if( cliente.insertarCliente()){
                ModCliente.cliente = Cliente.importarClientes(cliente.getIdCliente());
                return true;
            }

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" DATO NO VALIDO")));
        }
        return false;
    }
    @Override
    public void menuActualizar(){
        Cuadro actualizar = new Cuadro(Color.morado(" Modificar informacion de Cliente"));
        Texto.limpiarPantalla();

        actualizar.imprimirCuadro();
        tabla();

        System.out.println();
        int id = Leer.entero(Color.cian(" > ID del Cliente a modificar: "));

        if(!tabla(id)) { 
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" No existe Cliente con ID " + id + "...")));
            return;
        }

        //Metodo dentro de if despliega el metodo abstracto actualizar y regresa un booleano
        if(!actualizar(id)){
            System.out.println();
            Texto.esperarEnter(Color.rojo(" Error al actualizar Cliente"));
            return;
        }

        System.out.println();
        Texto.esperarEnter(Color.verde(" Cliente actualizado con exito"));
        
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

            if(!cliente.actualizarCliente())
                return false;
            
            tabla(id);
            return true;
            
                
        } catch (Exception e) {
            Texto.esperarEnter(Color.rojo(Color.negrita(" DATO NO VALIDO")));
        }
        return false;
    }

    @Override
    public boolean eliminar(int id){
        return Cliente.eliminarCliente(id);
    }

    @Override
    public void tabla(){
        tabla = new Tabla(Color. amarillo("ID"),Color.amarillo("Nombre Completo"),Color.amarillo("Correo"),Color.amarillo("Telefono"));
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
        if (!Cliente.validarCliente(id))
            return false;
            
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Completo"),Color.amarillo("Fecha de nacimiento"), Color.amarillo("Direccion"), Color.amarillo("Telefono"), Color.amarillo("Correo"));
        Cliente cliente = Cliente.importarClientes(id);

        tabla.agregarFila(
            cliente.getIdCliente(),
            cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
            Texto.fecha(cliente.getFecNac()),
            cliente.getCalle() + " " + cliente.getNumExt() + " " + cliente.getNumInt() + " " + cliente.getColonia() + " " + cliente.getCp(),
            cliente.getTelefono(),
            cliente.getCorreo()
        );

        Tarjeta tarjeta = cliente.importarTarjeta();
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        
        tabla.imprimirTablaSimple();

        System.out.println();
        System.out.println(Color.amarillo(Color.negrita("  Datos de membresia")));
        modTarjeta.tabla(tarjeta.getIdTarjeta());
        return true;

    }



}
