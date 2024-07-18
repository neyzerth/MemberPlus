package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String db="almacen2d";
    String host="192.168.60.20";
    String url = "jdbc:mysql://"+ host +"/" + db;
    String user = "admin";    //Valores por defecto
    String password = "12345678";   //Valores por defecto
    
    //Conexion
    public Connection conectar(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos");
            connection.close();
        } catch(SQLException e){
            System.out.println("Fallo de conexion");

            e.printStackTrace();
        }
        return connection;
    }
    
}