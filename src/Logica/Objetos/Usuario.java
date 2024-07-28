package Logica.Objetos;

import java.util.Date;
import Persistencia.Tablas.UsuarioEnt;

public class Usuario extends Persona {
    // ATRIBUTOS
    private int id;
    private String nomUsuario, contrasena, rfc;
    
    // CONSTRUCTORES

    public Usuario(){}

    public Usuario(int id, String nomUsuario, String contrasena, String rfc){
        super();
        this.nomUsuario = nomUsuario;
        this.contrasena = contrasena;
        this.rfc = rfc;
        this.id = id;
    }

    public Usuario(int id, String nombre, String apellidoPa, String apellidoMa,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
        Date fecNac, String nomUsuario, String contrasena, String rfc, int idPersona
    ) {  
          
        // El constructor le faltaban atributos, y el id de persona se transfiere
        super(idPersona, nombre, apellidoMa, apellidoPa, colonia, calle, numExt, numInt, telefono, correo, cp, fecNac);
        this.nomUsuario = nomUsuario;
        this.contrasena = contrasena;
        this.rfc = rfc;
        this.id = id;
    }

    // METODOS

    public static Usuario[] importarUsuarios(){
        UsuarioEnt usuariosBd = new UsuarioEnt();
        Usuario [] usuarios = new Usuario[usuariosBd.obtenerCantRegistros()];
        Object [][] datos = usuariosBd.ejecutarSelect();

        for (int i = 0; i < usuarios.length; i++) {
            Object[] dato = datos[i];
            usuarios[i] = new Usuario((int) dato[0], (String) dato[1], (String) dato[2], (String) dato[3]);            
        }
        return usuarios;
    }


    public static Usuario iniciarSesion(String nomUsuario, String contrasena){
        UsuarioEnt usuario = new UsuarioEnt();
        Usuario sesion;
        if(usuario.existeUsuario(nomUsuario, contrasena)){
            Object[] datos = usuario.obtenerUsuarioPorSesion(nomUsuario, contrasena);
            sesion = new Usuario(
                (int) datos[0], 
                String.valueOf(datos[1]), 
                String.valueOf(datos[2]), 
                String.valueOf(datos[3])
            );
            return sesion;
        }
        return null;
    }

    public boolean validarUsuario(){
        UsuarioEnt usuario = new UsuarioEnt();
        return usuario.existeUsuario(nomUsuario, contrasena);
            

    }

    // GETTERS AND SETTERS
    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasenia) {
        this.contrasena = contrasenia;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
    public int getId() {
        return this.id;
    }

    private void setId(int id){
        this.id = id;
    }

    //no se va a modificar el idUsuario


}