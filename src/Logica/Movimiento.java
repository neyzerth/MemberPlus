package Logica;

import java.util.Date;

public class Movimiento {
   private int idMovimiento;
   private String comentario, estado;
   private  Date fechaMov;

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
        this.fechaMov = fechaMov;
    }


    public Movimiento(int idMovimiento, String comentario, String estado, Date fechaMov) {
        this.idMovimiento = idMovimiento;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaMov = fechaMov;
    }    
    
}