package Presentacion.Menus;

import Logica.Sesion;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Principal {
    public static void menu() {
        boolean salir = false;
        
        do {
            Texto.limpiarPantalla();
            System.out.println(
                Color.morado(Color.invertido(
                Texto.espacio(8) +
                    "Bienvenido " + Color.negrita + Sesion.getNombre() +" :D" +
                (Texto.espacio(8) + "\n")))
            );
            System.out.println(Color.morado(Color.negrita("      > Menú principal <   ")));

            Cuadro principal = new Cuadro(
                Color.morado("Modulo de Usuario"),
                Color.morado("Módulo de Ventas"),
                Color.morado("Módulo de Clientes"),
                Color.morado("Módulo de Tarjeta"),
                Color.rojo("Salir")
            );
            principal.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian((Texto.espacio(1) + "> Seleccione una opción: ")));

            switch (opcion) {
                case 1: ModUsuario.desplegarMenu();
                    break;
                case 2: ModVenta.menu();
                    break;
                case 3: ModCliente.desplegarMenu();
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