package Logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento {
    // ATRIBUTOS
    private int idMovimiento;
    private String comentario, estado;
    private  Date fechaMov;

    // CONSTRUCTORES

    public Movimiento(int idMovimiento, String comentario, String estado, Date fechaMov) {
        this.idMovimiento = idMovimiento;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaMov = fechaMov;
    }    
    
    // METODOS
    public void modificarMovimiento(String comentario, String estado, String fechaMovStr){
        this.setComentario(comentario);
        this.setEstado(estado);
        this.setFechaMov(fechaMovStr);
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
        try {
            Date fechaMov = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMovStr);
            this.fechaMov = fechaMov;
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de movimiento no es válida", e);
        }
    }
    
}