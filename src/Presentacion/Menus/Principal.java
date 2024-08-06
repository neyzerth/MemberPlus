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

            String [] modulos = {
                Color.morado("Módulo de Ventas"),
                Color.morado("Módulo de Clientes"),
                Color.morado("Módulo de Membresias"),
                Color.morado("Modulo de Usuario"),
            };
            Cuadro principal = new Cuadro();

            //Desplegar menu de manera distinta para cada rol
            switch (Sesion.getRol()) {
                case "administrador":
                    principal = new Cuadro(modulos); //t0dos
                    break;
                case "cajero": case "supervisor":
                    principal = new Cuadro(modulos[0], modulos[1], modulos[2]);
                    break;
                case "gerente":
                    principal = new Cuadro(modulos[1], modulos[2], modulos[3]);
                    break;
            }

            principal.agregarSalir();
            principal.imprimirCuadroNum();

            System.out.println();

            int opcion = Leer.entero(Color.cian((Texto.espacio(1) + "> Seleccione una opción: ")));

            //alterar la opcion dependiendo el rol
            int opcionRol = opcion;
            switch (Sesion.getRol()) {
                case "cajero": case "supervisor":
                    //IF TERNARIO
                    // Si opcion == 4, regresa -1, sino, regresa el valor de opcion
                    //se guarda en la variable opcionRol
                    opcionRol = opcion == 4 ? -1 : opcion;
                    break;
                case "gerente":
                    opcionRol = opcion > 0 ? opcion+1 : -1;
                    opcionRol = opcion == 0 ? 0 : opcionRol;
                    break;
            }

            switch (opcionRol) {
                case 1: ModVenta.menu();
                    break;
                case 2: ModCliente.desplegarMenu();
                    break;
                case 3: ModMembresia.menu();
                    break;
                case 4: ModUsuario.desplegarMenu();
                    break;

                case 0: //SALIR
                    Sesion.cerrarSesion();

                    System.out.println(Color.rojo("Saliendo del programa..."));
                    Texto.esperar(1);
                    
                    salir = true;
                    break;
                default:
                    System.out.print(Color.rojo("Opción inválida, por favor intente de nuevo "));
                    Texto.suspensivos(3,0.6);
                    break;
            }
        } while (!salir);
    }
}