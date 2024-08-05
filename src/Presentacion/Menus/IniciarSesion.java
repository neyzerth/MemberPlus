package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

import Logica.ConectarBD;
import Logica.Sesion;

public class IniciarSesion {
    private static boolean sesionIniciada = true;

    public static boolean menu() {
        String usuario;
        String contrasena;
        
        
        Texto.limpiarPantalla();

        Cuadro inicioS = new Cuadro(Color.morado("  Iniciar sesión "));
        inicioS.imprimirCuadro();

        System.out.println(Color.morado(Color.negrita(Texto.espacio(3)+"Ingrese sus datos:")));

        if(!sesionIniciada){
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(Texto.espacio(2)+" ¡Datos incorrectos! ")));
            System.out.println(Color.rojo(Texto.espacio(2)+" Intentelo de nuevo "));
        }
        System.out.println();

        // Solicitar nombre de usuario
        usuario = Texto.leerString(Color.cian(" > Usuario: "));

        // Solicitar contraseña de forma segura
        contrasena = Texto.leerContra(Color.cian(" > Contraseña: "));

        System.out.println();
        System.out.print(Color.amarillo(" Conectando con base de datos"));
        Texto.suspensivos();

        //Cambiar el valor estatico
        IniciarSesion.sesionIniciada = Sesion.iniciarSesion(usuario, contrasena);

        if(sesionIniciada)
            ErrorConexion.menu(ConectarBD.probar()); 

        return sesionIniciada;
    
    }
}