package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Cliente {
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Clientes <")));

            Cuadro cliente = new Cuadro(
                Color.morado("Lista de clientes"),
                Color.morado("Información de un cliente"),
                Color.morado("Modificar cliente"),
                Color.morado("Eliminar cliente"),
                Color.rojo("Volver al menu principal")
            );
            cliente.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian("> Seleccione una opción: "));
            switch (opcion) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro listaCli = new Cuadro(
                            Color.amarillo("> Lista de clientes"));
                    listaCli.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoCli = new Cuadro(
                            Color.amarillo("> Informacion de un cliente"));
                    infoCli.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro modificarCli = new Cuadro(
                            Color.amarillo("> Modificar informacion de cliente"));
                    modificarCli.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarCli = new Cuadro(
                            Color.amarillo("> Eliminar Cliente"));
                    eliminarCli.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                    Texto.esperar(1);
            }
        }
    }
}
