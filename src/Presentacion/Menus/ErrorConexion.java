package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ErrorConexion {
    public static void menu(String host, String error, String state){
        Texto.limpiarPantalla();
        Cuadro mensajeError = new Cuadro(
            ":(",
            Color.rojo(" Error de conexión con host ") + host
        );

        mensajeError.imprimirCuadro();
        System.out.println();

        System.out.println(Color.rojo("Error: " + error));
        System.out.println("SQLState: " + state);

        
        Texto.esperarEnter(Color.morado(" \n Enter para regresar"));

    }

    public static void menu(String [] error){
        if(error[1] != null)
            menu(error[0], error[1], error[2]);
    }
    
}
