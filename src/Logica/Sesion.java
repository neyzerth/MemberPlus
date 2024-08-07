package Logica;

import Logica.Objetos.Usuario;

public class Sesion {
    //Estaticos, pues pertenecen a la clase y no a una instancia
    private static int id, idRol;
    private static String nombre, nomUsuario, rol;

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
        
        Sesion.setId(sesion.getIdUsuario());
        Sesion.setNombre(sesion.getNombre());
        Sesion.setNomUsuario(sesion.getNombre());
        Sesion.setIdRol(sesion.rol.getIdRol());
        Sesion.setRol(sesion.rol.getNombre());
        return true;
    }
    
    //SETTERS Y GETTERS
    //Al ser estatico, en ves de "this" se pone "Sesion" (la clase)
    private static void setId(int idUsuario) {
        Sesion.id = idUsuario;
    }

    public static int getId(){
        return Sesion.id;
    }        
    
    private static void setIdRol(int idRol) {
        Sesion.idRol = idRol;
    }
    
    public static int getIdRol(){
        return Sesion.idRol;
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

    private static void setRol(String rol){
        Sesion.rol = rol;
    }

    public static String getRol(){
        return rol;
    }

    public static void cerrarSesion(){
        id = 0;
        nombre = null;
        nomUsuario = null;
        rol = null;
    }
    
}
