package Logica.Objetos;
import Persistencia.Tablas.NivelEnt;
import Persistencia.Tablas.Nivel_BeneficioEnt;

public class Nivel {
    // ATRIBUTOS
    private int idNivel, anualidad, costoApertura;
    private String nombre;
    private Beneficio [] beneficios;

    // CONSTRUCTORES
    public Nivel(){}


    public Nivel(int idNivel, String nombre,int anualidad, int costoApertura) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.anualidad = anualidad;
        this.costoApertura = costoApertura;
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
        Beneficio [] beneficios = new Beneficio[nivel_beneficioBd.obtenerCantRegistros()];

        Object[] datos = nivel_beneficioBd.obtenerBeneficioPorIdNivel(idNivel);
        
        for (int i = 0; i < datos.length; i++) {
            beneficios[i] = Beneficio.importarBeneficios( (int) datos[i]);
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
        return nivel.insertarNivelDB(nombre, anualidad, costoApertura);
    }

    public boolean actualizarNivel(){
        NivelEnt nivel = new NivelEnt();
        return nivel.actualizarNivelDB(idNivel, nombre, anualidad, costoApertura);
    }

    public boolean validarNivel(){
        NivelEnt nivel = new NivelEnt();
        return nivel.existeNivel(nombre);
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

    public Beneficio[] getBeneficios() {
        return this.beneficios;
    }
    public Beneficio getBeneficio(int i) {
        return this.beneficios[i];
    }

    public void setBeneficios(Beneficio[] beneficios) {
        this.beneficios = beneficios;
    }

    

}