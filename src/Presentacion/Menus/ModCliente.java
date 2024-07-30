package Presentacion.Menus;

import Logica.Objetos.Cliente;
import Logica.Objetos.Persona;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

public class ModCliente {
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Clientes <")));

            Cuadro cliente = new Cuadro(
                Color.morado("Lista de clientes"),
                Color.morado("Información de un cliente"),
                Color.morado("Registrar cliente"),
                Color.morado("Modificar cliente"),
                Color.morado("Eliminar cliente"),
                Color.rojo("Volver al menu principal")
            );
            cliente.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian("> Seleccione una opción: "));
            switch (opcion) {
                case 1: verClientes();
                    break;
                case 2: verCliente();
                    break;
                case 3: registrarCliente();
                    break;
                case 4: actualizarCliente();
                    break;
                case 5:
                    Texto.limpiarPantalla();

                    Cuadro eliminarCli = new Cuadro(
                            Color.amarillo("> Eliminar Cliente"));
                    eliminarCli.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                    Texto.esperar(1);
            }
        }
    }

    private static void verClientes(){
        Texto.limpiarPantalla();

        Cuadro listaCli = new Cuadro(Color.amarillo("> Lista de clientes"));
        listaCli.imprimirCuadro();
        tablaClientes();

        Texto.esperarEnter();
    }
    private static void verCliente(){
        Texto.limpiarPantalla();

        Cuadro infoCli = new Cuadro(
                Color.amarillo("> Informacion de un cliente"));
        infoCli.imprimirCuadro();

        tablaClientes();

        int id = Texto.leerInt("> ID del cliente a ver: ");
        tablaClientes(id);

        Texto.esperarEnter();
    }

    public static void registrarCliente(){
        Texto.limpiarPantalla();

        Cuadro modificarCli = new Cuadro(Color.amarillo("> Registrar de cliente"));
        modificarCli.imprimirCuadro();
        
        Persona persona = ModPersona.datosPersona();
        Cliente cliente = new Cliente();
        do{
            //try {

                if(persona.insertarPersona()){
                    persona.setIdPersona();
                    cliente = new Cliente(0, persona);
                }

                if( cliente.insertarCliente()){
                    tablaClientes(cliente.getIdCliente());
                    Texto.esperarEnter("Cliente actualizar con exito");
                } else
                    Texto.esperarEnter("Error al actualizar");

            //} catch (Exception e) {
            //    Texto.esperarEnter("DATO NO VALIDO");
            //}
        } while (false);
    }
    public static void actualizarCliente(){
        Texto.limpiarPantalla();

        Cuadro modificarCli = new Cuadro(Color.amarillo("> Modificar informacion de cliente"));
        modificarCli.imprimirCuadro();
        tablaClientes();

        int id = Texto.leerInt("> ID del cliente a modificar: ");

        Cliente cliente = tablaClientes(id);
        if(cliente != null)
            return;
        
        Persona persona = ModPersona.datosPersona();
        do{
            try {

                if(!persona.actualizarPersona())
                    return;

                persona.setIdPersona();
                cliente = new Cliente(0, persona);

                if( cliente.insertarCliente()){
                    tablaClientes(cliente.getIdCliente());
                    Texto.esperarEnter("Cliente actualizar con exito");
                } else
                    Texto.esperarEnter("Error al actualizar");
                    
            } catch (Exception e) {
                Texto.esperarEnter("DATO NO VALIDO");
            }
        } while (false);
    }

    private static Cliente tablaClientes(int id) {
        Tabla tabla = new Tabla("ID", "Nombre Completo","Fecha de nacimiento", "Direccion", "Telefono", "Correo");
        Cliente cliente = Cliente.importarClientes(id);
        if (!Cliente.validarCliente(id)) {
            Texto.esperarEnter("No existe Cliente con ID : " + id);
            return null;
        }

        tabla.agregarFila(
            cliente.getIdCliente(),
            cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
            cliente.getFecNac(),
            cliente.getCalle() + " " + cliente.getNumExt() + " " + cliente.getNumInt() + " " + cliente.getColonia() + " " + cliente.getCp(),
            cliente.getTelefono(),
            cliente.getCorreo()
        );
        tabla.imprimirTablaSimple();

        return cliente;
    }

    private static Cliente[] tablaClientes() {
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

        return clientes;
    }
}
