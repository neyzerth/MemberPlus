package Presentacion;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println(Color.cian("↳ Menú Principal"));
            System.out.println(Color.verde("1. Módulo de Venta"));
            System.out.println(Color.verde("2. Módulo de Cliente"));
            System.out.println(Color.verde("3. Módulo de Usuario"));
            System.out.println(Color.verde("4. Módulo de Tarjeta"));
            System.out.println(Color.rojo("5. Salir"));
            System.out.print(Color.azul("Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(Color.morado("=== Módulo de Venta ==="));
                    // Añadir submenú si es necesario
                    break;
                case 2:
                    submenuCliente(scanner);
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

        scanner.close();
    }

    private static void submenuCliente(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.cian("=== Módulo de Cliente ==="));
            System.out.println(Color.verde("1. Lista de clientes"));
            System.out.println(Color.verde("2. Información de cliente específico"));
            System.out.println(Color.verde("3. Modificar cliente"));
            System.out.println(Color.verde("4. Eliminar cliente"));
            System.out.println(Color.rojo("5. Volver al menú principal"));
            System.out.print(Color.azul("Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    //listarClientes();//
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

    private static void submenuUsuario(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.cian("=== Módulo de Usuario ==="));
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