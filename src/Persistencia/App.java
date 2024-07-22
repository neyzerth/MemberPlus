package Persistencia;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();
        
        conexion.conectar();

    }
}
