package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Persistencia.Conexion;

public class BdPersona {
    // ATRIBUTOS
    private String nombre, apellidoPa, apellidoMa, colonia, calle, telefono, correo;
    private int  id, numCasa, cp, numExt, numInt;
    private Date fecNac;

    private Conexion c = new Conexion();
    private Connection con = c.conectar();
    private Statement stmt = null;
    private ResultSet rs = null;

    public BdPersona(){}

    public BdPersona(int id){
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM persona WHERE idPersona = " + id);

            while (rs.next()) {
                id = rs.getInt("idPersona");
                nombre = rs.getString("nombre");
                apellidoPa = rs.getString("apellidoPa");
                apellidoMa = rs.getString("apellidoMa");
                fecNac = rs.getDate("fecNac");
                colonia = rs.getString("colonia");
                calle = rs.getString("calle");
                numExt = rs.getInt("numExt");
                numInt = rs.getInt("numInt");
                cp = rs.getInt("cp");
                telefono = rs.getString("telefono");
                correo = rs.getString("correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidPa() {
        return this.apellidoPa;
    }

    public void setApellidPa(String apellidPa) {
        this.apellidoPa = apellidPa;
    }

    public String getApellidoMa() {
        return this.apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCasa() {
        return this.numCasa;
    }

    public void setNumCasa(int numCasa) {
        this.numCasa = numCasa;
    }

    public int getCp() {
        return this.cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getNumExt() {
        return this.numExt;
    }

    public void setNumExt(int numExt) {
        this.numExt = numExt;
    }

    public int getNumInt() {
        return this.numInt;
    }

    public void setNumInt(int numInt) {
        this.numInt = numInt;
    }

    public Date getFechaNacimiento() {
        return this.fecNac;
    }

    public void setFechaNacimiento(Date fecNac) {
        this.fecNac = fecNac;
    }



}
