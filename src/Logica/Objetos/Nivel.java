package Logica.Objetos;
import Persistencia.Tablas.NivelEnt;
import Persistencia.Tablas.Nivel_BeneficioEnt;
import java.sql.Date;

import Logica.FormatoFecha;

public class Nivel {
    // ATRIBUTOS
    private int idNivel, anualidad, costoApertura;
    private String nombre;
    public Beneficio [] beneficios;

    // CONSTRUCTORES
    public Nivel(){
        this.beneficios = new Beneficio[1];
    }


    public Nivel(int idNivel, String nombre,int anualidad, int costoApertura) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.anualidad = anualidad;
        this.costoApertura = costoApertura;
        this.beneficios = Nivel.importarBeneficios(this.idNivel);
    }

    // METODOS
    public void modificarNivel(String anualidadStr, String costoAperturaStr, String nombre){
        this.setAnualidad(anualidadStr);
        this.setCostoApertura(costoAperturaStr);
        this.setNombre(nombre);
    }

    //COMUNICACION CON PERSISTENCIA

    public static Nivel importarNiveles(Object [] datos){

        Nivel nivel = new Nivel(
            (int) datos[0],
            (String) datos[1],
            (int) datos[2],
            (int) datos[3]
        );
        return nivel;
    }

    public static Nivel importarNiveles(int id){
        NivelEnt nivelBd = new NivelEnt();
        Object[] datos = nivelBd.obtenerNivelPorIdDB(id);
        return importarNiveles(datos);
    }
    
    public static Beneficio [] importarBeneficios(int idNivel){
        Nivel_BeneficioEnt nivel_beneficioBd = new Nivel_BeneficioEnt();
        Object [][] datosBeneficios = nivel_beneficioBd.obtenerBeneficioPorIdNivel(idNivel);

        Beneficio [] beneficios = new Beneficio[datosBeneficios.length];

        for (int i = 0; i < beneficios.length; i++) {
            int puntos =(int) datosBeneficios[i][4];
            int cashback =(int) datosBeneficios[i][5];
            int descuento =(int) datosBeneficios[i][6];
            
            if(FormatoFecha.fechaActual().compareTo((Date) datosBeneficios[i][3]) > 0){
                datosBeneficios[i][4] = datosBeneficios[i][5] = datosBeneficios[i][6] = 0;
            }

            beneficios[i] = new Beneficio(
                (int) datosBeneficios[i][0],
                (String) datosBeneficios[i][1],
                (Date) datosBeneficios[i][2],
                (Date) datosBeneficios[i][3],
                puntos,
                cashback,
                descuento
            );
        }
        
        return beneficios;
    }

    public static Nivel[] importarNiveles(){
        NivelEnt nivelBd = new NivelEnt();
        Nivel[] niveles = new Nivel [nivelBd.obtenerCantRegistros()];
        Object[][] datos = nivelBd.ejecutarSelect();
        
        for (int i = 0; i < datos.length; i++) {
            niveles[i] = importarNiveles(datos[i]);
        }
        return niveles;
    }

    //CRUD NIVEL

    public boolean insertarNivel(){
        NivelEnt nivel = new NivelEnt();
        boolean insertado = nivel.insertarNivelDB(nombre, anualidad, costoApertura);
        if(insertado){
            Nivel_BeneficioEnt nivBen = new Nivel_BeneficioEnt();
            for (Beneficio beneficio : beneficios) {
                nivBen.insertarNivel_BeneficioDB(getIdNivel(), beneficio.getIdBeneficio());
            }
        }
        return insertado;
    }

    public boolean actualizarNivel(){
        NivelEnt nivel = new NivelEnt();
        return nivel.actualizarNivelDB(idNivel, nombre, anualidad, costoApertura);
    }

    public boolean validarNivel(){
        NivelEnt nivel = new NivelEnt();
        return nivel.existeNivel(nombre);
    }
    public static boolean validarNivel(int id){
        NivelEnt nivel = new NivelEnt();
        return nivel.existeRegistro(id);
    }
    
    public static boolean eliminarNivel(int id){
        NivelEnt nivel = new NivelEnt();
        return nivel.eliminarNivelDB(id);
    }
    
    // GETTERS AND SETTERS

    public int getIdNivel() {
        if(this.idNivel > 0)
            return this.idNivel;
        
        NivelEnt nivelBd = new NivelEnt();
        
        Object [] datos = nivelBd.ejecutarSelectPorAtributos(
            this.nombre, this.anualidad, this.costoApertura
        );
            
        Nivel nivel = Nivel.importarNiveles(datos);
        this.idNivel = nivel.getIdNivel();

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
        if (costoApertura < 0)
            throw new IllegalArgumentException("Cantidad no valida");
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

    public Beneficio[] getBeneficios() {
        return this.beneficios;
    }
    public Beneficio getBeneficio(int i) {
        return this.beneficios[i];
    }

    public void agregarBeneficio(Beneficio beneficio){
        if(this.beneficios[0] ==  null){
            this.beneficios[0] = beneficio;
            return;
        }
        if(!noRepetido(beneficio))
            throw new IllegalArgumentException("Este beneficio ya esta incluido");

        Beneficio[] beneficiosAux = new Beneficio[this.beneficios.length + 1];

        System.arraycopy(this.beneficios, 0, beneficiosAux, 0, this.beneficios.length);
        
        beneficiosAux[this.beneficios.length] = beneficio;
        this.beneficios = beneficiosAux;
    }

    //Comprobar que no ponga dos veces el mismo beneficio
    public boolean noRepetido(Beneficio beneficio){
        for (Beneficio b : this.beneficios) {
            if (b.getIdBeneficio() == beneficio.getIdBeneficio())
                return false;
        }
        return true;
    }

    public void agregarBeneficio(int idBeneficio){
        Beneficio beneficio = Beneficio.importarBeneficios(idBeneficio);
        agregarBeneficio(beneficio);
    }

    public void setBeneficios(Beneficio[] beneficios) {
        this.beneficios = beneficios;
    }

    

}