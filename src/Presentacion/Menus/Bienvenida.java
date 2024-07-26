package Presentacion.Menus;

import java.util.Scanner;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Bienvenida {
    public static void menu(Scanner scanner) {
        Texto.limpiarPantalla();

        Cuadro bienvenida = new Cuadro(
            Color.morado("   Bienvenidos "), 
            Color.cian("   a Member +")
        );
        bienvenida.imprimirCuadro();

        System.out.println(snoopy);

        Texto.esperarEnter();

        Texto.limpiarPantalla();
    }

    static String snoopy = Color.amarillo(
            "   ,-~~-.___\n" +
            "  / |  '     \\ \n" +
            " (  )        0\n" +
            "  \\_/-, ,----'\n" +
            "     ====           //\n" +
            "    /  \\-'~;    /~~~(O)\n" +
            "   /  __/~|   /       |\n" +
            " =(  _____| (_________|)\n"
        );
}
