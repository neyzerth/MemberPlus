package Presentacion.Formato;

public class Color {
    // Colores ANSI
    private static String reset =   "\u001B[0m";
    private static String blanco =  "\u001B[37m"; // Blanco
    private static String azul =    "\u001B[34m"; // Azul
    private static String verde =   "\u001B[32m"; // Verde
    private static String rojo =    "\u001B[31m"; // Rojo
    private static String negro =   "\u001B[30m"; // Negro
    private static String amarillo ="\u001B[33m"; // Amarillo
    private static String morado =  "\u001B[35m"; // Morado
    private static String cian =    "\u001B[36m"; // Cian

    private static String colorText(String color, String txt){
        return color + txt + reset;
    }

    public static String blanco(String txt){
        return colorText(blanco, txt);
    } 

    public static String azul(String txt){
        return colorText(azul, txt);
    } 

    public static String verde(String txt){
        return colorText(verde, txt);
    } 

    public static String rojo(String txt){
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
}