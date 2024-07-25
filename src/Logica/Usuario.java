package Logica;

import java.util.Date;

public class Usuario extends Persona {
    // ATRIBUTOS
    private String nomUsuario, contrasenia, rfc;
    private int idUsuario;
    
    // CONSTRUCTORES
    public Usuario(){}

    public Usuario(String nombre, String apellidPa, String apellidoMa,
            String colonia, String calle, String telefono, String correo, int numCasa, int cp,
            Date fechaNacimiento, String nomUsuario, String contrasenia, String rfc, int idUsuario) {    
        //super(nombre, apellidPa, apellidoMa, colonia, calle, telefono, correo, numCasa, cp, fechaNacimiento);
        this.nomUsuario = nomUsuario;
        this.contrasenia = contrasenia;
        this.rfc = rfc;
        this.idUsuario = idUsuario;
    }

    // METODOS

    // GETTERS AND SETTERS
    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContrasena() {
        return this.contrasenia;
    }

    public void setContrasena(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}