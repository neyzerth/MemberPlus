package Logica;

import Logica.Objetos.Rol;
import Logica.Objetos.Usuario;

public class Sesion {
    //Estaticos, pues pertenecen a la clase y no a una instancia
    private static String nombre, nomUsuario;
    private static Rol rol;

    //Retorna un usuario a partir de su nombre de usuario y contrasena
    private static Usuario usuarioSesion(String usuario, String contrasena){
        Usuario sesion = new Usuario();
        sesion = Usuario.iniciarSesion(usuario, contrasena);
        return sesion;
    }

    //si el usuario que inicio sesion no es nulo, se inicia la sesion dinamica
    public static boolean iniciarSesion(String usuario, String contrasena){
        Usuario sesion = usuarioSesion(usuario, contrasena);
        if(sesion == null)
            return false;
        
        Sesion.setNombre(sesion.getNombre());
        Sesion.setNomUsuario(sesion.getNombre());
        Sesion.setRol(sesion.getRol());
        return true;
    }
    
    //SETTERS Y GETTERS
    //Al ser estatico, en ves de "this" se pone "Sesion" (la clase)
    private static void setNombre(String nombre){
        Sesion.nombre = nombre;
    }
    public static String getNombre(){
        return nombre;
    }

    private static void setNomUsuario(String nomUsuario){
        Sesion.nomUsuario = nomUsuario;
    }

    public static String getNomUsuario(){
        return nomUsuario;
    }

    private static void setRol(Rol rol2){
        Sesion.rol = rol2;
    }

    public static Rol getRol(){
        return rol;
    }

    public static void cerrarSesion(){
        nombre = null;
        nomUsuario = null;
        rol = null;
    }
    
}
