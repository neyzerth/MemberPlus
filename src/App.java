//-- Active: 1719932866993@@127.0.0.1@3306@member_plus
import Logica.Cliente;
import Logica.Persona;
import Logica.Usuario;
public class App {
    public static void main(String[] args) throws Exception {
        Cliente u = new Cliente();
        u.setFecNac("2005-01-30");
        //prueba de la impresion de datos
        u.imprimirEdad();
    }
}
