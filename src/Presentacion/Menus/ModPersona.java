package Presentacion.Menus;

import Logica.Objetos.Persona;
import Presentacion.Formato.Texto;

public class ModPersona {
    
    public static Persona datosPersona(){
        Persona persona = new Persona();

        try{
            System.out.println("DATOS PERSONALES");

            persona.setNombre(Texto.leerString("> *Nombre :"));
            persona.setApellidoPa(Texto.leerString("> *Apellido Paterno :"));
            persona.setApellidoMa(Texto.leerString("> Apellido Materno :"));

            System.out.println("> FECHA DE NACIMIENTO");
            int dia = Texto.leerInt("> Dia :");
            int mes = Texto.leerInt("> Mes :");
            int anio = Texto.leerInt("> Año :");
            persona.setFecNac(dia, mes, anio);

            System.out.println("DIRECCION");
            persona.setColonia(Texto.leerString("> Colonia :"));
            persona.setCalle(Texto.leerString("> Calle :"));
            persona.setNumExt(Texto.leerInt("> Numero Exterior :"));
            persona.setNumInt(Texto.leerInt("> Numero Interior :"));
            persona.setCp(Texto.leerString("> Codigo Postal :"));

            System.out.println("CONTACTO");
            persona.setTelefono(Texto.leerString("> Telefono :"));
            persona.setCorreo(Texto.leerString("> Correo :"));

        } catch (Exception e){
            System.out.println("DATO NO VALIDO");
            return null;
        }

        return persona;
    }
}
