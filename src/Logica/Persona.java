package Logica;

import java.util.Date;

public class Persona {
    private String nombre, apellidPa, apellidoMa, colonia, calle, telefono, correo;
    private int numCasa, cp;
    private Date fechaNacimiento;

    public Persona(){}

    public Persona(String nombre, String apellidPa, String apellidoMa, String colonia, String calle, String telefono,
            String correo, int numCasa, int cp, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidPa = apellidPa;
        this.apellidoMa = apellidoMa;
        this.colonia = colonia;
        this.calle = calle;
        this.telefono = telefono;
        this.correo = correo;
        this.numCasa = numCasa;
        this.cp = cp;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidPa() {
        return this.apellidPa;
    }

    public void setApellidPa(String apellidPa) {
        this.apellidPa = apellidPa;
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

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
