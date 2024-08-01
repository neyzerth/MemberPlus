package Logica.Objetos;


import Logica.FormatoFecha;
import java.sql.Date;

public class Compra {
    // ATRIBUTOS
    private int idCompra, porcentajePunto, descuento;
    private Date fechaCompra;
    private float total;

    // CONSTRUCTORES

    public Compra(int idCompra, int porcentajePunto, int descuento, Date fechaCompra, float total) {
        this.idCompra = idCompra;
        this.porcentajePunto = porcentajePunto;
        this.descuento = descuento;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    // METODOS
    public void modificarCompra(String porcentajePuntoStr, String descuentoStr, String fechaCompraStr,
            String totalStr) {
        this.setPorcentajePunto(porcentajePuntoStr);
        this.setDescuento(descuentoStr);
        this.setFechaCompra(fechaCompraStr);
        this.setTotal(totalStr);
    }

    // GETTERS AND SETTERS

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
        if (porcentajePunto <= 0)
            throw new IllegalArgumentException("El porcentaje no puede ser inferioir a 0");
        this.porcentajePunto = porcentajePunto;
    }

    public void setPorcentajePunto(String porcentajePuntoStr) {
        try {
            int porcentajePunto = Integer.parseInt(porcentajePuntoStr);
            this.setPorcentajePunto(porcentajePunto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El porcentaje no es válido");
        }
    }

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        if (descuento <= 0)
            throw new IllegalArgumentException("El descuento no puede ser inferioir a 0");
        this.descuento = descuento;
    }

    public void setDescuento(String descuentoStr) {
        try {
            int descuento = Integer.parseInt(descuentoStr);
            this.setDescuento(descuento);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El descuento no es válido");
        }
    }

    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        if (fechaCompra == null)
            throw new IllegalArgumentException("La fecha de compra no puede estar vacía");
        this.fechaCompra = fechaCompra;
    }

    public void setFechaCompra(String fechaCompraStr) {
    this.fechaCompra = FormatoFecha.fecha(fechaCompraStr);
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        if (total <= 0)
            throw new IllegalArgumentException("El total no puede ser inferioir a 0");
        this.total = total;
    }

    public void setTotal(String totalStr) {
        try {
            int total = Integer.parseInt(totalStr);
            this.setTotal(total);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El total no es válido");
        }
    }

}