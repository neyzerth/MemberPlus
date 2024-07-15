package Logica;

import java.util.Date;

public class Compra {
    private int idCompra, porcentajePunto, descuento;
    private Date fechaCompra;
    private float total;

    public int getIdCompra() {
        return this.idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getPorcentajePunto() {
        return this.porcentajePunto;
    }

    public void setPorcentajePunto(int porcentajePunto) {
        this.porcentajePunto = porcentajePunto;
    }

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

 public Compra(int idCompra, int porcentajePunto, int descuento, Date fechaCompra, float total) {
  this.idCompra = idCompra;
  this.porcentajePunto = porcentajePunto;
  this.descuento = descuento;
  this.fechaCompra = fechaCompra;
  this.total = total;
  
 }


}