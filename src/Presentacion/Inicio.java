package Presentacion;

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