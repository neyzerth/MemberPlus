package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Venta {
    public static void menu(Scanner scanner) {
        Texto.limpiarPantalla();
        boolean back = false;

        while (!back) {
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Venta <")));

            Cuadro venta = new Cuadro(
                    Color.morado("Numero de tarjeta"),
                    Color.morado("Recoger el total"),
                    Color.rojo("Volver al menú principal"));
            venta.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro numTarjeta = new Cuadro(
                            Color.amarillo("> Numero de tarjeta"));

                    numTarjeta.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro recogerTotal = new Cuadro(
                            Color.amarillo("> Recoger el total"));
                    recogerTotal.imprimirCuadro();

                    System.out.print("> $");
                    scanner.nextInt();
                    break;
                case 3:
                    Texto.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
    
}
