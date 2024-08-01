//-- Active: 1719932866993@@127.0.0.1@3306@member_plus
package Presentacion;

import Logica.Sesion;
import Logica.Objetos.Usuario;
import Presentacion.Formato.Texto;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.IniciarSesion;

public class Inicio {
    public static Usuario sesion;
    public static void main(String[] args) {
        do{
            try{
                Bienvenida.menu();
                IniciarSesion.interfaz();
            } catch (Exception e) {
                Texto.esperarEnter("Error: " + e.getMessage());
            }
        } while(true);
    }

}