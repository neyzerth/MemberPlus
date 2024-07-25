package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.Color;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // ya quedo

        while (!exit) {
            System.out.println(
                    Color.morado(Color.invertido((Cuadro.espacio(6) + "Bienvenido :D" + (Cuadro.espacio(7) + "\n")))));
            System.out.println(Color.morado(Color.negrita("    > Menú principal <   ")));
            System.out.println(Color.blanco("╔────────────────────────╗"));
            System.out.println(
                    Color.blanco("║ ") + Color.morado("1. Módulo de Ventas") + Color.blanco("    ║"));
            System.out.println(
                    Color.blanco("║ ") + Color.morado("2. Módulo de Clientes") + Color.blanco("  ║"));
            System.out.println(
                    Color.blanco("║ ") + Color.morado("3. Módulo de Tarjeta") + Color.blanco("   ║"));
            System.out.println(
                    Color.blanco("║ ") + Color.rojo("4. Salir") + Color.blanco("               ║"));
            System.out.println(Color.blanco("╚════════════════════════╝" + "\n"));
            System.out.print(Color.cian("> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(Color.morado(Color.invertido("=== Módulo de Venta ===")));
                    // Añadir submenú si es necesario
                    break;
                case 2:
                    Color.limpiarPantalla();
                    moduloCliente(scanner);
                    break;
                case 3:
                    submenuUsuario(scanner);
                    break;
                case 4:
                    submenuTarjeta(scanner);
                    break;
                case 5:
                    System.out.println(Color.rojo("Saliendo del programa..."));
                    exit = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }

            System.out.println(); // Agrega una línea en blanco para separación
        }
        
    }

    private static void moduloCliente(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(6) + "> Módulo de Cliente < " + "\n")));
            System.out.println(Color.blanco("┌──────────────────────────────┐"));
            System.out
                    .println((Color.blanco("│ ") + Color.morado("1. Lista de clientes         ") + Color.blanco("│")));
            System.out
                    .println((Color.blanco("│ ") + Color.morado("2. Información de un cliente ") + Color.blanco("│")));
            System.out
                    .println((Color.blanco("│ ") + Color.morado("3. Modificar cliente         ") + Color.blanco("│")));
            System.out
                    .println((Color.blanco("│ ") + Color.morado("4. Eliminar cliente          ") + Color.blanco("│")));
            System.out.println((Color.blanco("│ ") + Color.rojo("5. Volver al menú principal  ") + Color.blanco("│")));
            System.out.println(Color.blanco("└──────────────────────────────┘" + "\n"));
            System.out.print(Color.cian("> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();
                    System.out.println(Color.amarillo(Color.negrita("> Lista de clientes")));
                    scanner.nextInt();
                    break;
                case 2:

                    break;
                case 3:
                    // Implementar funcionalidad aquí
                    break;
                case 4:
                    // Implementar funcionalidad aquí
                    break;
                case 5:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }

            System.out.println(); // Agrega una línea en blanco para separación
        }
    }

    private static void submenuUsuario(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.cian("↳ Módulo de Usuario"));
            System.out.println(Color.verde("1. Lista de usuarios"));
            System.out.println(Color.verde("2. Información de usuario específico"));
            System.out.println(Color.verde("3. Modificar usuario"));
            System.out.println(Color.verde("4. Eliminar usuario"));
            System.out.println(Color.rojo("5. Volver al menú principal"));
            System.out.print(Color.azul("Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Implementar funcionalidad aquí
                    break;
                case 2:
                    // Implementar funcionalidad aquí
                    break;
                case 3:
                    // Implementar funcionalidad aquí
                    break;
                case 4:
                    // Implementar funcionalidad aquí
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }

            System.out.println(); // Agrega una línea en blanco para separación
        }
    }

    private static void submenuTarjeta(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.cian("=== Módulo de Tarjeta ==="));
            System.out.println(Color.verde("1. Submódulo de movimientos"));
            System.out.println(Color.verde("2. Submódulo de nivel de tarjetas"));
            System.out.println(Color.verde("3. Submódulo de beneficios"));
            System.out.println(Color.rojo("4. Volver al menú principal"));
            System.out.print(Color.azul("Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Implementar funcionalidad aquí
                    break;
                case 2:
                    // Implementar funcionalidad aquí
                    break;
                case 3:
                    // Implementar funcionalidad aquí
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }

            System.out.println(); // Agrega una línea en blanco para separación
        }
    }
}