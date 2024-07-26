package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Cliente {
    public static void menu(Scanner scanner) {
        boolean back = false;
        while (!back) {
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
            System.out.print(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro listaCli = new Cuadro(
                            Color.amarillo("> Lista de clientes"));
                    listaCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoCli = new Cuadro(
                            Color.amarillo("> Informacion de un cliente"));
                    infoCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro modificarCli = new Cuadro(
                            Color.amarillo("> Modificar informacion de cliente"));
                    modificarCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarCli = new Cuadro(
                            Color.amarillo("> Eliminar Cliente"));
                    eliminarCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 5:
                    Texto.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
}
