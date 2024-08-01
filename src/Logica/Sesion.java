package Logica;

import Logica.Objetos.Usuario;

public class Sesion {
    private static String nombre, nomUsuario;
    private static int rol;

    public static boolean iniciarSesion(String usuario, String contrasena){
        Usuario sesion = usuarioSesion(usuario, contrasena);
        if(sesion == null)
            return false;
        
        Sesion.setNombre(sesion.getNombre());
        Sesion.setNomUsuario(sesion.getNombre());
        Sesion.setRol(sesion.getIdRol());
        return true;
    }
    
    private static Usuario usuarioSesion(String usuario, String contrasena){
        Usuario sesion = new Usuario();
        sesion = Usuario.iniciarSesion(usuario, contrasena);
        return sesion;
    }

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

    private static void setRol(int rol){
        Sesion.rol = rol;
    }

    public static int getRol(){
        return rol;
    }
    
}
