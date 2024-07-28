package Logica.Objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Beneficio {
    // ATRIBUTOS
    private String nombre;
    private int idBeneficio, porcPuntos, porcentajeCashBack;
    private Date fecVen, fecInicio;

    // CONSTRUCTORES
    public Beneficio() {
    }

    public Beneficio(int idBeneficio, String nombre, int porcPuntos, int porcentajeCashBack, Date fecVen,
            Date fecInicio) {
        this.idBeneficio = idBeneficio;
        this.nombre = nombre;
        this.porcPuntos = porcPuntos;
        this.porcentajeCashBack = porcentajeCashBack;
        this.fecVen = fecVen;
        this.fecInicio = fecInicio;
    }

    // METODOS
    public void modificarTargeta(String nombre, String porcPuntosStr,
            String porcentajeCashBackStr, String fecVenStr, String fecInicioStr) {
        this.setNombre(nombre);
        // no puse el idBeneficio
        this.setPorcPuntos(porcPuntosStr);
        this.setPorcentajeCashBack(porcentajeCashBackStr);
        this.setFecVen(fecVenStr);
        this.setFecInicio(fecInicioStr);
    }

    // GETTERS AND SETTERS
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre.trim();
    }

    public int getIdBeneficio() {
        return this.idBeneficio;
    }

    public int getPorcPuntos() {
        return this.porcPuntos;
    }

    public void setPorcPuntos(int porcPuntos) {
        if (porcPuntos <= 0)
            throw new IllegalArgumentException("El porcentaje no puede ser negativo o cero");
        this.porcPuntos = porcPuntos;
    }

    public void setPorcPuntos(String porcPuntosStr) {
        try {
            int porcPuntos = Integer.parseInt(porcPuntosStr);
            this.setPorcPuntos(porcPuntos);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El porcentaje no es válido");
        }
    }

    public int getPorcentajeCashBack() {
        return this.porcentajeCashBack;
    }

    public void setPorcentajeCashBack(int porcentajeCashBack) {
        if (porcentajeCashBack <= 0)
            throw new IllegalArgumentException("El porcentaje de cash back no puede ser negativo o cero");
        this.porcPuntos = porcentajeCashBack;
    }

    public void setPorcentajeCashBack(String porcentajeCashBackStr) {
        try {
            int porcentajeCashBack = Integer.parseInt(porcentajeCashBackStr);
            this.setPorcentajeCashBack(porcentajeCashBack);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número no es válido");
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

    public Date getFecInicio() {
        return this.fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        if (fecInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede estar vacía");
        this.fecInicio = fecInicio;
    }

    public void setFecInicio(String fecInicioStr) {
        try {
            Date fecInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fecInicioStr);
            this.fecInicio = fecInicio;
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de inicio no es válida", e);
        }
    }

}