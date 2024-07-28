package Logica.Objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarjeta {
    // ATRIBUTOS
    private long numTarjeta;
    private float saldo;
    private int puntos;
    private Date fecExp, fecVen;
    private boolean activo;

    // CONSTRUCTORES

    public Tarjeta() {
    }

    public Tarjeta(long numTarjeta, float saldo, int puntos, Date fecExp, Date fecVen, boolean activo) {
        this.numTarjeta = numTarjeta;
        this.saldo = saldo;
        this.puntos = puntos;
        this.fecExp = fecExp;
        this.fecVen = fecVen;
        this.activo = activo;
    }

    // METODOS
    public void modificarTarjeta(String numTarjetaStr, String saldoStr, String puntosStr, String fecExpStr, String fecVenStr, String activoStr){
        this.setNumTarjeta(numTarjetaStr);
        this.setSaldo(saldoStr);
        this.setPuntos(puntosStr);
        this.setFecExp(fecExpStr);
        this.setFecVen(fecVenStr);
        this.setActivo(activoStr);
    }

    // GETTERS AND SETTERS

    public long getNumTarjeta() {
        return this.numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        if (numTarjeta <= 0)
            throw new IllegalArgumentException("El numero de la tarjeta no puede ser negativo o cero");
        this.numTarjeta = numTarjeta;
    }

    public void setNumTarjeta(String numTarjetaStr) {
        try {
            int numTarjeta = Integer.parseInt(numTarjetaStr);
            this.setNumTarjeta(numTarjeta);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La tarjeta no es válida");
        }
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        if (saldo <= 0)
            throw new IllegalArgumentException("El saldo no puede ser negativo o cero");
        this.saldo = saldo;
    }

    public void setSaldo(String saldoStr) {
        try {
            int saldo = Integer.parseInt(saldoStr);
            this.setSaldo(saldo);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El saldo no es válido");
        }
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        if (puntos <= 0)
            throw new IllegalArgumentException("Los puntos no puede ser negativos o cero");
        this.puntos = puntos;
    }

    public void setPuntos(String puntosString) {
        try {
            int puntos = Integer.parseInt(puntosString);
            this.setPuntos(puntos);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los puntos no son validos");
        }
    }

    public Date getFecExp() {
        return this.fecExp;
    }

    public void setFecExp(Date fecExp) {
        if (fecExp == null)
            throw new IllegalArgumentException("La fecha de expiración no puede estar vacía");
        this.fecExp = fecExp;
    }

    public void setFecExp(String fecExpStr){
        try {
            Date fecExp = new SimpleDateFormat("yyyy-MM-dd").parse(fecExpStr);
            this.fecExp = fecExp;
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de expiración no es válida", e);
        }
    }

    public Date getFecVen() {
        return this.fecVen;
    }

    public void setFecVen(Date fecVen) {
        if (fecVen == null)
            throw new IllegalArgumentException("La fecha de vencimineto no puede estar vacía");
        this.fecVen = fecVen;
    }

    public void setFecVen(String fecVenStr) {
        try {
            Date fecVen = new SimpleDateFormat("yyyy-MM-dd").parse(fecVenStr);
            this.fecVen = fecVen;
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de vencimiento no es válida", e);
        }
    }

    public boolean isActivo() {
        return this.activo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        if (activo == false)
            //esto es solo para que no lo marque como error
            throw new IllegalArgumentException("El estado de activo no puede estar ser falso");
        this.activo = activo;
    }

    public void setActivo(String activoStr) {
        if (activoStr == null || activoStr.isEmpty())
        throw new IllegalArgumentException("El estado de activo no puede estar vacío");
        try {
            this.activo = Boolean.parseBoolean(activoStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("El estado de activo no es válido", e);
        }
    }
}
