import Persistencia.Conexion;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("ola papusa");
        Conexion conexion = new Conexion();
        conexion.conectar();
    }
}
