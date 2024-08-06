package Presentacion.Menus;

import Logica.Objetos.Persona;
import Presentacion.Formato.Color;
import Presentacion.Formato.Texto;

public class ModPersona {

    public static Persona datosPersona() {
        Persona persona = new Persona();

        try {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Datos personales")));

            persona.setNombre(Texto.leerString(Color.cian(Color.negrita(" > Nombre: "))));
            persona.setApellidoPa(Texto.leerString(Color.cian(Color.negrita(" > Apellido Paterno: "))));
            persona.setApellidoMa(Texto.leerString(Color.cian(" > Apellido Materno: ")));

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de nacimiento")));
            int dia = Texto.leerInt(Color.cian(Color.negrita(" > Dia: ")));
            int mes = Texto.leerInt(Color.cian(Color.negrita(" > Mes: ")));
            int anio = Texto.leerInt(Color.cian(Color.negrita(" > Año: ")));
            persona.setFecNac(dia, mes, anio);

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Direccion")));
            persona.setColonia(Texto.leerString(Color.cian(" > Colonia: ")));
            persona.setCalle(Texto.leerString(Color.cian(" > Calle: ")));
            persona.setNumExt(Texto.leerInt(Color.cian(" > Numero Exterior: ")));
            persona.setNumInt(Texto.leerInt(Color.cian(" > Numero Interior: ")));
            persona.setCp(Texto.leerString(Color.cian(" > Codigo Postal: ")));

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Contacto")));
            persona.setTelefono(Texto.leerString(Color.cian(Color.negrita(" > Telefono : "))));
            persona.setCorreo(Texto.leerString(Color.cian(" > Correo: ")));

        } catch (Exception e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" DATO NO VALIDO")));
            return null;
        }

        return persona;
    }
}
