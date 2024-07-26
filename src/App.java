//-- Active: 1719932866993@@127.0.0.1@3306@member_plus
import Persistencia.Conexion;

public class App {
    public static void main(String[] args) throws Exception {
        Conexion p = new Conexion();

        p.conectar();
    }
}
