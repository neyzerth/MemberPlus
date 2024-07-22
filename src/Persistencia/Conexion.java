package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String bd="member_plus";
    String host, url, user, password;

    //Conexion
    public Connection conectar(){
        Connection connection = null;
        setLocal();
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a bd \'"+ bd +"\' en \'" + host +"\'");
            
        } catch(SQLException e){
            System.out.println("Fallo de conexion  bd \'"+ bd +"\' en \'" + host +"\': " + e.getMessage());
        }
        return connection;
    }

    private void setLocal(){
        host = "localhost";
        url = "jdbc:mysql://"+ host +"/" + bd;
        user = "root";
        password = "";
    }
    private void setVlan(){
        host = "192.168.60.20";
        url = "jdbc:mysql://"+ host +"/" + bd;
        user = "admin";
        password = "12345678";
    }
    
}