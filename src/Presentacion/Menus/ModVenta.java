package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ModVenta {
    public static void menu() {
        boolean salir = false;
        
        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Venta <")));

            Cuadro venta = new Cuadro(
                    Color.morado("Numero de tarjeta"),
                    Color.morado("Recoger el total"),
                    Color.rojo("Volver"));
            venta.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian(" > Seleccione una opción: "));

            switch (opcion) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro numTarjeta = new Cuadro(
                            Color.morado("> Numero de tarjeta"));

                    numTarjeta.imprimirCuadro();

                    Texto.leerString("> ");
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro recogerTotal = new Cuadro(
                            Color.morado("> Recoger el total"));
                    recogerTotal.imprimirCuadro();

                    Texto.leerInt("> $");
                    break;
                case 3:
                    Texto.limpiarPantalla();
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                    Texto.esperar(1);
            }
        }
    }
    
}
