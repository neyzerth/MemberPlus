package Presentacion.Formato;

public class Color {
    // Colores ANSI
    public static String reset = "\u001B[0m";
    public static String blanco = "\u001B[37m"; // Blanco
    public static String azul = "\u001B[34m"; // Azul
    public static String verde = "\u001B[32m"; // Verde
    public static String rojo = "\u001B[31m"; // Rojo
    public static String negro = "\u001B[30m"; // Negro
    public static String amarillo = "\u001B[33m"; // Amarillo
    public static String morado = "\u001B[35m"; // Morado
    public static String cian = "\u001B[36m"; // Cian
    // Colores brillantes de texto
    public static String negroBrillante = "\u001B[90m";
    public static String rojoBrillante = "\u001B[91m";
    public static String verdeBrillante = "\u001B[92m";
    public static String amarilloBrillante = "\u001B[93m";
    public static String azulBrillante = "\u001B[94m";
    public static String moradoBrillante = "\u001B[95m";
    public static String cianBrillante = "\u001B[96m";
    public static String blancoBrillante = "\u001B[97m";
    // Colores de fondo ANSI
    public static String fondoNegro = "\u001B[40m";
    public static String fondoRojo = "\u001B[41m";
    public static String fondoVerde = "\u001B[42m";
    public static String fondoAmarillo = "\u001B[43m";
    public static String fondoAzul = "\u001B[44m";
    public static String fondoMorado = "\u001B[45m";
    public static String fondoCian = "\u001B[46m";
    public static String fondoBlanco = "\u001B[47m";
    // Colores brillantes de fondo
    public static String fondoNegroBrillante = "\u001B[100m";
    public static String fondoRojoBrillante = "\u001B[101m";
    public static String fondoVerdeBrillante = "\u001B[102m";
    public static String fondoAmarilloBrillante = "\u001B[103m";
    public static String fondoAzulBrillante = "\u001B[104m";
    public static String fondoMoradoBrillante = "\u001B[105m";
    public static String fondoCianBrillante = "\u001B[106m";
    public static String fondoBlancoBrillante = "\u001B[107m";
    // Estilos de texto ANSI
    public static String negrita = "\u001B[1m";
    public static String subrayado = "\u001B[4m";
    public static String invertido = "\u001B[7m";
    public static String invisible = "\u001B[8m";

    public static String colorText(String color, String txt) {
        return color + txt + reset;
    }

    public static String blanco(String txt) {
        return colorText(blanco, txt);
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

    public static String rojoBrillante(String txt) {
        return colorText(rojoBrillante, txt);
    }

    public static String verdeBrillante(String txt) {
        return colorText(verdeBrillante, txt);
    }

    public static String amarilloBrillante(String txt) {
        return colorText(amarilloBrillante, txt);
    }

    public static String azulBrillante(String txt) {
        return colorText(azulBrillante, txt);
    }

    public static String moradoBrillante(String txt) {
        return colorText(moradoBrillante, txt);
    }

    public static String cianBrillante(String txt) {
        return colorText(cianBrillante, txt);
    }

    public static String blancoBrillante(String txt) {
        return colorText(blancoBrillante, txt);
    }

    // Colores de fondo ANSI
    public static String fondoNegro(String txt) {
        return colorText(fondoNegro, txt);
    }

    public static String fondoRojo(String txt) {
        return colorText(fondoRojo, txt);
    }

    public static String fondoVerde(String txt) {
        return colorText(fondoVerde, txt);
    }

    public static String fondoAmarillo(String txt) {
        return colorText(fondoAmarillo, txt);
    }

    public static String fondoAzul(String txt) {
        return colorText(fondoAzul, txt);
    }

    public static String fondoMorado(String txt) {
        return colorText(fondoMorado, txt);
    }

    public static String fondoCian(String txt) {
        return colorText(fondoCian, txt);
    }

    public static String fondoBlanco(String txt) {
        return colorText(fondoBlanco, txt);
    }

    // Colores brillantes de fondo
    public static String fondoNegroBrillante(String txt) {
        return colorText(fondoNegroBrillante, txt);
    }

    public static String fondoRojoBrillante(String txt) {
        return colorText(fondoRojoBrillante, txt);
    }

    public static String fondoVerdeBrillante(String txt) {
        return colorText(fondoVerdeBrillante, txt);
    }

    public static String fondoAmarilloBrillante(String txt) {
        return colorText(fondoAmarilloBrillante, txt);
    }

    public static String fondoAzulBrillante(String txt) {
        return colorText(fondoAzulBrillante, txt);
    }

    public static String fondoMoradoBrillante(String txt) {
        return colorText(fondoMoradoBrillante, txt);
    }

    public static String fondoCianBrillante(String txt) {
        return colorText(fondoCianBrillante, txt);
    }

    public static String fondoBlancoBrillante(String txt) {
        return colorText(fondoBlancoBrillante, txt);
    }

    // Estilos de texto ANSI
    public static String negrita(String txt) {
        return colorText(negrita, txt);
    }

    public static String subrayado(String txt) {
        return colorText(subrayado, txt);
    }

    public static String invertido(String txt) {
        return colorText(invertido, txt);
    }

    public static String invisible(String txt) {
        return colorText(invisible, txt);
    }


}