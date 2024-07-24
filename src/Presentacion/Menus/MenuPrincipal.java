package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Formato.Color;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Color.cian("-- Menú Principal --"));
        System.out.println(Color.blanco("╔──────────────────────────────────────╗"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("1. Módulo de Ventas") + Color.blanco("                  ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("2. Módulo de Clientes") + Color.blanco("                ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("3. Módulo de Tarjeta") + Color.blanco("                 ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("4. Salir") + Color.blanco("                             ║"));
        System.out.println(Color.blanco("╚══════════════════════════════════════╝" + "\n"));
        System.out.print(Color.azul("Seleccione una opción: "));

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println(Color.cian("-- Módulo de Ventas --"+ "\n"));

                break;

            case 2:
                System.out.println(Color.cian("-- Módulo de Clientes --"+ "\n"));

                break;

            case 3:
                System.out.println(Color.cian("-- Módulo de Tarjeta --"+ "\n"));

                break;

            case 4:
                // el usuario sale//

                break;

            default:
                System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."+ "\n"));

                break;
        }
    }
}