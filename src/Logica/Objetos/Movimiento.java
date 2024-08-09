package Logica.Objetos;


import Logica.FormatoFecha;
import Logica.Sesion;
import Persistencia.Tablas.MovimientoEnt;
import java.sql.Date;

public class Movimiento {
    // ATRIBUTOS
    private int idMovimiento;
    private String comentario, estado;
    private  Date fechaMov;
    public Tarjeta tarjeta;
    public Usuario usuario;
    public TipoMovimiento tipo;

    // CONSTRUCTORES


    public Movimiento(int idMovimiento, Date fechaMov, String estado, String comentario,  Usuario usuario, Tarjeta tarjeta,TipoMovimiento tipo) {
        this.idMovimiento = idMovimiento;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaMov = fechaMov;
        this.usuario = usuario;
        this.tarjeta = tarjeta;
        this.tipo = tipo;
    }    

    public Movimiento(){}
    

    
    // METODOS

    //COMUNICACION CON PERSISTENCIA
    public static Movimiento importarMovimientos(Object [] datos){
        if (datos[2] == null)
            datos[2]="Sin comentario";
        if (datos[3] == null) 
            datos[3] = "Sin estado";

        Movimiento movimiento = new Movimiento(
            (int)datos[0], 
            (Date)datos[1],
            (String)datos[2],
            (String)datos[3],
            Usuario.importarUsuarios((int)datos[4]),
            Tarjeta.importarTarjeta((int)datos[5]),
            TipoMovimiento.importarTipoMovimientos((int)datos[6])
        );
        return movimiento;
    }

    public static Movimiento importarMovimientos(int id){
        MovimientoEnt movimientoBd = new MovimientoEnt();
        Object[] datos = movimientoBd.obtenerMovimientoPorIdDB(id);
        return importarMovimientos(datos);
    }
    
    //?
    public static Movimiento[] importarMovimientos(){
        MovimientoEnt movimientoBd = new MovimientoEnt();
        Movimiento[] movimientos = new Movimiento[movimientoBd.obtenerCantRegistros()];
        Object [][] datos = movimientoBd.ejecutarSelect();

        for (int i = 0; i < datos.length; i++) {
            movimientos[i] = importarMovimientos(datos[i]);
        }
        return movimientos;
    }

    //CRUD de movimiento
    
    private static boolean registrarMovimiento(String comentario, Tarjeta tarjeta, int idTipo){
        MovimientoEnt movimientoBd = new MovimientoEnt();
        Date fechaActual = new Date(System.currentTimeMillis());

        return movimientoBd.insertarMovimientoDB(fechaActual, "Finalizado", comentario, Sesion.getId(), tarjeta.getIdTarjeta(), idTipo);
    }

    public static boolean alta(String comentario, Tarjeta tarjeta){
        return Movimiento.registrarMovimiento(comentario,tarjeta, 1);
    }

    public static boolean renovacion(String comentario, Tarjeta tarjeta){
        return Movimiento.registrarMovimiento(comentario,tarjeta, 2);
    }
    
    public static boolean cancelacion(String comentario, Tarjeta tarjeta){
        return Movimiento.registrarMovimiento(comentario,tarjeta, 3);

    }
    
    public static boolean compra(String comentario, Tarjeta tarjeta){
        return Movimiento.registrarMovimiento(comentario,tarjeta, 4);

    }

    public boolean actualizarMovimiento( int idTarjeta){
        MovimientoEnt movimientoBd = new MovimientoEnt();
        return movimientoBd.actualizarMovimientoDB(idMovimiento, fechaMov, estado, comentario, usuario.getIdUsuario(), idTarjeta, tipo.getIdTipoMovimiento());
    }

    public boolean validarMovimiento(){
        MovimientoEnt movimientoEnt = new MovimientoEnt();
        return movimientoEnt.existeRegistro(idMovimiento);
    }

    public static boolean validarMovimiento(int idMovimiento){
        MovimientoEnt movimientoEnt = new MovimientoEnt();
        return movimientoEnt.existeRegistro(idMovimiento);
    }
    
    // GETTERS AND SETTERS

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_movimiento() {
        return this.idMovimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.idMovimiento = id_movimiento;
    }

    public Date getFechaMov() {
        return this.fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        if (fechaMov == null)
            throw new IllegalArgumentException("La fecha de movimiento no puede estar vacía");
        this.fechaMov = fechaMov;
    }

    public void setFechaMov(String fechaMovStr) {
        this.fechaMov = FormatoFecha.fecha(fechaMovStr);
    }

    public int getIdMovimiento() {
        return this.idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoMovimiento getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    
}