package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Persistencia.Conexion;

public class UsuarioEnt {
    private int id, persona, rol;
    private String nombreUsuario, contrasena, rfc;

    private static Conexion c = new Conexion();

    public UsuarioEnt(){

    }

    // METODOS
    public static UsuarioEnt selectUsuario(int id){
        UsuarioEnt usuario =  new UsuarioEnt();
        String query = "SELECT * FROM usuario WHERE idUsuario = " + id;

        try (Connection con = c.conectar();
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                usuario.id = rs.getInt("idUsuario");
                usuario.nombreUsuario = rs.getString("nombreUsuario");
                usuario.contrasena = rs.getString("contrasena");
                usuario.rfc = rs.getString("rfc");
                usuario.id = rs.getInt("idUsuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    //
    public static List<UsuarioEnt> selectAllUsuario(){
        List<UsuarioEnt> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";

        try (Connection con = c.conectar();
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                usuarios.add(selectUsuario(rs.getInt(1)));
            }

            return usuarios;

        } catch (SQLException e) {
            return null;
        }
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersona() {
        return this.persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    public int getRol() {
        return this.rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
}
