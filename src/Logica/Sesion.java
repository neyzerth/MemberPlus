package Logica;

import Logica.Objetos.Usuario;

public class Sesion {
    public static boolean iniciarSesion(String usuario, String contrasena){
        return usuarioSesion(usuario, contrasena) != null;
    }
    public static Usuario usuarioSesion(String usuario, String contrasena){
        Usuario sesion = new Usuario();
        sesion = Usuario.iniciarSesion(usuario, contrasena);
        return sesion;
    }
}
