package Presentacion;

import Presentacion.Formato.Color;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.Login;

public class Inicio {
    public static void main(String[] args) {
        Bienvenida.main(args);
        Login.interfaz();
    }
}