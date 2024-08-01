package Logica.Objetos;
import Logica.FormatoFecha;
import Persistencia.Tablas.BeneficioEnt;

import java.sql.Date;

public class Beneficio {
    // ATRIBUTOS
    private String nombre;
    private int idBeneficio, porcPuntos, porcentajeCashBack,descuento;
    private Date fecVen, fecInicio;

    // CONSTRUCTORES
    public Beneficio() {
    }

    public Beneficio(int idBeneficio, String nombre, Date fecInicio,Date fecVen, int porcPuntos, int porcentajeCashBack, int descuento) {
        this.idBeneficio = idBeneficio;
        this.nombre = nombre;
        this.fecInicio = fecInicio;
        this.fecVen = fecVen;
        this.porcPuntos = porcPuntos;
        this.porcentajeCashBack = porcentajeCashBack;
        this.descuento=descuento;
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

    //COMUNICACION CON PERSISTENCIA
    public static Beneficio importarBeneficios(Object [] datos){
        if (datos[4] == null) 
            datos[4] = 0;
        if (datos[5] == null) 
            datos[5] = 0;
        if (datos[6] == null) 
            datos[6] = 0;
        
        Beneficio beneficio = new Beneficio(
            (int) datos[0],
            (String) datos[1],
            (Date) datos[2],
            (Date) datos[3],
            (int) datos[4],
            (int) datos[5],
            (int) datos[6]
        );
        return beneficio;
    }

    public static Beneficio importarBeneficios(int id){
        BeneficioEnt beneficioBd = new BeneficioEnt();
        Object [] datos = beneficioBd.obtenerBeneficioPorIdDB(id);
        return importarBeneficios(datos);
    }

    public static Beneficio[] importarBeneficios(){
        BeneficioEnt beneficioBd = new BeneficioEnt();
        Beneficio[] beneficios = new Beneficio[beneficioBd.obtenerCantRegistros()];
        Object [][] datos = beneficioBd.ejecutarSelect();

        for (int i = 0; i < datos.length; i++) {
            beneficios[i] = importarBeneficios(datos[i]);
        }
        return beneficios;
    }

    public boolean insetarBeneficio(){
        BeneficioEnt beneficio = new BeneficioEnt();
        return beneficio.insertarBeneficioDB( nombre, (java.sql.Date) fecInicio, (java.sql.Date) fecVen, 
            porcPuntos, porcentajeCashBack, descuento);
    }

    public boolean actualizarBeneficio(){
        BeneficioEnt beneficio = new BeneficioEnt();
        return beneficio.actualizarBeneficioDB(
            idBeneficio,nombre, (java.sql.Date) fecInicio,
        (java.sql.Date) fecVen, porcPuntos, porcentajeCashBack, descuento);
    }

    public boolean validarBeneficio(){
        BeneficioEnt beneficio =  new BeneficioEnt();
        return beneficio.existeBeneficio(nombre);
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
        this.fecVen = FormatoFecha.fecha(fecVenStr); //ejemplo
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
        this.fecInicio = FormatoFecha.fecha(fecInicioStr);
    }

}