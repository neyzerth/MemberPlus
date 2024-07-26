package Logica.Objetos;

import java.util.Date;

public class Usuario extends Persona {
    // ATRIBUTOS
    private String nomUsuario, contrasenia, rfc;
    private int idUsuario;
    
    // CONSTRUCTORES
    public Usuario(){}

    public Usuario(int id, String nombre, String apellidoPa, String apellidoMa,
    String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
    Date fecNac, String nomUsuario, String contrasenia, String rfc, int idUsuario) {  
          
        // El constructor le faltaban atributos, y el id de persona se transfiere
        super(id, nombre, apellidoMa, apellidoPa, colonia, calle, numExt, numInt, telefono, correo, cp, fecNac);
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

    //no se va a modificar el idUsuario


}