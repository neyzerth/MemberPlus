package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;
import Logica.Objetos.Usuario;

public class ModUsuario {
    public static void menu() {
        boolean salir = false;
        int id;
        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Usuario <")));

            Cuadro listaUsuario = new Cuadro(
                    Color.morado("Lista de usuarios"),
                    Color.morado("Información de usuario"),
                    Color.morado("Modificar usuario"),
                    Color.morado("Eliminar usuario"),
                    Color.rojo("Volver al menú principal"));
            listaUsuario.imprimirCuadroNum();

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

                    Texto.esperarEnter();
                    break;
                case 2:
                    Texto.limpiarPantalla();

                    Cuadro infoUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    infoUsua.imprimirCuadro();

                    id = Texto.leerInt("> ID de usuario a desplegar: ");

                    tablaUsuarios(id);
                    Texto.esperarEnter();
                    break;
                case 3:
                    menuActualizarUsuario();
                    
                    break;
                case 4:
                    Texto.limpiarPantalla();

                    Cuadro eliminarUsua = new Cuadro(
                            Color.amarillo("> Información de usuario"));
                    eliminarUsua.imprimirCuadro();

                    tablaUsuarios();

                    id = Texto.leerInt("> ID del usuario a eliminar: ");

                    tablaUsuarios(id);
                    
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

    public static void menuActualizarUsuario(){
        Texto.limpiarPantalla();
        int id;
        Cuadro modificarUsua = new Cuadro(
                Color.amarillo("> Modificar informacion del usuario"));
        modificarUsua.imprimirCuadro();

        id =Texto.leerInt("> ID del usuario a modificar: ");
        tablaUsuarios(id);

        
        String nombre = Texto.leerString("> Nombre del usuario: ");
        String contrasena = Texto.leerString("> Contraseña del usuario: ");
        String rfc = Texto.leerString("> RFC del usuario: ");
        int persona = Texto.leerInt("> PERSONA del usuario: ");
        int rol = Texto.leerInt("> ROL del usuario: ");

        Usuario usuario = new Usuario(id, nombre, contrasena, rfc);

        boolean actualizar = usuario.actualizarUsuario(persona, rol);

        Texto.esperarEnter(String.valueOf(actualizar));
    }

    private static void tablaUsuarios(int id){
        Tabla tabla = new Tabla("ID", "Nombre Usuario", "RFC");
        Usuario [] usuarios;
        if(id == 0){
            Texto.esperarEnter("INGRESA UN ID VALIDO");
            return;
        }

        if(id < 1)
            usuarios = Usuario.importarUsuarios();
        else
            usuarios = Usuario.importarUsuarios(id);

        for (Usuario usuario : usuarios) {
            tabla.agregarFila(
                usuario.getId(),
                usuario.getNomUsuario(),
                usuario.getRfc()
            );
        }
        tabla.imprimirTabla();
        Usuario.importarUsuarios();
    }

    private static void tablaUsuarios(){
        tablaUsuarios(-1);
    }


}
