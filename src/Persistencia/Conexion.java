package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String db="";
    String url = "" + db;
    String user = "root";    //Valores por defecto
    String password = "";   //Valores por defecto
    
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