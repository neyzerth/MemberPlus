package Logica.Objetos;


import Logica.FormatoFecha;
import Persistencia.Tablas.BeneficioEnt;
import Persistencia.Tablas.MovimientoEnt;
import java.sql.Date;

public class Movimiento {
    // ATRIBUTOS
    private int idMovimiento;
    private String comentario, estado;
    private  Date fechaMov;
    private Usuario usuario;
    private TipoMovimiento tipo;

    // CONSTRUCTORES

    //Constructores para inicializar un movimiento con los valores dados que son id del movimiento, el comentario
    //el estado del movimiento y la fecha del movimiento 

    public Movimiento(int idMovimiento, String comentario, String estado, Date fechaMov, Usuario usuario, TipoMovimiento tipo) {
        this.idMovimiento = idMovimiento;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaMov = fechaMov;
        this.usuario = usuario;
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
            (String)datos[1],
            (String)datos[2],
            (Date)datos[3],
            Usuario.importarUsuarios((int)datos[4]),
            TipoMovimiento.importarTipoMovimientos((int)datos[5])
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
    public boolean insertarMovimiento(int idTarjeta){
        MovimientoEnt movimientoBd = new MovimientoEnt();
        return movimientoBd.insertarMovimientoDB(fechaMov, estado, comentario, idMovimiento, idTarjeta, idMovimiento);
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
    
}