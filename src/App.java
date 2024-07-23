import Persistencia.Conexion;

public class App {
    public static void main(String[] args) throws Exception {
        Conexion p = new Conexion();

        p.conectar();
        
    }
}
