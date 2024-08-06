package Presentacion.Formato;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leer {
    public static String cadena(String txt, String placeholder) {
        String placeholderStr = txt + Color.negro(placeholder);
        System.out.print(placeholderStr + "\r" + txt);
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

    public static String cadena(String txt){
        return cadena(txt, "");
    }

    public static String contra(String txt, String placeholder){
        String contrasena = cadena(txt + Color.invisible, placeholder);
        System.err.print(Color.reset);
        return contrasena;
    }

    public static String contra(String txt){
        return contra(txt, "");
    }

    public static int entero(String txt, String placeholder) {
        try {
            String num = cadena(txt, placeholder);

            if(num.isBlank())
                return 0;
            else
                return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó un valor entero."))));
            return -1;
        }
    }
    public static int entero(String txt) {
        return entero(txt, "");
    }

    public static double decimal(String txt, String placeholder) {
        try {
            String num = cadena(txt, placeholder);
            
            if(num.isBlank())
                return 0;
            else
                return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Error: ") + (Color.rojo("no se ingresó un valor numerico."))));
            return -1;
        }
    }
    public static double decimal(String txt) {
        return decimal(txt, "");
    }

    public static float flotante(String txt, String placeholder) {
        return (float) decimal(txt, placeholder);
    }
    public static float flotante(String txt) {
        return flotante(txt, "");
    }

    public static boolean booleano(String color, String txt, String placeholder){
        return 1 == entero(Color.colorText(color, Color.negrita(txt)+"\n> SI [1] - NO [2]: "));
    }
    public static boolean booleano(String color,  String txt){
        return booleano(color, txt, "");
    }
    public static boolean booleano(String txt){
        return booleano("", txt);
    }
    
}
