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
                case 1: verUsuarios();
                    break;
                case 2: verUsuario();
                    break;
                case 3: actualizarUsuario();
                    break;
                case 4: eliminarUsuario();
                    break;
                case 5: default: salir = true;
            }
        }
    }

    private static void verUsuarios() {
        Texto.limpiarPantalla();
        Cuadro listaUsua = new Cuadro(
                Color.amarillo("> Lista de usuarios"));
        listaUsua.imprimirCuadro();

        tablaUsuarios();

        Texto.esperarEnter();
    }

    private static void verUsuario() {
        Texto.limpiarPantalla();
        Cuadro infoUsua = new Cuadro(
                Color.amarillo("> Información de usuario"));
        infoUsua.imprimirCuadro();

        int id = Texto.leerInt("> ID de usuario a desplegar: ");

        tablaUsuarios(id);
        Texto.esperarEnter();
    }

    public static void actualizarUsuario() {
        Texto.limpiarPantalla();
        int id;
        Cuadro modificarUsua = new Cuadro(
                Color.amarillo("> Modificar informacion del usuario"));
        modificarUsua.imprimirCuadro();

        id = Texto.leerInt("> ID del usuario a modificar: ");
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

    public static void eliminarUsuario() {
        Texto.limpiarPantalla();
        boolean eliminado = false;

        Cuadro eliminarUsua = new Cuadro(
                Color.amarillo("> Información de usuario"));
        eliminarUsua.imprimirCuadro();

        tablaUsuarios();

        int id = Texto.leerInt("> ID del usuario a eliminar: ");

        if (tablaUsuarios(id) != null) {
            System.out.println(Color.rojo(Color.negrita("Seguro que desea eliminar este usuario?")));
            boolean conf = Texto.leerString("SI[s]  NO[n]: ").toLowerCase().equals("s");

            if (conf)
                eliminado = Usuario.eliminarUsuario(id);
        }
        Texto.esperarEnter(String.valueOf(eliminado));

    }

    private static Usuario[] tablaUsuarios(int id) {
        Tabla tabla = new Tabla("ID", "Nombre Usuario", "RFC");
        Usuario[] usuarios = new Usuario[1];

        if (id == 0) {
            Texto.esperarEnter("INGRESA UN ID VALIDO");
            return null;
        }

        if (id == -1)
            usuarios = Usuario.importarUsuarios();
        else {
            usuarios[0] = Usuario.importarUsuarios(id);
            if (!Usuario.validarUsuario(id)) {
                Texto.esperarEnter("No existe usuario con ID : " + id);
                return null;
            }
        }

        for (Usuario usuario : usuarios) {
            tabla.agregarFila(
                usuario.getId(),
                usuario.getNomUsuario(),
                usuario.getRfc());
        }
        tabla.imprimirTablaSimple();

        return usuarios;
    }

    private static Usuario[] tablaUsuarios() {
        return tablaUsuarios(-1);
    }

}
