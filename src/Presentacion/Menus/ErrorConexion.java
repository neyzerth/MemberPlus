package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ErrorConexion {
    public static void menu(String host, String error, String state){
        Texto.limpiarPantalla();
        Cuadro mensajeError = new Cuadro(
            ":(",
            "Error de conexión con host " + host
        );

        mensajeError.imprimirCuadro();
        System.out.println();

        System.out.println("Error: " + error);
        System.out.println("SQLState: " + state);

        
        Texto.esperarEnter("\nEnter para regresar");

    }
    
}
