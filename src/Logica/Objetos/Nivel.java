package Logica;

public class Nivel {
    // ATRIBUTOS
    private int idNivel, anualidad, costoApertura;
    private String nombre;

    // CONSTRUCTORES
    public Nivel(){}


    public Nivel(int idNivel, int anualidad, int costoApertura, String nombre) {
        this.idNivel = idNivel;
        this.anualidad = anualidad;
        this.costoApertura = costoApertura;
        this.nombre = nombre;
    }

    // METODOS
    public void modificarNivel(String anualidadStr, String costoAperturaStr, String nombre){
        this.setAnualidad(anualidadStr);
        this.setCostoApertura(costoAperturaStr);
        this.setNombre(nombre);
    }

    // GETTERS AND SETTERS

    public int getIdNivel() {
        return this.idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public int getAnualidad() {
        return this.anualidad;
    }

    public void setAnualidad(int anualidad) {
        if (anualidad <= 0)
            throw new IllegalArgumentException("La anualidad no puede ser negativo o cero");
        this.anualidad = anualidad;
    }

    public void setAnualidad(String anualidadStr) {
        try {
            int anualidad = Integer.parseInt(anualidadStr);
            this.setAnualidad(anualidad);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La anualidad no es válida");
        }
    }

    public int getCostoApertura() {
        return this.costoApertura;
    }

    public void setCostoApertura(int costoApertura) {
        if (costoApertura <= 0)
            throw new IllegalArgumentException("El costo de apertura no puede ser negativo o cero");
        this.costoApertura = costoApertura;
    }

    public void setCostoApertura(String costoAperturaStr) {
        try {
            int costoApertura = Integer.parseInt(costoAperturaStr);
            this.setAnualidad(costoApertura);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El costo de apertura no es válido");
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}