package Presentacion;

import Logica.Objetos.Usuario;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.IniciarSesion;

public class Inicio {
    public static Usuario sesion;
    public static void main(String[] args) {
        do{
            Bienvenida.menu();
            IniciarSesion.interfaz();
        } while(true);
    }

}