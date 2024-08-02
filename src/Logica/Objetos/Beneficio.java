package Logica.Objetos;
import Logica.FormatoFecha;
import Persistencia.Tablas.BeneficioEnt;

import java.sql.Date;

public class Beneficio {
    // ATRIBUTOS
    private String nombre;
    private int idBeneficio, porcPuntos, porcCashBack, porcDescuento;
    private Date fecVen, fecInicio;

    // CONSTRUCTORES
    public Beneficio() {
    }

    public Beneficio(int idBeneficio, String nombre, Date fecInicio,Date fecVen, int porcPuntos, int porcCashBack, int porcDescuento) {
        this.idBeneficio = idBeneficio;
        this.nombre = nombre;
        this.fecInicio = fecInicio;
        this.fecVen = fecVen;
        this.porcPuntos = porcPuntos;
        this.porcCashBack = porcCashBack;
        this.porcDescuento=porcDescuento;
    }

    // METODOS
    public void modificarTarjeta(String nombre, String porcPuntosStr,
            String porcentajeCashBackStr, String fecVenStr, String fecInicioStr) {
        this.setNombre(nombre);
        this.setPorcPuntos(porcPuntosStr);
        this.setPorcCashBack(porcentajeCashBackStr);
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
    
    public static Beneficio[] importarBeneficiosTarjeta(int idTarjeta){
        BeneficioEnt beneficioBd = new BeneficioEnt();
        Beneficio[] beneficios = new Beneficio[beneficioBd.obtenerCantRegistros()];
        Object [][] datos = beneficioBd.ejecutarSelect();

        for (int i = 0; i < datos.length; i++) {
            beneficios[i] = importarBeneficios(datos[i]);
        }
        return beneficios;
    }

    //CRUD BENEFICIO
    public boolean insetarBeneficio(){
        BeneficioEnt beneficio = new BeneficioEnt();
        return beneficio.insertarBeneficioDB( nombre, (java.sql.Date) fecInicio, (java.sql.Date) fecVen, 
            porcPuntos, porcCashBack, porcDescuento);
    }

    public boolean actualizarBeneficio(){
        BeneficioEnt beneficio = new BeneficioEnt();
        return beneficio.actualizarBeneficioDB(
            idBeneficio,nombre, (java.sql.Date) fecInicio,
        (java.sql.Date) fecVen, porcPuntos, porcCashBack, porcDescuento);
    }

    public boolean validarBeneficio(){
        BeneficioEnt beneficio =  new BeneficioEnt();
        return beneficio.existeBeneficio(nombre);
    }

    public static boolean validarBeneficio(int id){
        BeneficioEnt beneficio =  new BeneficioEnt();
        return beneficio.existeRegistro(id);
    }

    public static boolean eliminarBeneficio(int id){
        BeneficioEnt beneficio = new BeneficioEnt();
        return beneficio.eliminarBeneficioDB(id);
    }

    // GETTERS AND SETTERS
    
    public int getIdBeneficio() {
        if(this.idBeneficio < 1)
            this.setIdBeneficio();
        return this.idBeneficio;
    }
    public void setIdBeneficio() {
        BeneficioEnt beneficioBd = new BeneficioEnt();
        
        Object [] datos = beneficioBd.ejecutarSelectPorAtributos(
            nombre, fecInicio, fecVen, porcPuntos, porcCashBack, porcDescuento
        );

        Beneficio beneficio = importarBeneficios(datos);

        this.idBeneficio = beneficio.getIdBeneficio();
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre.trim();
    }


    public int getPorcPuntos() {
        return this.porcPuntos;
    }

    public void setPorcPuntos(int porcPuntos) {
        if (porcPuntos < 0)
            throw new IllegalArgumentException("El porcentaje de puntos no valido");
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

    public int getPorcCashBack() {
        return this.porcCashBack;
    }
    public float getCashBack() {
        return this.porcCashBack/100;
    }
    

    public void setPorcCashBack(int porcCashBack) {
        if (porcCashBack < 0 || porcCashBack > 100)
            throw new IllegalArgumentException("El porcentaje de cash back no es valido");
        this.porcPuntos = porcCashBack;
    }

    public void setPorcCashBack(String porcentajeCashBackStr) {
        try {
            int porcentajeCashBack = Integer.parseInt(porcentajeCashBackStr);
            this.setPorcCashBack(porcentajeCashBack);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número no es válido");
        }
    }

    public void setPorcDescuento(int porcDescuento){
        if (porcDescuento < 0 || porcDescuento > 100)
            throw new IllegalArgumentException("El porcentaje de cash back no puede ser negativo o cero");
        this.porcDescuento = porcDescuento;
    }
    public int getPorcDescuento(){
        return this.porcDescuento;
    }

    public float getDescuento(){
        return this.porcDescuento/100;
    }

    public Date getFecVen() {
        return this.fecVen;
    }

    public void setFecVen(Date fecVen) {
        if (fecVen == null)
            throw new IllegalArgumentException("La fecha de vencimineto no puede estar vacía");
        this.fecVen = fecVen;
    }

    public void setFecVen(int dia, int mes, int anio) {
        this.fecVen = FormatoFecha.fecha(dia, mes, anio);
    }
    public void setFecVen(String fecVenStr) {
        this.fecVen = FormatoFecha.fecha(fecVenStr);
    }

    public Date getFecInicio() {
        return this.fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        if (fecInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede estar vacía");
        this.fecInicio = fecInicio;
    }

    public void setFecInicio(int dia, int mes, int anio) {
        this.fecInicio = FormatoFecha.fecha(dia, mes, anio);
    }
    public void setFecInicio(String fecInicioStr) {
        this.fecInicio = FormatoFecha.fecha(fecInicioStr);
    }

}