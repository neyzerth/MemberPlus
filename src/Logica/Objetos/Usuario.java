package Logica.Objetos;

import java.sql.Date;

import Persistencia.Tablas.UsuarioEnt;

public class Usuario extends Persona {
    // ATRIBUTOS
    private int idUsuario;
    private String nomUsuario, contrasena, rfc;
    public Rol rol;
    
    // CONSTRUCTORES

    public Usuario(){}

    public Usuario(int idUsuario, String nomUsuario, String contrasena, String rfc, Persona persona, Rol rol){
        super(persona);
        this.nomUsuario = nomUsuario;
        this.contrasena = contrasena;
        this.rfc = rfc;
        this.idUsuario = idUsuario;
        this.rol = rol;

    }

    public Usuario(int id, String nomUsuario, String contrasena, String rfc, int idPersona, int idRol,
        String nombre, String apellidoPa, String apellidoMa,
        String colonia, String calle, int numExt, int numInt, String cp, String telefono, String correo,
        Date fecNac
    ) {  
    
        // El constructor le faltaban atributos, y el id de persona se transfiere
        super(idPersona, nombre, apellidoMa, apellidoPa, fecNac, colonia, calle, numExt, numInt, telefono, correo, cp);
        this.nomUsuario = nomUsuario;
        this.contrasena = contrasena;
        this.rfc = rfc;
        this.idUsuario = id;
    }

    // METODOS
    
    public static Usuario importarUsuarios(Object [] datos){
        Persona persona = Persona.importarPersonas((int) datos[4]);

        Usuario usuario = new Usuario(
            (int) datos[0],
            (String) datos[1],
            (String) datos[2], 
            (String) datos[3],
            persona,
            Rol.importarRoles((int) datos[5])
        );            
        return usuario;
    }

    public static Usuario[] importarUsuarios(){
        UsuarioEnt usuariosBd = new UsuarioEnt();
        Usuario [] usuarios = new Usuario[usuariosBd.obtenerCantRegistros()];
        Object [][] datos = usuariosBd.ejecutarSelect();

        for (int i = 0; i < usuarios.length; i++) {  
            Object[] dato = datos[i];

            usuarios[i] = importarUsuarios((int) dato[0]);            
        }
        return usuarios;
    }
    public static Usuario importarUsuarios(int id){
        UsuarioEnt usuariosBd = new UsuarioEnt();
        Object [] datos = usuariosBd.obtenerUsuarioPorIdDB(id);

        return importarUsuarios(datos);            
    }

    public boolean insertarUsuario(){
        UsuarioEnt usuario = new UsuarioEnt();
        if(validarPersona(this.getIdPersona()))
            return usuario.insertarUsuarioDB(this.nomUsuario, this.contrasena, this.rfc , this.getIdPersona(), this.rol.getIdRol());

        return false;
    }
    public boolean actualizarUsuario(){
        UsuarioEnt usuario = new UsuarioEnt();
        return usuario.actualizarUsuarioDB(this.idUsuario, this.nomUsuario, this.contrasena, this.rfc , this.getIdPersona(), this.rol.getIdRol());

    }

    public static boolean eliminarUsuario(int id){
        UsuarioEnt usuario = new UsuarioEnt();
        return usuario.eliminarUsuarioDB(id);
    }


    public static Usuario iniciarSesion(String nomUsuario, String contrasena){
        UsuarioEnt usuario = new UsuarioEnt();
        Usuario sesion;
        if(usuario.existeUsuario(nomUsuario, contrasena)){
            Object[] datos = usuario.obtenerUsuarioPorSesion(nomUsuario, contrasena);
            sesion = importarUsuarios(datos);
            return sesion;
        }
        return null;
    }

    

    public boolean validarUsuario(){
        UsuarioEnt usuario = new UsuarioEnt();
        return usuario.existeUsuario(this.nomUsuario, this.contrasena);
    }

    public static boolean validarUsuario(int id){
        UsuarioEnt usuario = new UsuarioEnt();
        return usuario.existeRegistro(id);
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
    
    public int getIdUsuario() {
        if(this.idUsuario > 0)
            return this.idUsuario;
        
        UsuarioEnt usuarioBd = new UsuarioEnt();

        Object [] datos = usuarioBd.ejecutarSelectPorAtributos(
            nomUsuario, contrasena, rfc, getIdPersona(), rol.getIdRol()
        );
        Usuario usuario = Usuario.importarUsuarios(datos);

        this.idUsuario = usuario.getIdUsuario();

        return this.idUsuario;
    }

    /*public void setIdUsuario(int id) {
        this.idUsuario = id;
    }*///Lo mismo que en logica

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public void setRol(int idRol) {
        if(idRol < 1)
            try {
                throw new Exception("El id del rol no puede ser menor a 1");
            } catch (Exception e) {
                this.rol = null;
            }
            
        this.rol = Rol.importarRoles(idRol);
    }

}