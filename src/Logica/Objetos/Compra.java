package Logica.Objetos;


import Logica.FormatoFecha;
import Persistencia.Tablas.CompraEnt;
import Persistencia.Tablas.Compra_BeneficioEnt;

import java.sql.Date;

public class Compra {
    // ATRIBUTOS
    private int idCompra, porcentajePunto, descuento;
    private Date fechaCompra;
    private float total;
    private Tarjeta tarjeta;
    private Beneficio []beneficios;

    // CONSTRUCTORES

    public Compra(int idCompra, int porcentajePunto, int descuento, Date fechaCompra, float total, Tarjeta tarjeta) {
        this.idCompra = idCompra;
        this.porcentajePunto = porcentajePunto;
        this.descuento = descuento;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.tarjeta = tarjeta;
    }
    //COMUNICACION CON PERSISTENCIA
    public static Compra importarCompras(Object [] datos){

        Compra compra = new Compra(
            (int) datos[0],
            (int) datos[1],
            (int) datos[2],
            (Date) datos[3],
            (float) datos[4],
            (Tarjeta) datos[5]
            );
        return compra;
    }

    public static Compra importarCompras(int id){
        CompraEnt compraBd = new CompraEnt();
        Object[] datos = compraBd.obtenerCompraPorIdDB(id);

        return importarCompras(datos);
    }

    public static Compra[] importCompras(){
        CompraEnt compraBd = new CompraEnt();
        Compra[] compras = new Compra[compraBd.obtenerCantRegistros()];
        Object[][] datos = compraBd.ejecutarSelect();
        for (int i = 0; i < datos.length; i++) {
            compras[i] = importarCompras(datos[i]);
        }
        return compras;
    }

    /*public static Beneficio [] importarBeneficios(int IdTarjeta){
        Compra_BeneficioEnt compra_BeneficioEnt = new Compra_BeneficioEnt();

    }*/

    //CRUD COMPRA
    public boolean insertarCompras(){
        CompraEnt compra = new CompraEnt();
        return compra.insertarCompraDB(fechaCompra,porcentajePunto,descuento,tarjeta.getIdTarjeta(),total);
    }

    public boolean actualizarCompra(){
        CompraEnt compra = new CompraEnt();
        return compra.actualizarCompraDB(idCompra,fechaCompra,porcentajePunto,descuento,tarjeta.getIdTarjeta(),total);
    }

    public boolean validarCompra(){
        CompraEnt compra = new CompraEnt();
        return compra.existeCompra(fechaCompra, tarjeta.getNumTarjeta());
    }
    
    public static boolean eliminarCompra(int idCompra){
        CompraEnt compra = new CompraEnt();
        return compra.eliminarCompraDB(idCompra);
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