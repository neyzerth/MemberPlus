package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

import java.io.Console;

import Logica.ConectarBD;
import Logica.Sesion;

public class IniciarSesion {

    public static void interfaz() {
        String usuario;
        String contrasena;
        boolean errorSesion = false;
        
        do {
            Texto.limpiarPantalla();

            Console consola = System.console();

            Cuadro inicioS = new Cuadro(Color.morado(" > Iniciar sesión <"));
            inicioS.imprimirCuadro();

            if(errorSesion){
                System.out.println(Color.rojo(Color.negrita(" ¡Datos incorrectos! ")));
                System.out.println(Color.rojo(" Intentelo de nuevo "));
            }
            System.out.println();

            // Solicitar nombre de usuario
            System.out.print(Color.cian(" > Usuario: "));
            usuario = consola.readLine();

            // Solicitar contraseña de forma segura
            char[] contrasenaArreglo = consola.readPassword(Color.cian(" > Contraseña: "));

            // Convertir el array de caracteres a String
            contrasena = new String(contrasenaArreglo);

            System.out.println("Conectando con base de datos...");
            errorSesion = !Sesion.iniciarSesion(usuario, contrasena);

            if(!errorSesion){
                String[] error = ConectarBD.probar(); 
                if(error[1] != null)
                    ErrorConexion.menu(error[0], error[1], error[2]);
            }

        } while (errorSesion);

        Principal.menu();
    }
}