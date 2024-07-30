//-- Active: 1719932866993@@127.0.0.1@3306@member_plus
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