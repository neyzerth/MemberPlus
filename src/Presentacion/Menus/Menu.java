package Presentacion.Menus;

import java.util.Scanner;

import Presentacion.Login;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.Color;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Start
        while (!exit) {
            System.out.println(Color
                    .morado(Color.invertido((Cuadro.espacio(6) + "  Bienvenido :D" + (Cuadro.espacio(8) + "\n")))));
            System.out.println(Color.morado(Color.negrita("      > Menú principal <   ")));

            Cuadro principal = new Cuadro(
                    Color.morado("Módulo de Ventas"),
                    Color.morado("Módulo de Clientes"),
                    Color.morado("Modulo de Usuario"),
                    Color.morado("Módulo de Tarjeta"),
                    Color.rojo("Salir"));
            principal.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian((Cuadro.espacio(1) + "> Seleccione una opción: ")));

            int option = scanner.nextInt();

            // End

            // Switch de menu principal

            switch (option) {
                case 1:
                    Color.limpiarPantalla();
                    moduloVenta(scanner);
                    break;
                case 2:
                    Color.limpiarPantalla();
                    moduloCliente(scanner);
                    break;
                case 3:
                    Color.limpiarPantalla();
                    moduloUsuario(scanner);
                    break;
                case 4:
                    Color.limpiarPantalla();
                    moduloTarjeta(scanner);
                    break;
                case 5:
                    Color.limpiarPantalla();
                    System.out.println(Color.rojo("Saliendo del programa..."));
                    exit = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
    // End

    // Start
    private static void moduloVenta(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(8) + "> Módulo de Venta <")));

            Cuadro venta = new Cuadro(
                    Color.morado("Numero de tarjeta"),
                    Color.morado("Recoger el total"),
                    Color.rojo("Volver al menú principal"));
            venta.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Cuadro.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();

                    Cuadro numTarjeta = new Cuadro(
                            Color.amarillo("> Numero de tarjeta"));

                    numTarjeta.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Color.limpiarPantalla();

                    Cuadro recogerTotal = new Cuadro(
                            Color.amarillo("> Recoger el total"));
                    recogerTotal.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
            // End
        }
    }

    // Start
    private static void moduloCliente(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(8) + "> Módulo de Clientes <")));

            Cuadro cliente = new Cuadro(
                    Color.morado("Lista de clientes"),
                    Color.morado("Información de un cliente"),
                    Color.morado("Modificar cliente"),
                    Color.morado("Eliminar cliente"),
                    Color.rojo("Volver al menu principal"));
            cliente.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Cuadro.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();

                    Cuadro listaCli = new Cuadro(
                            Color.amarillo("> Lista de clientes"));
                    listaCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Color.limpiarPantalla();

                    Cuadro infoCli = new Cuadro(
                            Color.amarillo("> Informacion de un cliente"));
                    infoCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Color.limpiarPantalla();

                    Cuadro modificarCli = new Cuadro(
                            Color.amarillo("> Modificar informacion de cliente"));
                    modificarCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Color.limpiarPantalla();

                    Cuadro eliminarCli = new Cuadro(
                            Color.amarillo("> Eliminar Cliente"));
                    eliminarCli.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 5:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
    // End

    // Start
    private static void moduloUsuario(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(8) + "> Módulo de Usuario <")));

            Cuadro usuario = new Cuadro(
                    Color.morado("Lista de usuarios"),
                    Color.morado("Información de usuario"),
                    Color.morado("Modificar usuario"),
                    Color.morado("Eliminar usuario"),
                    Color.rojo("Volver al menú principal"));
            usuario.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Cuadro.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();

                    Cuadro listaUsua = new Cuadro(
                            Color.amarillo("> Lista de usuarios"));
                    listaUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Color.limpiarPantalla();

                    Cuadro infoUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    infoUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Color.limpiarPantalla();

                    Cuadro modificarUsua = new Cuadro(
                            Color.amarillo("> Modificar informacion del usuario"));
                    modificarUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Color.limpiarPantalla();

                    Cuadro eliminarUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    eliminarUsua.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 5:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
    // End

    // Start
    private static void moduloTarjeta(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                    Color.morado("Movimientos"),
                    Color.morado("Nivel de tarjetas"),
                    Color.morado("Beneficios"),
                    Color.rojo("Volver al menú principal"));
            tarjeta.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Cuadro.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();
                    movimientosTarj(scanner);
                    break;
                case 2:
                    Color.limpiarPantalla();

                    Cuadro nivelTarj = new Cuadro(
                            Color.amarillo("> Nivel de tarjetas"));
                    nivelTarj.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Color.limpiarPantalla();

                    Cuadro beneficTarj = new Cuadro(
                            Color.amarillo("> Beneficios de la tarjeta"));
                    beneficTarj.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
        // End
    }

    private static void movimientosTarj(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println(Color.morado(Color.negrita(Cuadro.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                    Color.morado("Alta de membresia"),
                    Color.morado("Cancelación de membresia"),
                    Color.morado("Renovación de membresia"),
                    Color.rojo("Volver al modulo"));
            tarjeta.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Cuadro.espacio(1) + "> Seleccione una opción: "));

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Color.limpiarPantalla();

                    Cuadro movimientosTarj = new Cuadro(
                            Color.amarillo("> Alta de membresia"));
                    movimientosTarj.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 2:
                    Color.limpiarPantalla();

                    Cuadro nivelTarj = new Cuadro(
                            Color.amarillo("> Cancelación de membresia"));
                    nivelTarj.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 3:
                    Color.limpiarPantalla();

                    Cuadro beneficTarj = new Cuadro(
                            Color.amarillo("> Renovacion de la membresia"));
                    beneficTarj.imprimirCuadro();

                    scanner.nextInt();
                    break;
                case 4:
                    Color.limpiarPantalla();
                    back = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
}