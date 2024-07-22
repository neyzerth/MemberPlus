package Logica;

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

    // GETTERS AND SETTERS

    public long getNumTarjeta() {
        return this.numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Date getFecExp() {
        return this.fecExp;
    }

    public void setFecExp(Date fecExp) {
        this.fecExp = fecExp;
    }

    public Date getFecVen() {
        return this.fecVen;
    }

    public void setFecVen(Date fecVen) {
        this.fecVen = fecVen;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
