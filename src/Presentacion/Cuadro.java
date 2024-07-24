package Presentacion;

public class Cuadro {
    public static void main(String[] args) {
        System.out.println(Color.blanco("╔──────────────────────────────────────╗"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("1. Módulo de Ventas") + Color.blanco("                  ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("2. Módulo de Clientes") + Color.blanco("                ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("3. Módulo de Tarjeta") + Color.blanco("                 ║"));
        System.out.println(
                Color.blanco("║ ") + Color.amarillo("4. Salir") + Color.blanco("                             ║"));
        System.out.println(Color.blanco("╚══════════════════════════════════════╝" + "\n"));
    }
}
