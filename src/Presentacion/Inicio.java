package Presentacion;

import Presentacion.Formato.Texto;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.IniciarSesion;

public class Inicio {
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