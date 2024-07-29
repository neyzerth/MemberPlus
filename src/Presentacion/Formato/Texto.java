package Presentacion.Formato;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Texto {
    //limpiar pantalla 
    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al limpiar la consola: " + ex.getMessage());
        }
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

    public static String leerString(String txt) {
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

    public static String leerContra(String txt){

        String contrasena = leerString(txt + Color.invisible);
        System.err.print(Color.reset);
        return contrasena;
    }

    public static int leerInt(String txt) {
        try {
            return Integer.parseInt(leerString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor entero.");
            return 0;
        }
    }

    public static double leerDoub(String txt) {
        try {
            return Integer.parseInt(leerString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor numerico.");
            return 0;
        }
    }

    public static float leerFloat(String txt) {
        try {
            return Float.parseFloat(leerString(txt));
        } catch (NumberFormatException e) {
            System.out.println("Error: no se ingresó un valor numerico.");
            return 0;
        }
    }
}
