package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String bd="member_plus";
    private String host, url, user, password;
    private String error;
    private String sqlState;
    private int opc = 1;

    //Conexion
    public Connection conectarHost(){
        Connection connection = null;

        url = "jdbc:mysql://"+ host +"/" + bd+"?connectionTime = 3000";
        try{
            connection = DriverManager.getConnection(url, user, password);            
        } catch(SQLException e){
            this.error = e.getMessage();
            this.sqlState = e.getSQLState();
        }
        return connection;
    }

    public Connection conectar(){
        if (opc == 1) setVlan(); 
        else        setLocal(); 
        return conectarHost();
    }

    private void setLocal(){
        host = "localhost";
        user = "root";
        password = "";
    }
    private void setVlan(){
        host = "192.168.60.20";
        user = "admin";
        password = "12345678";
    }


    public String getBd() {
        return this.bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(int opcion) {
        this.opc = opcion;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return this.error;
    }

    public String getSqlState() {
        return this.sqlState;
    }
    
}