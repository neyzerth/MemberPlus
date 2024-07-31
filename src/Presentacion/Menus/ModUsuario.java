//-- Active: 1719932866993@@127.0.0.1@3306
package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

import Logica.Objetos.Persona;
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
                    Color.morado("Registrar usuario"),
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
                case 3: registrarUsuario();
                    break;
                case 4: actualizarUsuario();
                    break;
                case 5: eliminarUsuario();
                    break;
                case 6: default: salir = true;
            }
        }
    }

    private static void verUsuarios() {
        Texto.limpiarPantalla(); 
        Cuadro listaUsua = new Cuadro (
                Color.cian(" Lista de usuarios"));
        listaUsua.imprimirCuadro();

        tablaUsuarios();

        Texto.esperarEnter();
    }

    private static void verUsuario() {
        Texto.limpiarPantalla();
        Cuadro infoUsua = new Cuadro(
                Color.cian(" Información de usuario"));
        infoUsua.centrado(true);
        infoUsua.imprimirCuadro();

        tablaUsuarios();
        System.out.println();

        int id = Texto.leerInt(Color.cian(" > ID de usuario a desplegar: "));
        tablaUsuarios(id);
        Texto.esperarEnter();
    }

    public static void registrarUsuario() {
        Texto.limpiarPantalla();
        Cuadro modificarUsua = new Cuadro(Color.cian(" Registrar usuario"));
        modificarUsua.imprimirCuadro();

        Usuario usuario = new Usuario();

        do{
            System.out.println();
            String nombre = Texto.leerString(Color.amarillo(" > Nombre del usuario: "));
            String contrasena = Texto.leerContra(Color.amarillo(Color.negrita(" > Contraseña del usuario: ")));
            String rfc = Texto.leerString(Color.amarillo(" > RFC del usuario: "));
            int rol = Texto.leerInt(Color.amarillo(Color.negrita(" > ROL del usuario: ")));

            try {
                Persona persona = ModPersona.datosPersona();

                if(persona.insertarPersona()){
                    persona.setIdPersona();
                    usuario = new Usuario(0, nombre, contrasena, rfc, persona, rol);
                }

                if( usuario.insertarUsuario()){
                    tablaUsuarios(usuario.getId());
                    Texto.esperarEnter(Color.verde("Usuario registrado con exito"));
                } else
                    Texto.esperarEnter(Color.rojo("Error al registrar el usuario"));
            } catch (Exception e) {
                Texto.esperarEnter(Color.rojo("DATO NO VALIDO"));
            }
        } while (false);
    }

    private static void actualizarUsuario(){
        Texto.limpiarPantalla();
        int id;
        Cuadro modificarUsua = new Cuadro(
                Color.morado("> Modificar informacion del usuario"));
        modificarUsua.imprimirCuadro();

        tablaUsuarios();

        id = Texto.leerInt("> ID del usuario a modificar: ");
        
        
        tablaUsuarios(id);
        do{
            Usuario usuario = Usuario.importarUsuarios(id);

            usuario.setNomUsuario(Texto.leerString(Color.amarillo(Color.negrita("> Nombre del usuario: "))));
            usuario.setContrasena(Texto.leerContra(Color.amarillo(Color.negrita("> Contraseña del usuario: "))));
            usuario.setRfc(Texto.leerString("> RFC del usuario: "));
            usuario.setIdRol(Texto.leerInt(Color.amarillo(Color.negrita("> ROL del usuario: "))));

            try {
                System.out.println(Color.rojo(Color.negrita("Desea modificar la informacion personal del usuario??")));
                boolean conf = Texto.leerString(Color.rojo("SI[s]  NO[n]: ")).toLowerCase().equals("s");

                if(conf){
                    Persona persona = ModPersona.datosPersona();
                    persona.setIdPersona(usuario.getIdPersona());
                }
            
                boolean actualizar = usuario.actualizarUsuario();

                if(actualizar){
                    System.out.println("Usuario actualizado con exito");
                    tablaUsuarios(id);
                } else
                    System.out.println("Error al actualizar el usuario");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Texto.esperarEnter("DATO NO VALIDO");
                
            }
        } while (false);
    }

    public static void eliminarUsuario() {
        Texto.limpiarPantalla();
        boolean eliminado = false;

        Cuadro eliminarUsua = new Cuadro(
                Color.amarillo("> Información de usuario"));
        eliminarUsua.imprimirCuadro();

        tablaUsuarios();

        int id = Texto.leerInt("> ID del usuario a eliminar: ");

        tablaUsuarios(id);
        System.out.println(Color.rojo(Color.negrita("Seguro que desea eliminar este usuario?")));
        boolean conf = Texto.leerString("SI[s]  NO[n]: ").toLowerCase().equals("s");

        if (conf)
            eliminado = Usuario.eliminarUsuario(id);
        Texto.esperarEnter(String.valueOf(eliminado));

    }

    private static Usuario tablaUsuarios(int id) {
        
        if (!Usuario.validarUsuario(id)){
            System.out.println(Color.rojo(Color.negrita("\n No existe usuario con ID: " + id)));
            return null;
        }

        Tabla tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("RFC"));

        Usuario usuario = Usuario.importarUsuarios(id);

        tabla.agregarFila(
            usuario.getId(),
            usuario.getNomUsuario(),
            usuario.getRfc()
        );
        tabla.imprimirTablaSimple();

        return usuario;
    }

    private static Usuario[] tablaUsuarios() {
        Tabla tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("RFC"));
        Usuario[] usuarios =  Usuario.importarUsuarios();

        for (Usuario usuario : usuarios) {
            tabla.agregarFila(
                usuario.getId(),
                usuario.getNomUsuario(),
                usuario.getRfc()
            );
        }
        tabla.imprimirTablaSimple();

        return usuarios;
    }
}
