package Logica;

import java.util.Date;

public class Beneficio {
    private String nombre;
    private int idBeneficio, porcPuntos, porcentajeCashBack;
    private Date fecVen, fecInicio;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdBeneficio() {
        return this.idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public int getPorcPuntos() {
        return this.porcPuntos;
    }

    public void setPorcPuntos(int porcPuntos) {
        this.porcPuntos = porcPuntos;
    }

    public int getPorcentajeCashBack() {
        return this.porcentajeCashBack;
    }

    public void setPorcentajeCashBack(int porcentajeCashBack) {
        this.porcentajeCashBack = porcentajeCashBack;
    }

    public Date getFecVen() {
        return this.fecVen;
    }

    public void setFecVen(Date fecVen) {
        this.fecVen = fecVen;
    }

    public Date getFecInicio() {
        return this.fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }
    }