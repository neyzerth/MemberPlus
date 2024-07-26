package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Persistencia.Conexion;

public class PersonaEnt {
    // ATRIBUTOS
    private String nombre, apellidoPa, apellidoMa, colonia, calle, telefono, correo;
    private int  id, numCasa, cp, numExt, numInt;
    private Date fecNac;

    private static Conexion c = new Conexion();

    // CONSTRUCTORES
    public PersonaEnt(){}

    // METODOS
    public static PersonaEnt selectPersona(int id){
        PersonaEnt persona =  new PersonaEnt();
        String query = "SELECT * FROM persona WHERE idPersona = " + id;

        try (Connection con = c.conectar();
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                persona.id = rs.getInt("idPersona");
                persona.nombre = rs.getString("nombre");
                persona.apellidoPa = rs.getString("apellidoPa");
                persona.apellidoMa = rs.getString("apellidoMa");
                persona.fecNac = rs.getDate("fecNac");
                persona.colonia = rs.getString("colonia");
                persona.calle = rs.getString("calle");
                persona.numExt = rs.getInt("numExt");
                persona.numInt = rs.getInt("numInt");
                persona.cp = rs.getInt("cp");
                persona.telefono = rs.getString("telefono");
                persona.correo = rs.getString("correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    //
    public static List<PersonaEnt> selectAllPersona(){
        List<PersonaEnt> personas = new ArrayList<>();
        String query = "SELECT * FROM persona";

        try (Connection con = c.conectar();
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                personas.add(selectPersona(rs.getInt(1)));
            }

            return personas;

        } catch (SQLException e) {
            return null;
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPa() {
        return this.apellidoPa;
    }

    public void setApellidoPa(String apellidPa) {
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

    public Date getFecNac() {
        return this.fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }



}
