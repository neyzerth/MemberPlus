package Logica.Objetos;

import Persistencia.Tablas.TipoMovimientoEnt;

public class TipoMovimiento{
    // ATRIBUTOS
    private int idTipoMovimiento;
    private String nombre,descripcion; 
    
    
    // CONSTRUCTORES 
    public TipoMovimiento(int idTipoMovimiento, String nombre, String descripcion) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    // public TipoMovimiento(int idMovimiento, String comentario, String estado, Date fechaMov,int idTipoMovimiento, String nombre, String descripcion) {
    //     super(idMovimiento,comentario,estado,fechaMov);
    //     this.idTipoMovimiento = idTipoMovimiento;
    //     this.nombre = nombre;
    //     this.descripcion = descripcion;
    // }


      // COMUNICACION CON PERSISTENCIA
        public static TipoMovimiento importarTipoMovimientos(Object[] datos) {
        if (datos[2] == null)
            datos[2] = "Sin descripcion";

        TipoMovimiento tipoMovimiento = new TipoMovimiento(
                (int) datos[0],
                (String) datos[1],
                (String) datos[2]);
        return tipoMovimiento;
    }

    public static TipoMovimiento importarTipoMovimientos(int id) {
        TipoMovimientoEnt tipoMovimientoBd = new TipoMovimientoEnt();
        Object[] datos = tipoMovimientoBd.obtenerTipoMovimientoPorIdDB(id);
        return importarTipoMovimientos(datos);
    }

    public static TipoMovimiento[] importarTipoMovimientos() {
        TipoMovimientoEnt tipoMovimientoBd = new TipoMovimientoEnt();
        TipoMovimiento[] tipoMovimientos = new TipoMovimiento[tipoMovimientoBd.obtenerCantRegistros()];
        Object[][] datos = tipoMovimientoBd.ejecutarSelect();

        for (int i = 0; i < datos.length; i++) {
            tipoMovimientos[i] = importarTipoMovimientos(datos[i]);
        }
        return tipoMovimientos;
    }

    //CRUD TipoMovientos


    public boolean insertarTipoMovimiento() {
        TipoMovimientoEnt tipoMovimientoEnt = new TipoMovimientoEnt();
        return tipoMovimientoEnt.insertarTipoMovimientoDB(nombre, descripcion);
    }

    public boolean actualizarTipoMovimiento() {
        TipoMovimientoEnt tipoMovimientoEnt = new TipoMovimientoEnt();
        return tipoMovimientoEnt.actualizarTipoMovimientoDB(idTipoMovimiento, nombre, descripcion);
    }

    public boolean validarTipoMovimiento() {
        TipoMovimientoEnt tipoMovimientoEnt = new TipoMovimientoEnt();
        return tipoMovimientoEnt.existeTipoMovimiento(nombre);
    }




    // GETTERS AND SETTERS

    public int getIdTipoMovimiento() {
        return this.idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

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

}