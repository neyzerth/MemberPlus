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

        System.out.println(Color.amarillo("   ,-~~-.___"));
        System.out.println(Color.amarillo("  / |  '     \\ "));
        System.out.println(Color.amarillo(" (  )        0"));
        System.out.println(Color.amarillo("  \\_/-, ,----'"));
        System.out.println(Color.amarillo("     ====           //"));
        System.out.println(Color.amarillo("    /  \\-'~;    /~~~(O)"));
        System.out.println(Color.amarillo("   /  __/~|   /       |"));
        System.out.println(Color.amarillo(" =(  _____| (_________|"));

        scanner.nextLine();

        Texto.limpiarPantalla();
    }
}
