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

        Texto.esperarEnter(Color.cian("Enter para continuar..."));

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
    
    static String logo = "MMMMMMMMMMMWNWNkdooooooc;dNMWNWMMMMMMMMM\r\n" + //
                "MMMMMMMMWXko;:ocoOOkkkkl.,xxc,l0WMMMMMMM\r\n" + //
                "MMMNXKK0kddko;,lkOOOOOOx:,lxxc..:kKKKKXW\r\n" + //
                "W0dc;,':dkOOOOOOOOOOOOOOOOOOOOx:..,,,'.l\r\n" + //
                "OcoxxdlcoxOOOOOkdoooooodkOOOOOd:,cdxdc..\r\n" + //
                "xcxko:;..cxxxxlcoxkkkkxoloxxxd;..'''ld'.\r\n" + //
                "xcxd:,'..',,,'.,x0000000x;''.'...   ;o'.\r\n" + //
                "xcxdc,;oddoolc'.,kKXXXKOc',,,,,,,.  ;o'.\r\n" + //
                "xcxdc,:dxddoool,.'kXNNO:';;;;;;;,. .:d'.\r\n" + //
                "xcxxc,;dxxddoolc,..okx:,;;;;;;;;,..:xd'.\r\n" + //
                "xcxkkc:dxxdooollc;. ..,;:;;;;;;,,..lkd'.\r\n" + //
                "xcxkkc:dxdddolclcc;'',::,,;;;;;;,..lkd'.\r\n" + //
                "xcxkkc:dxxdoo:.':cc::::'..,;;;;;,..lkd'.\r\n" + //
                "xcxkkc:dxddoo:. ';:::;'. .,;;;;,,..lkd'.\r\n" + //
                "xcxkkc;dxxdoo:..;,'''',;..,;;;;,,..lkd'.\r\n" + //
                "xcxkkc:dxxdoo:.'oxdoodxxl',;;;;;,..lkd'.\r\n" + //
                "xcxkkc,cccc::,.:kOOO0OOOo'.''''''.'okd''\r\n" + //
                "0clool::::::::coodddddddl:;;;;;;;:coocck\r\n" + //
                "WKkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkOXM\r\n" + //
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM";
    static String logo2 = "..8@888888;\r\n" + //
                "  .  . .  .:88.% X S S X;X.;8S .  . .  .\r\n" + //
                "   .     %:X ;:S@%S%%%%:.8@%8S@8 .\r\n" + //
                "     .  8 Xt%S8X%tttttt@8t;%.t S    . . \r\n" + //
                " .8t:SS:S.tt;t:;ttt;tt;t..tt;.88:XSSS% @\r\n" + //
                ".8%@X@8t8;ttttttt.;%S;:ttttttt  @@8@@@; \r\n" + //
                ":.@SSSSXt.;tttt:Xt:88:%X:tttt.8%@8XXS8% \r\n" + //
                ":.@8%8%8@ %%%%8S8S@888S8.;;%%@8;@8@8X8S \r\n" + //
                ":.X 8%.X@S8@88S;8%888888888X8@8@8;.%X8S \r\n" + //
                "::888 t88888@88@%XS88888S@8888888X:XS8S \r\n" + //
                "::@:8 S8888888::88X888 8X%8:8:8% 8:@@8SS\r\n" + //
                ":.888:8888888888@SXtS.8888tX88X888@8S8% \r\n" + //
                ":;@ 8S888@88888;@88@88t:888t8@.% 8SS%8X \r\n" + //
                "::8S 8888@8888888888tX88%8%88S88XXXSS8%S\r\n" + //
                ":t88 88@X88888X8:@8888S8888X888%@@@%8@S \r\n" + //
                "::@ 8 8888@888@888 X888XX@88X8@X@8% 88X \r\n" + //
                ":.88 8888@88888 88@88S%%:SX88.S88S8SS:8X\r\n" + //
                ":;@ @8888X8888@: 8@;8@8@XX8%8%88.8%S88S \r\n" + //
                "::8 8SX8@888888tS;t888%:S88@88888S%X88XS\r\n" + //
                ":t88 8888S@8888t;88XXXS88888X8%8;8X 88; \r\n" + //
                ":;@ 8%8tXt8X88@X8XS8888;88%8@8S8888S888%\r\n" + //
                ".S88S8 X;8.8 S%S%8 88 8XSSXXXXS%%S%8S.@.\r\n" + //
                " t.8@8888@@@888@8@@X@@8@888888888888@ % \r\n" + //
                "  :::t:;:::::;::: ..  :;:ttt;tttt:.: .";
}
