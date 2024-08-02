package Presentacion.Menus;

import Logica.Objetos.Persona;
import Presentacion.Formato.Color;
import Presentacion.Formato.Texto;

public class ModPersona {

    public static Persona datosPersona() {
        Persona persona = new Persona();

        try {
            System.out.println();
            System.out.println(Color.cian(Color.negrita("Datos personales")));

            persona.setNombre(Texto.leerString(Color.amarillo(Color.negrita("> Nombre: "))));
            persona.setApellidoPa(Texto.leerString(Color.amarillo(Color.negrita("> Apellido Paterno: "))));
            persona.setApellidoMa(Texto.leerString(Color.amarillo("> Apellido Materno: ")));

            System.out.println();
            System.out.println(Color.cian(Color.negrita("Fecha de nacimiento")));
            int dia = Texto.leerInt(Color.amarillo(Color.negrita("> Dia: ")));
            int mes = Texto.leerInt(Color.amarillo(Color.negrita("> Mes: ")));
            int anio = Texto.leerInt(Color.amarillo(Color.negrita("> Año: ")));
            persona.setFecNac(dia, mes, anio);

            System.out.println();
            System.out.println(Color.cian(Color.negrita("Direccion")));
            persona.setColonia(Texto.leerString(Color.amarillo("> Colonia: ")));
            persona.setCalle(Texto.leerString(Color.amarillo("> Calle: ")));
            persona.setNumExt(Texto.leerInt(Color.amarillo("> Numero Exterior: ")));
            persona.setNumInt(Texto.leerInt(Color.amarillo("> Numero Interior: ")));
            persona.setCp(Texto.leerString(Color.amarillo("> Codigo Postal: ")));

            System.out.println();
            System.out.println(Color.cian(Color.negrita("Contacto")));
            persona.setTelefono(Texto.leerString(Color.amarillo(Color.negrita("> Telefono :"))));
            persona.setCorreo(Texto.leerString(Color.amarillo("> Correo: ")));

        } catch (Exception e) {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita("DATO NO VALIDO")));
            return null;
        }

        return persona;
    }
}
