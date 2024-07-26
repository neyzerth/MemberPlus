package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Usuario {
    public static void menu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Usuario <")));

            Cuadro usuario = new Cuadro(
                    Color.morado("Lista de usuarios"),
                    Color.morado("Información de usuario"),
                    Color.morado("Modificar usuario"),
                    Color.morado("Eliminar usuario"),
                    Color.rojo("Volver al menú principal"));
            usuario.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro listaUsua = new Cuadro(
                            Color.amarillo("> Lista de usuarios"));
                    listaUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    infoUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro modificarUsua = new Cuadro(
                            Color.amarillo("> Modificar informacion del usuario"));
                    modificarUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    eliminarUsua.imprimirCuadro();

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
