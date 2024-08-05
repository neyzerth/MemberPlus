package Presentacion.Formato;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

    public static void suspensivos(){
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            esperar(0.1);
        }
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
            return "Numero de tarjeta no valido";

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

    public static String leerString(String txt) {
        System.out.print(txt);
        @SuppressWarnings("resource")
        Scanner entrada = new Scanner(System.in);
        try {
            String dato = entrada.nextLine();
            return dato;
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("Ocurrio un error inesperado."))));
            return null; // o alguna otra acción de error
        } catch (Exception e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó ningún valor."))));
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
            String num = leerString(txt);

            if(num.isBlank())
                return 0;
            else
                return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó un valor entero."))));
            return 0;
        }
    }

    public static double leerDoub(String txt) {
        try {
            String num = leerString(txt);
            
            if(num.isBlank())
                return 0;
            else
                return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó un valor numerico."))));
            return 0;
        }
    }

    public static float leerFloat(String txt) {
        try {
            String num = leerString(txt);
            
            if(num.isBlank())
                return 0;
            else
                return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó un valor numerico."))));
            return 0;
        }
    }
}
