package Logica.Objetos;

import Persistencia.Tablas.RolEnt;

public class Rol {
    // ATRIBUTOS
    private String nombre, descripcion;
    private int idRol;

    // CONSTRUCTORES
    public Rol() {
    }

    public Rol(int idRol, String nombre, String descripcion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // COMUNICACION CON PERSISTENCIA
    public static Rol importarRoles(Object[] datos) {
        if (datos[2] == null)
            datos[2] = "Sin descripcion ";

        Rol rol = new Rol(
                (int) datos[0],
                (String) datos[1],
                (String) datos[2]);
        return rol;
    }

        public static Rol importarRoles(int id){
        RolEnt rolBd = new RolEnt();
        Object[] datos = rolBd.obtenerRolPorIdDB(id);
        return importarRoles(datos);
    }

    public static Rol[] importarRoles(){
        RolEnt rolBd = new RolEnt();
        Rol[] roles = new Rol [rolBd.obtenerCantRegistros()];
        Object[][] datos = rolBd.ejecutarSelect();
        
        for (int i = 0; i < datos.length; i++) {
            roles[i] = importarRoles(datos[i]);
        }
        return roles;
    }

    //CRUD Rol

    public boolean insertarRol(){
        RolEnt rol = new RolEnt();
        return rol.insertarRolDB(nombre, descripcion);
    }

    public boolean actualizarRol(){
        RolEnt rol = new RolEnt();
        return rol.actualizarRolDB(idRol, nombre, descripcion);
    }

    public boolean validarRol(){
        RolEnt rol = new RolEnt();
        return rol.existeRol(nombre);
    }



    // GETTERS AND SETTERS

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return this.idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}