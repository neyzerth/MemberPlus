package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

import Logica.ConectarBD;
import Logica.Sesion;

public class IniciarSesion {

    public static void interfaz() {
        String usuario;
        String contrasena;
        boolean errorSesion = false;
        
        do {
            Texto.limpiarPantalla();

            Cuadro inicioS = new Cuadro(Color.morado(" > Iniciar sesión <"));
            inicioS.imprimirCuadro();

            System.out.println(Color.morado(Color.negrita(Texto.espacio(4)+"Ingrese sus datos:")));

            if(errorSesion){
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(Texto.espacio(2)+" ¡Datos incorrectos! ")));
                System.out.println(Color.rojo(Texto.espacio(2)+" Intentelo de nuevo "));
            }
            System.out.println();

            // Solicitar nombre de usuario
            usuario = Texto.leerString(Color.cian(" > Usuario: "));

            // Solicitar contraseña de forma segura
            contrasena = Texto.leerContra(Color.cian(" > Contraseña: "));

            System.out.println("Conectando con base de datos...");
            errorSesion = !Sesion.iniciarSesion(usuario, contrasena);

            if(errorSesion){
                String[] error = ConectarBD.probar(); 
                if(error[1] != null)
                    ErrorConexion.menu(error[0], error[1], error[2]);
            }

        } while (errorSesion);

        Principal.menu();
    }
}