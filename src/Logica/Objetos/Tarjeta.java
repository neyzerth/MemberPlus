package Logica.Objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Tarjeta {
    // ATRIBUTOS
    private String  numTarjeta;
    private float saldo;
    private int puntos;
    private Date fecExp, fecVen;
    private boolean activo;

    // CONSTRUCTORES

    public Tarjeta() {
    }

    public Tarjeta(String numTarjeta, float saldo, int puntos, Date fecExp, Date fecVen, boolean activo) {
        this.numTarjeta = numTarjeta;
        this.saldo = saldo;
        this.puntos = puntos;
        this.fecExp = fecExp;
        this.fecVen = fecVen;
        this.activo = activo;
    }


    // GETTERS AND SETTERS

    public String getNumTarjeta() {
        return this.numTarjeta;
    }
    

    public void setNumTarjeta(String numTarjeta) {
        try {
            this.numTarjeta = numTarjeta;
            this.numTarjeta = generarNumeroTarjeta();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La tarjeta no es válida");
        }
    }
    

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        if (saldo <= 0)
            throw new IllegalArgumentException("El saldo no puede ser inferior a 0");
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
            throw new IllegalArgumentException("Los puntos no pueden ser inferior a 0");
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

      // Método para generar un número de tarjeta de 16 dígitos con los primeros 4 dígitos fijos
        public static String generarNumeroTarjeta() {
        Random random = new Random();

        String primerosCuatro = "1729";  

        // Generar los siguientes 12 dígitos aleatorios
        StringBuilder resto = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digitoAleatorio = random.nextInt(10);
            resto.append(digitoAleatorio);
        }

        
        return primerosCuatro + resto.toString();
    }





}
