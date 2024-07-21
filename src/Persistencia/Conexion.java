package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String db="ejemplo";
    String host = "";
    String url = "jdbc:mysql://"+host+"/"+db;
    String user = "root";
    String password = "";

    //Conexion
    public Connection conectar(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos");
            connection.close();
        } catch(SQLException e){
            System.out.println("Fallo de conexion: " + e.getMessage());
        }
        return connection;
    }

    public void setVlan(){
        host = "192.168.60.20";
        url = "jdbc:mysql://"+ host +"/" + db;
        user = "admin";
        password = "12345678";
    }
    public void setLocal(){
        host = "localhost";
        url = "jdbc:mysql://"+ host +"/" + db;
        user = "root";
        password = "";
    }
    
}