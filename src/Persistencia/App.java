package Persistencia;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("ola papusa");
        Conexion conexion = new Conexion();
        
        System.out.println("Conexion a LocalHost");
        conexion.setLocal();
        conexion.conectar();

        System.out.println();
        System.out.println("Conexion a 192.168.60.20");
        conexion.setVlan();
        conexion.conectar();
    }
}
