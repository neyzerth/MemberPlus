package Logica.Objetos;

import Persistencia.Tablas.TarjetaEnt;
import java.sql.Date;
import java.util.Random;
import Logica.FormatoFecha;

public class Tarjeta {
    // ATRIBUTOS
    private String numTarjeta;
    private float saldo;
    private int idTarjeta, puntos;
    private Date fecExp, fecVen;
    private boolean activo;
    private Cliente cliente;
    private Nivel nivel;

    // CONSTRUCTORES

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String numTarjeta, float saldo,
     int puntos, Date fecExp, Date fecVen, boolean activo, Cliente cliente, Nivel nivel) {
        this.idTarjeta = idTarjeta;
        this.numTarjeta = numTarjeta;
        this.saldo = saldo;
        this.puntos = puntos;
        this.fecExp = fecExp;
        this.fecVen = fecVen;
        this.activo = activo;
        this.cliente =  cliente;
        this.nivel = nivel;
    }

    // METODOS
    

    //COMUNICACION CON PERSISTENCIA
    public static Tarjeta importarTarjeta(Object[] datos){

        Tarjeta tarjeta = new Tarjeta(
            (int) datos[0],
            (String) datos[1],  
            (float) datos[2],
            (int) datos[3],
            (Date) datos[4],
            (Date) datos[5],
            (boolean) datos[6],
            (Cliente) datos[7],
            (Nivel) datos[8]
        );
        return tarjeta;
    }

    public static Tarjeta importarTarjeta(int id){
        TarjetaEnt tarjetaBd = new TarjetaEnt();
        Object[] datos = tarjetaBd.obtenerTarjetaPorId(id);

        return importarTarjeta(datos);
    }
    
    public static Tarjeta importarTarjeta(String numTarjeta){
        TarjetaEnt tarjetaBd = new TarjetaEnt();
        Object[] datos = tarjetaBd.obtenerTarjetaPorNum(numTarjeta);

        return importarTarjeta(datos);
    }

    //CRUD de tarjeta
    public boolean insertarTarjeta(){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.insertarTarjetaDB(numTarjeta,fecExp,fecVen,activo,saldo,puntos,cliente.getIdCliente() ,nivel.getIdNivel());
    }

    public boolean actualizarTarjeta(){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.actualizarTarjetaDB(idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente.getIdCliente(), nivel.getIdNivel());
    }

    public boolean validarNumTarjeta(){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.existeNumTarjeta(numTarjeta);
    }

    public static boolean validarNumTarjeta(String numTarjeta){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.existeNumTarjeta(numTarjeta);
    }

    public boolean validarIdTarjeta(){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.existeIdTarjeta(idTarjeta);
    }
    
    public boolean validarIdTarjeta(int idTarjeta){
        TarjetaEnt tarjeta = new TarjetaEnt();
        return tarjeta.existeIdTarjeta(idTarjeta);
    }
    //public boolean 


    // Método para generar un número de tarjeta de 16 dígitos con los primeros 4 dígitos fijos
    public static String generarNumeroTarjeta() {
        Random random = new Random();

        String primerosCuatro = "1729";

        // Generar los siguientes 12 dígitos aleatorios
        StringBuilder resto = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digitoAleatorio = random.nextInt(10); // El 10 se refiere a los numeors del 0 - 9
            resto.append(digitoAleatorio);
        }

        return primerosCuatro + resto.toString();
    }

    // GETTERS AND SETTERS

    public int getIdTarjeta() {
        return this.idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    public String getNumTarjeta() {
        return this.numTarjeta;
    }


    public void setNumTarjeta(String numTarjetaStr) {
        try {
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

    public void setFecExp(String fecExpStr) {
        this.fecExp = FormatoFecha.fecha(fecExpStr);
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
        this.fecVen = FormatoFecha.fecha(fecVenStr);
    }

    public void setFecVen(int dia, int mes, int anio) {
        this.fecVen = FormatoFecha.fecha(dia, mes, anio);
    }

    public boolean isActivo() {
        return this.activo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        if (activo == false)
            // esto es solo para que no lo marque como error
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