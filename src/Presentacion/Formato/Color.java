package Presentacion.Formato;

public class Color {
    // Colores ANSI
    public static String reset = "\u001B[0m";
    public static String azul = "\u001B[34m"; // Azul
    public static String verde = "\u001B[32m"; // Verde
    public static String rojo = "\u001B[31m"; // Rojo
    public static String negro = "\u001B[30m"; // Negro
    public static String amarillo = "\u001B[33m"; // Amarillo
    public static String morado = "\u001B[35m"; // Morado
    public static String cian = "\u001B[36m"; // Cian
    // Colores brillantes de texto
    public static String negroBrillante = "\u001B[90m";
    // Colores de fondo ANSI
    public static String fondoRojo = "\u001B[41m";
    public static String fondoAzul = "\u001B[44m";
    public static String fondoMorado = "\u001B[45m";
  
    // Estilos de texto ANSI
    public static String negrita = "\u001B[1m";
    public static String invisible = "\u001B[8m";

    public static String colorText(String color, String txt) {
        return color + txt + reset;
    }

    public static String azul(String txt) {
        return colorText(azul, txt);
    }

    public static String verde(String txt) {
        return colorText(verde, txt);
    }

    public static String rojo(String txt) {
        return colorText(rojo, txt);
    }

    public static String negro(String txt) {
        return colorText(negro, txt);
    }

    public static String amarillo(String txt) {
        return colorText(amarillo, txt);
    }

    public static String morado(String txt) {
        return colorText(morado, txt);
    }

    public static String cian(String txt) {
        return colorText(cian, txt);
    }

    // Colores brillantes de texto
    public static String negroBrillante(String txt) {
        return colorText(negroBrillante, txt);
    }

    // Colores de fondo ANSI
    public static String fondoRojo(String txt) {
        return colorText(fondoRojo, txt);
    }

    public static String fondoAzul(String txt) {
        return colorText(fondoAzul, txt);
    }

    public static String fondoMorado(String txt) {
        return colorText(fondoMorado, txt);
    }

    // Estilos de texto ANSI
    public static String negrita(String txt) {
        return colorText(negrita, txt);
    }

    public static String invisible(String txt) {
        return colorText(invisible, txt);
    }
}