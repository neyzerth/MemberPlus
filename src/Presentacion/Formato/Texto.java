package Presentacion.Formato;

import java.util.Scanner;

public class Texto {
    //limpiar pantalla 
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // iterando n espacios
    public static String espacio(int n) {
        String espacios = "";
        for (int i = 0; i < n; i++) {
            espacios += " ";
        }
        return espacios;
    }

    @SuppressWarnings("resource")
    public static void esperarEnter(String msj) {
        Scanner enter = new Scanner(System.in);
        System.out.print(msj);
        enter.nextLine();
    }

    public static void esperarEnter(){
        esperarEnter("\nENTER para continuar...");
    }
}
