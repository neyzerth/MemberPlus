package Presentacion.Menus;

import Logica.Objetos.Persona;
import Presentacion.Formato.*;

public class ModPersona {

    public static Persona datosPersona() {
        Persona persona = new Persona();

        try {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Datos personales")));

            persona.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre: "))));
            persona.setApellidoPa(Leer.cadena(Color.cian(Color.negrita(" > Apellido Paterno: "))));
            persona.setApellidoMa(Leer.cadena(Color.cian(" > Apellido Materno: ")));

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de nacimiento")));
            int dia = Leer.entero(Color.cian(Color.negrita(" > Dia: ")));
            int mes = Leer.entero(Color.cian(Color.negrita(" > Mes: ")));
            int anio = Leer.entero(Color.cian(Color.negrita(" > Año: ")));
            persona.setFecNac(dia, mes, anio);

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Direccion")));
            persona.setColonia(Leer.cadena(Color.cian(" > Colonia: ")));
            persona.setCalle(Leer.cadena(Color.cian(" > Calle: ")));
            persona.setNumExt(Leer.entero(Color.cian(" > Numero Exterior: ")));
            persona.setNumInt(Leer.entero(Color.cian(" > Numero Interior: ")));
            persona.setCp(Leer.cadena(Color.cian(" > Codigo Postal: ")));

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Contacto")));
            persona.setTelefono(Leer.cadena(Color.cian(Color.negrita(" > Telefono : "))));
            persona.setCorreo(Leer.cadena(Color.cian(" > Correo: ")));

        } catch (Exception e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(e.getMessage())));
            return null;
        }

        return persona;
    }
}
