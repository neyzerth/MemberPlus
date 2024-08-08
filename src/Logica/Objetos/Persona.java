package Logica.Objetos;


import java.util.Calendar;
import Logica.FormatoFecha;
import java.sql.Date;

import Persistencia.Tablas.PersonaEnt;


public class Persona {
    // ATRIBUTOS
    private String nombre, apellidoMa, apellidoPa, colonia, calle, telefono, correo, cp;
    private int idPersona, numExt, numInt;
    private Date fecNac;

    // CONSTRUCTORES
    public Persona() {
    }

    public Persona(int id) {
        Persona persona = importarPersonas(id);
        this.idPersona = id;
        this.nombre = persona.getNombre();
        this.apellidoMa = persona.getApellidoPa();
        this.apellidoPa = persona.getApellidoMa();
        this.fecNac = persona.getFecNac();
        this.colonia = persona.getColonia();
        this.calle = persona.getCalle();
        this.numExt = persona.getNumExt();
        this.numInt = persona.getNumInt();
        this.cp = persona.getCp();
        this.telefono = persona.getTelefono();
        this.correo = persona.getCorreo();
    }

    public Persona(int idPersona, String nombre, String apellidoPa, String apellidoMa , Date fecNac, String colonia, String calle,
        int numExt, int numInt, String cp, String telefono, String correo
    ) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPa = apellidoPa;
        this.apellidoMa = apellidoMa;
        this.fecNac = fecNac;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.numInt = numInt;
        this.cp = cp;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Persona(Persona persona){
        this(
            persona.getIdPersona(),
            persona.getNombre(),
            persona.getApellidoPa(),
            persona.getApellidoMa(),
            persona.getFecNac(),
            persona.getColonia(),
            persona.getCalle(),
            persona.getNumExt(),
            persona.getNumInt(),
            persona.getCp(),
            persona.getTelefono(),
            persona.getCorreo()
        );
    }

    //COMUNICACION CON PERSISTENCIA
    //METODOS ESTATICOS
    public static Persona importarPersonas(Object [] datos){

        if(datos[7] == null)
            datos[7] = 0;
        if(datos[8] == null)
            datos[8] = 0;
            
        Persona persona = new Persona(
            (int) datos[0],
            (String) datos[1],
            (String) datos[2],
            (String) datos[3],
            (Date) datos[4],
            (String) datos[5],
            (String) datos[6],
            (int) datos[7],
            (int) datos[8],
            (String) datos[9],
            (String) datos[10],
            (String) datos[11]
        );

        return persona;
    }

    public static Persona importarPersonas(int id){
        PersonaEnt personaBd = new PersonaEnt();
        Object [] datos = personaBd.obtenerPersonaPorIdDB(id);
        return importarPersonas(datos);
    }

    public static Persona[] importarPersonas(){
        PersonaEnt personaBd = new PersonaEnt();
        Persona[] personas = new Persona[personaBd.obtenerCantRegistros()];
        Object [][] datos = personaBd.ejecutarSelect();

        for (int i = 0; i < personas.length; i++) {
            personas[i] = importarPersonas(datos[i]);      
        }
        return personas;
    }

    public boolean insertarPersona(){
        PersonaEnt persona = new PersonaEnt();
        return persona.insertarPersonaDB(
            nombre, apellidoPa, apellidoMa, fecNac,
            colonia, calle, numExt, numInt, cp, telefono, correo
        );
    }

    public boolean actualizarPersona(){
        PersonaEnt persona = new PersonaEnt();
        return persona.actualizarPersonaDB(
            this.idPersona,
            nombre, apellidoPa, apellidoMa, fecNac,
            colonia, calle, numExt, numInt, cp, telefono, correo
        );
    }
    
    public boolean validarPersona(int id){
        PersonaEnt persona = new PersonaEnt();
        return persona.existeRegistro(id);
    }

    
    //METODOS

    // Método para calcular edad
    public int calcularEdad(Persona persona) {
        Date fechaNacimiento = persona.getFecNac();
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimientoCalendar = Calendar.getInstance();
        fechaNacimientoCalendar.setTime(fechaNacimiento);

        int añoActual = fechaActual.get(Calendar.YEAR);
        int añoNacimiento = fechaNacimientoCalendar.get(Calendar.YEAR);
        int mesActual = fechaActual.get(Calendar.MONTH);
        int mesNacimiento = fechaNacimientoCalendar.get(Calendar.MONTH);
        int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int diaNacimiento = fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH);

        int edad = añoActual - añoNacimiento;

        if (mesActual < mesNacimiento || (mesActual == mesNacimiento && diaActual < diaNacimiento)) {
            edad--;
        }

        return edad;
    }

    // GETTERS AND SETTERS
    public void setIdPersona(int id){
        if(id > 0)
            this.idPersona = id;
    }
    public void setIdPersona(){
        PersonaEnt personaBd = new PersonaEnt();

        Object [] datos = personaBd.ejecutarSelectPorAtributos(
            nombre, apellidoPa, apellidoMa, fecNac, colonia, calle, numExt, numInt,
            cp, telefono, correo
        );
        Persona persona = Persona.importarPersonas(datos);
        
        this.idPersona = persona.getIdPersona();

    }
    public int getIdPersona() {
        if(this.idPersona < 1)
            setIdPersona();
        
        return this.idPersona;
    }
    // solo se obtiene el id ya que este no se va a modificar

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre.trim();
    }

    public String getApellidoMa() {
        return this.apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa.trim();
    }

    public String getApellidoPa() {
        return this.apellidoPa;
    }

    public void setApellidoPa(String apellidoPa) {
        if (apellidoPa == null || apellidoPa.trim().isEmpty())
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío");
        this.apellidoPa = apellidoPa.trim();
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia.trim();
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle.trim();
    }

    public int getNumExt() {
        return this.numExt;
    }

    public void setNumExt(int numExt) {
        this.numExt = numExt;
    }


    public int getNumInt() {
        return this.numInt;
    }

    public void setNumInt(int numInt) {
        if (numInt < 0)
            throw new IllegalArgumentException("El número interior no puede ser negativo");
        this.numInt = numInt;
    }

    public String getCp() {
        return this.cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion(){
        String direccion = "";
        if(!calle.isBlank()) direccion += calle + " ";
        if(numExt != 0) direccion += numExt + " ";
        if(numInt != 0) direccion += numInt + " ";
        if(!cp.isBlank()) direccion += cp + " ";

        return direccion;
     }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty())
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        this.telefono = telefono.trim();
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        if(correo.isBlank()){
            this.correo = "";
            return;
        }
        if(!correo.contains("@"))
            throw new IllegalArgumentException("El correo electrónico debe contener '@'");
        
        String[] direccion = correo.split("@");
        if(direccion[1].contains("\\."))
            throw new IllegalArgumentException("El correo electrónico debe contener '.'");
            
        this.correo = correo;
    }

    public Date getFecNac() {
        return this.fecNac;
    }

    public void setFecNac(int dia, int mes, int anio) {
        this.fecNac = FormatoFecha.fecha(dia, mes, anio);
    }
}
