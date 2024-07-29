package Logica.Objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static Persona[] importarPersonas(){
        PersonaEnt personaBd = new PersonaEnt();
        Persona[] personas = new Persona[personaBd.obtenerCantRegistros()];
        Object [][] datos = personaBd.ejecutarSelect();

        for (int i = 0; i < personas.length; i++) {
            Object[] dato = datos[i];
            personas[i] = new Persona(
                (int) dato[0],
                (String) dato[1],
                (String) dato[2],
                (String) dato[3],
                (Date) dato[4],
                (String) dato[5],
                (String) dato[6],
                (int) dato[7],
                (int) dato[8],
                (String) dato[9],
                (String) dato[10],
                (String) dato[11]
            );            
        }
        return personas;
    }
    public static Persona importarPersonas(int id){
        PersonaEnt personaBd = new PersonaEnt();
        Persona persona = new Persona();

        Object [] datos = personaBd.obtenerPersonaPorIdDB(id);

        if(datos[7] == null)
            datos[7] = 0;
        if(datos[8] == null)
            datos[8] = 0;

        persona = new Persona(
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

    // Método para modificar y verificar los datos
    public void modificar(String nombre, String apellidoPa, String apellidoMa, String colonia,
            String calle, String numExtStr, String numIntStr, String cp, String telefono,
            String correo, String fecNacStr) {

        this.setNombre(nombre);
        this.setApellidoPa(apellidoPa);
        this.setApellidoMa(apellidoMa);
        this.setColonia(colonia);
        this.setCalle(calle);
        this.setNumExt(numExtStr);
        this.setNumInt(numIntStr);
        this.setCp(cp);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setFecNac(fecNacStr);
    }

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

    // Imprime solamente la edad
    public void imprimirEdad() {
        System.out.println("Edad: " + calcularEdad(this));
    }

    // GETTERS AND SETTERS
    public int getIdPersona() {
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
        if (apellidoMa == null || apellidoMa.trim().isEmpty())
            throw new IllegalArgumentException("El apellido materno no puede estar vacío");
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
        if (colonia == null || colonia.trim().isEmpty())
            throw new IllegalArgumentException("La colonia no puede estar vacía");
        this.colonia = colonia.trim();
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        if (calle == null || calle.trim().isEmpty())
            throw new IllegalArgumentException("La calle no puede estar vacía");
        this.calle = calle.trim();
    }

    public int getNumExt() {
        return this.numExt;
    }

    public void setNumExt(int numExt) {
        if (numExt <= 0)
            throw new IllegalArgumentException("El número exterior no puede ser negativo o cero");
        this.numExt = numExt;
    }

    public void setNumExt(String numExtStr) {
        try {
            int numExt = Integer.parseInt(numExtStr);
            this.setNumExt(numExt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número exterior no es válido");
        }
    }

    public int getNumInt() {
        return this.numInt;
    }

    public void setNumInt(int numInt) {
        if (numInt < 0)
            throw new IllegalArgumentException("El número interior no puede ser negativo");
        this.numInt = numInt;
    }

    public void setNumInt(String numIntStr) {
        try {
            int numInt = Integer.parseInt(numIntStr);
            this.setNumInt(numInt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número interior no es válido");
        }
    }

    public String getCp() {
        return this.cp;
    }

    public void setCp(String cp) {
        if (cp == null || cp.trim().isEmpty())
            throw new IllegalArgumentException("El código postal no puede estar vacío");
        this.cp = cp.trim();
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
        if (correo == null || correo.trim().isEmpty())
            throw new IllegalArgumentException("El correo no puede estar vacío");
        this.correo = correo.trim();
    }

    public Date getFecNac() {
        return this.fecNac;
    }

    public void setFecNac(Date fecNac) {
        if (fecNac == null)
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía");
        this.fecNac = fecNac;
    }

    public void setFecNac(String fecNacStr) {
        try {
            Date fecNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecNacStr);
            this.fecNac = fecNac;
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento no es válida", e);
        }
    }
}
