package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Principal {
    public static void menu() {
        boolean salir = false;
        
        do {
            Texto.limpiarPantalla();
            System.out.println(Color.azul(Color.invertido(
                (Texto.espacio(6) + "  Bienvenido :D" + (Texto.espacio(8) + "\n")))));
            System.out.println(Color.azul(Color.negrita("      > Menú principal <   ")));

            Cuadro principal = new Cuadro(
                Color.azul("Módulo de Ventas"),
                Color.azul("Módulo de Clientes"),
                Color.azul("Modulo de Usuario"),
                Color.azul("Módulo de Tarjeta"),
                Color.rojo("Salir")
            );
            principal.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian((Texto.espacio(1) + "> Seleccione una opción: ")));

            switch (opcion) {
                case 1: ModVenta.menu();
                    break;
                case 2: ModCliente.menu();
                    break;
                case 3: ModUsuario.menu();
                    break;
                case 4: ModTarjeta.menu();
                    break;

                case 5: //SALIR
                    System.out.println(Color.rojo("Saliendo del programa..."));
                    Texto.esperar(1);
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo. "));
                    Texto.esperar(1);
                    break;
            }
        } while (!salir);
    }
}