package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Usuario {
    public static void menu() {
        boolean salir = false;
        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Usuario <")));

            Cuadro usuario = new Cuadro(
                    Color.morado("Lista de usuarios"),
                    Color.morado("Información de usuario"),
                    Color.morado("Modificar usuario"),
                    Color.morado("Eliminar usuario"),
                    Color.rojo("Volver al menú principal"));
            usuario.imprimirCuadroNum();

            System.out.println();

            int option = Texto.entradaInt(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro listaUsua = new Cuadro(
                            Color.amarillo("> Lista de usuarios"));
                    listaUsua.imprimirCuadro();

                    Texto.entradaInt("> ");
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    infoUsua.imprimirCuadro();

                    Texto.entradaInt("> ");
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro modificarUsua = new Cuadro(
                            Color.amarillo("> Modificar informacion del usuario"));
                    modificarUsua.imprimirCuadro();

                    Texto.entradaInt("> ");
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    eliminarUsua.imprimirCuadro();

                    Texto.entradaInt("> ");
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                    Texto.esperar(1);
            }
        }
    }
}
