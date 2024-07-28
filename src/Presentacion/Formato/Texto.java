package Presentacion.Formato;

import java.io.Console;
import java.util.NoSuchElementException;
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

    public static void esperarEnter(String msj) {
        @SuppressWarnings("resource")
        Scanner enter = new Scanner(System.in);
        System.out.print(msj);
        System.out.print(Color.invisible);
        enter.nextLine();
        System.out.print(Color.reset);
    }

    public static void esperarEnter(){
        esperarEnter("\nENTER para continuar...");
    }

    public static void esperar(float seg) {
        int tiempo = (int) (seg * 1000);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String entradaString(String txt) {
        System.out.print(txt);
        @SuppressWarnings("resource")
        Scanner entrada = new Scanner(System.in);
        try {
            String dato = entrada.nextLine();
            return dato;
        } catch (NoSuchElementException e) {
            System.out.println("Error: Ocurrio un error inesperado.");
            return null; // o alguna otra acción de error
        } catch (Exception e) {
            System.out.println("Error: no se ingresó ningún valor.");
            return null; // o alguna otra acción de error
        }
    }

    public static String entradaContrasena(String txt){
        String contrasena = entradaString(txt + Color.invisible);
        System.err.print(Color.reset);
        return contrasena;
    }

    public static int entradaInt(String txt) {
        try {
            return Integer.parseInt(entradaString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor entero.");
            return 0;
        }
    }

    public static double entradaDou(String txt) {
        try {
            return Integer.parseInt(entradaString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor numerico.");
            return 0;
        }
    }

    public static float entradaFloat(String txt) {
        try {
            return Float.parseFloat(entradaString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor numerico.");
            return 0;
        }
    }
}
