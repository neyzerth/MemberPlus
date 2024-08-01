package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ModTarjeta {
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                    Color.morado("Movimientos"),
                    Color.morado("Nivel de tarjetas"),
                    Color.morado("Beneficios"),
                    Color.rojo("Volver al menú principal"));
            tarjeta.imprimirCuadroNum();

            System.out.println();

            int option = Texto.leerInt(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
                case 1:
                    movimientosTarj();
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro nivelTarj = new Cuadro(
                            Color.amarillo("> Nivel de tarjetas"));
                    nivelTarj.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro beneficTarj = new Cuadro(
                            Color.amarillo("> Beneficios de la tarjeta"));
                    beneficTarj.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }

    }

    private static void movimientosTarj() {
        boolean salir = false;
        while (!salir) {
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                    Color.morado("Alta de membresia"),
                    Color.morado("Cancelación de membresia"),
                    Color.morado("Renovación de membresia"),
                    Color.rojo("Volver al modulo"));
            tarjeta.imprimirCuadroNum();

            System.out.println();
            System.out.print(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            int option = Texto.leerInt(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro movimientosTarj = new Cuadro(
                            Color.amarillo("> Alta de membresia"));
                    movimientosTarj.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro nivelTarj = new Cuadro(
                            Color.amarillo("> Cancelación de membresia"));
                    nivelTarj.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro beneficTarj = new Cuadro(
                            Color.amarillo("> Renovacion de la membresia"));
                    beneficTarj.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }
    }
}
