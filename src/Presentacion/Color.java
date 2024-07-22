package Presentacion;

public class Color {
    // Colores ANSI
    private static String reset = "\u001B[0m";
    private static String blanco = "\u001B[37m"; // Blanco
    private static String azul = "\u001B[34m"; // Azul
    private static String verde = "\u001B[32m"; // Verde
    private static String rojo = "\u001B[31m"; // Rojo

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

}
