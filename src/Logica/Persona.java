package Logica;

import Persistencia.Conexion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona {
    // ATRIBUTOS
    private String nombre, apellidoMa, apellidoPa, colonia, calle, telefono, correo, cp;
    private int numExt, numInt;
    private Date fecNac;

    // CONSTRUCTORES
    public Persona() {
    }

    public Persona(String nombre, String apellidoMa, String apellidoPa, String colonia, String calle, int numExt,
            int numInt, String cp, String telefono, String correo, Date fecNac) {
        this.nombre = nombre;
        this.apellidoMa = apellidoMa;
        this.apellidoPa = apellidoPa;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.numInt = numInt;
        this.cp = cp;
        this.telefono = telefono;
        this.correo = correo;
        this.fecNac = fecNac;
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

    // GETTERS AND SETTERS
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
