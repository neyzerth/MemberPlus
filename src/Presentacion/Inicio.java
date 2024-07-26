package Presentacion;

import java.util.Scanner;

import Logica.Objetos.Usuario;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.Login;

public class Inicio {
    public static Usuario sesion;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bienvenida.menu(scan);
        Login.interfaz();
    }
}