package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Principal {
    public static void menu() {
        Texto.limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        do {
            System.out.println(Color.morado(Color.invertido(
                (Texto.espacio(6) + "  Bienvenido :D" + (Texto.espacio(8) + "\n")))));
            System.out.println(Color.morado(Color.negrita("      > Menú principal <   ")));

            Cuadro principal = new Cuadro(
                Color.morado("Módulo de Ventas"),
                Color.morado("Módulo de Clientes"),
                Color.morado("Modulo de Usuario"),
                Color.morado("Módulo de Tarjeta"),
                Color.rojo("Salir")
            );
            principal.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian((Texto.espacio(1) + "> Seleccione una opción: ")));

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: Venta.menu(scanner);
                    break;
                case 2: Cliente.menu(scanner);
                    break;
                case 3: Usuario.menu(scanner);
                    break;
                case 4: Tarjeta.menu(scanner);
                    break;

                case 5: //SALIR
                    System.out.println(Color.rojo("Saliendo del programa..."));
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo. "));
                    break;
            }
        } while (!salir);
    }
}