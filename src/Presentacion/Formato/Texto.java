package Presentacion.Formato;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
            System.out.println();
            System.out.println(Color.rojo(" Error al limpiar la consola: " + ex.getMessage()));
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
        esperarEnter(Color.morado("\n Enter para continuar..."));
    }

    public static void esperar(double seg) {
        int tiempo = (int) (seg * 1000);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void suspensivos(int puntos, double seg){
        for (int i = 0; i < puntos; i++) {
            System.out.print(".");
            esperar(seg);
        }
    }
    public static void suspensivos(int puntos){
        suspensivos(puntos, 0.1);
    }
    public static void suspensivos(){
        suspensivos(5);
    }

    public static String moneda(double cantidad) {
        return moneda(cantidad, 2);
    }
    public static String moneda(float cantidad) {
        return moneda(cantidad, 2);
    }
    public static String moneda(int cantidad) {
        double nuevaCantidad = cantidad + 0.0;
        return moneda(nuevaCantidad, 2);
    }

    public static String moneda(double cantidad, int decim){
        String moneda = "";
        //Divido en dos con la funcion split cuando encuentre un "."
        String[] numeros = String.valueOf(cantidad).split("\\.");

        //los enteros se guardan en la primera posicion
        //para mas facilidad, inverti los enteros para agregarles coma
        String enteros = invertir(numeros[0]);
        
        //los decimales se guardan en la segunda posicion
        String decimales = numeros[1];

        //si solo tiene un digito, se le agrega un 0 extra
        if(decimales.length() < 2)
            decimales += "0";

        int digitos = enteros.length(); //solo guardo los digitos de los enteros

        for (int i = 0; i < digitos; i++) {
            moneda += enteros.charAt(i);
            //Cada que itere en una tercera posicion, agrega ","
            //menos cuando sea el ultimo digito (digitos-1)
            if ((i+1) % 3 == 0 && i < digitos - 1)
                moneda += ",";
        }

        return "$" + invertir(moneda) + "." + decimales.substring(0, decim); //dependiento cuantos decimales quiere (defecto 2)
    }

    public static String tarjeta(String numTarjeta){
        String formatoTarjeta = "";
        
        if(numTarjeta.length() != 16)
            return Color.rojo(Color.negrita(" Numero de tarjeta no valido"));

        for (int i = 0; i < numTarjeta.length(); i++) {
            formatoTarjeta += numTarjeta.charAt(i);
            if((i+1) % 4 == 0)
                formatoTarjeta += " ";
        }
        return formatoTarjeta;
    }

    public static String fecha(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    private static String invertir(String s) {
        String invertido = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            invertido = invertido + s.charAt(i);
        }
        return invertido;
    }


}
