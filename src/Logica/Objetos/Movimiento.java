package Logica.Objetos;


import Logica.FormatoFecha;
import java.sql.Date;

public class Movimiento {
    // ATRIBUTOS
    private int idMovimiento;
    private String comentario, estado;
    private  Date fechaMov;
    private Usuario usuario;
    private TipoMovimiento tipo;

    // CONSTRUCTORES

    public Movimiento(int idMovimiento, String comentario, String estado, Date fechaMov, Usuario usuario, TipoMovimiento tipo) {
        this.idMovimiento = idMovimiento;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaMov = fechaMov;
        this.usuario = usuario;
        this.tipo = tipo;
       }    
    
    // METODOS

    //COMUNICACION CON PERSISTENCIA
    public static Movimiento importarMovimientos(Object [] datos){
        Movimiento movimiento = new Movimiento(
            (int)datos[0], 
            (String)datos[1],
            (String)datos[2],
            (Date)datos[3],
            Usuario.importarUsuarios((int)datos[4]),
            TipoMovimiento.importarTipo((int)datos[5])
        );
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