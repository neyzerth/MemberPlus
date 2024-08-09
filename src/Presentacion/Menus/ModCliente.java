
package Presentacion.Menus;

import Logica.Objetos.Cliente;
import Logica.Objetos.Nivel;
import Logica.Objetos.Persona;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

public class ModCliente extends Menu {
    private static Cliente cliente;

    public ModCliente() {
        super("Cliente", "Clientes");
    }

    public static void desplegarMenu() {
        ModCliente modCliente = new ModCliente();
        modCliente.menu();

    }

    @Override
    public void menuRegistrar() {
        Cuadro modificar = new Cuadro(Color.morado(" Registrar Clientes"));
        do {
            Texto.limpiarPantalla();

            modificar.imprimirCuadro();
            System.out.println(Color.amarillo(Color.negrita(" ¿Desea registrar un " + modSing + "?")));
            String opc = Leer.cadena(Color.cian(" SI[s] NO[n]"), "     (ENTER para salir)");

            if (!opc.equalsIgnoreCase("s")) {
                return;
            }

            // Metodo dentro de if despliega el metodo abstracto registrar y regresa un
            // booleano
            if (!registrar()) {
                System.out.println();
                Texto.esperarEnter(Color.rojo(Color.negrita(" Error al registrar Cliente")));
                break;
            }

            System.out.println();
            Texto.esperarEnter((" ") + Color.verde("Cliente") + Color.verde(" registrado con exito"));
            System.out.println();

            SubmodNivel modNivel = new SubmodNivel();

            Tarjeta tarjeta = new Tarjeta();
            modNivel.tabla();

            System.out.println();
            int idNivel = Leer.entero(Color.cian(Color.negrita(" > ID del nivel a solicitar: ")));

            tarjeta.nivel = Nivel.importarNiveles(idNivel);

            tarjeta = new Tarjeta(cliente, tarjeta.getNivel());
            if (!tarjeta.insertarTarjeta()) {
                System.out.println();
                Texto.esperarEnter(Color.rojo(Color.negrita(" Error al registrar Tarjeta")));
                break;
            }

            SubmodTarjeta modTarjeta = new SubmodTarjeta();
            System.out.println();
            Texto.esperarEnter(Color.amarillo(Color
                    .negrita("Se le cobrara el costo de apertura " + Texto.moneda(tarjeta.nivel.getCostoApertura()))));
            System.out.println();
            boolean conf = Leer.cadena(Color.verde(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            if (conf) {
                modTarjeta.tabla(tarjeta.getNumTarjeta());
                System.out.println();
                Texto.esperarEnter(Color.verde(Color.negrita(" Tarjeta registrada con exito")));
            } else {
                System.out.println(Color.rojo("Cancelando"));
                Texto.suspensivos();
                Persona.eliminarPersona(cliente.getIdPersona());
            }
        } while (true);

    }

    @Override
    public boolean registrar() {
        cliente = new Cliente();

        ModPersona.pedirDatos(cliente);

        try {

            if (cliente.insertarPersona()) {
                cliente.setIdPersona();
            }

            if (cliente.insertarCliente()) {
                ModCliente.cliente = Cliente.importarClientes(cliente.getIdCliente());
                return true;
            }

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(e.getMessage())));
        }
        return false;
    }



    @Override
    public boolean actualizar(int id) {

        if (!Cliente.validarCliente(id))
            return false;

        Cliente cliente = Cliente.importarClientes(id);
        ModPersona.datosPersona(cliente);
        try {

            cliente.setIdPersona(cliente.getIdPersona());
            if (!cliente.actualizarPersona())
                return false;

            cliente = new Cliente(id, cliente);

            if (!cliente.actualizarCliente())
                return false;

            tabla(id);
            return true;

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(e.getMessage())));
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Cliente.eliminarCliente(id);
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Completo"), Color.amarillo("Correo"),
                Color.amarillo("Telefono"));
        Cliente[] clientes = Cliente.importarClientes();

        for (Cliente cliente : clientes) {
            tabla.agregarFila(
                    cliente.getIdCliente(),
                    cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
                    cliente.getCorreo(),
                    cliente.getTelefono());
        }
        tabla.imprimirTablaSimple();
    }

    public boolean tabla(int id) {
        if (!Cliente.validarCliente(id))
            return false;

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Completo"),
                Color.amarillo("Fecha de nacimiento"), Color.amarillo("Direccion"), Color.amarillo("Telefono"),
                Color.amarillo("Correo"));
        Cliente cliente = Cliente.importarClientes(id);

        tabla.agregarFila(
                cliente.getIdCliente(),
                cliente.getNombre() + " " + cliente.getApellidoPa() + " " + cliente.getApellidoMa(),
                Texto.fecha(cliente.getFecNac()),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getCorreo());

        Tarjeta tarjeta = cliente.importarTarjeta();
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        System.out.println(Color.amarillo(Color.negrita("  Datos del cliente")));
        tabla.imprimirTablaSimple();

        System.out.println();
        System.out.println(Color.amarillo(Color.negrita(" Datos de membresia")));
        modTarjeta.tabla(tarjeta.getIdTarjeta());

        return true;

    }

}
