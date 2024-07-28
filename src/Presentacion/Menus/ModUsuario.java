package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;
import Logica.Objetos.Usuario;

public class ModUsuario {
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

            int option = Texto.leerInt(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
                case 1:
                    Texto.limpiarPantalla();

                    Cuadro listaUsua = new Cuadro(
                            Color.amarillo("> Lista de usuarios")
                    );
                    listaUsua.imprimirCuadro();

                    tablaUsuarios();

                    Texto.leerInt("> ");
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    infoUsua.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 3:
                    Texto.limpiarPantalla();

                    Cuadro modificarUsua = new Cuadro(
                            Color.amarillo("> Modificar informacion del usuario"));
                    modificarUsua.imprimirCuadro();

                    Texto.leerInt("> ");
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    eliminarUsua.imprimirCuadro();

                    Texto.leerInt("> ");
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

    private static void tablaUsuarios(){
        Tabla tabla = new Tabla("ID", "Nombre Usuario","Contrasena", "RFC");
        for (Usuario usuario : Usuario.importarUsuarios()) {
            tabla.agregarFila(
                usuario.getId(),
                usuario.getNomUsuario(),
                usuario.getContrasena(),
                usuario.getRfc()
            );
        }
        tabla.imprimirTabla();
        Usuario.importarUsuarios();
    }
}
