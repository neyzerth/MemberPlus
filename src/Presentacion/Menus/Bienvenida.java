package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class Bienvenida {
    public static String color1 = Color.cian;
    public static String color2 = Color.negroBrillante;
    public static void menu() {
        Texto.limpiarPantalla();

        Cuadro bienvenida = new Cuadro(
            Color.morado("Bienvenidos "), 
            Color.cian("a Member +")
        );
        bienvenida.centrado(true);
        bienvenida.setMaxLinea(54);
        
        System.out.println();
        System.out.println(logo);
        bienvenida.imprimirCuadro();

        System.out.println(Color.negro("PENEEEEEEEEE"));
        System.out.println(Color.negroBrillante("PENEEEEEEEEE"));
        Texto.esperarEnter(Color.cian("Enter para continuar..."));
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

    static String logo = 
        "                       " + Color.colorText(color2, ":***#*****#:")+"                   \r\n" + //
        "                  " + Color.colorText(color2, "     =#::::::::%=    ")+"               \r\n" + //
        "                " + Color.colorText(color2, "-@-+@  =#::::::::%- =@-#% ")+"            \r\n" + //
        "              " + Color.colorText(color2, ":@:::::=@+::::::::::*@:::::%# ")+"          \r\n" + //
        "       :=====" + Color.colorText(color2, "@=::::::::::::::::::::::::::::%#")+"=====:   \r\n" + //
        "     +@     " + Color.colorText(color2, "%#::::::::::::::::::::::::::::::@:")+"     @+ \r\n" + //
        "     @        " + Color.colorText(color2, "%*:::::::::*@%#*%@#-::::::::%*")+"        @ \r\n" + //
        "     @    " + Color.colorText(color2, "::::=@::::::-@-        :@=::::::@:::::")+"    @ \r\n" + //
        "     @   " + Color.colorText(color2, ":#-==========@            #===========@")+"    @ \r\n" + //
        "     @   " + Color.colorText(color2, ":*") +"*"+ Color.colorText(color1, "@@@@@@@@@@@=          .@@@@@@@@@@@@")+ "*    @ \r\n" + //
        "     @   " + Color.colorText(color2, ":*") +"*"+ Color.colorText(color1, "@@@@@@@@@@@@*        :@@@@@@@@@@@@@")+ "*    @ \r\n" + //
        "     @   " + Color.colorText(color2, ":*") +"*"+ Color.colorText(color1, "@@@@@@@@@@@@@%      :@@@@@@@@@@@@@@")+ "*    @ \r\n" + //
        "     @   " + Color.colorText(color2, ":*") +"*"+ Color.colorText(color1, "@@@@@@@@@@@@@@%    =@@@@@@@@@@@@@@@")+ "*    @ \r\n" + //
        "     @     *" + Color.colorText(color1, "@@@@@@@@@@@@@@@@  +@@@@@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@*@@@@@@@@@@-@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@:%:*@@@@*:%:@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@:    @@    :@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@:          :@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @     +" + Color.colorText(color1, "@@@@@@@@@@@:          :@@@@@@@@@@@@")+ "     @ \r\n" + //
        "     @                                              @ \r\n" + //
        "      @@##########################################@@  \r\n" //
        ;
}
