package Presentacion.Menus;

import Logica.FormatoFecha;
import Logica.Objetos.Persona;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ModPersona {

    public static void datosPersona(Persona persona) {
        int opc;

        do{
            System.out.println();
            
            System.out.println(Color.amarillo(Color.negrita(" ¿Que desea actualizar? ")));
            Cuadro cuadro = new Cuadro("Nombre", "Nacimiento", "Direccion", "Contacto");
            cuadro.agregarSalir();
            cuadro.imprimirCuadroNum();
            opc = Leer.entero(" > ");
            datos(persona, opc);
        } while(opc != 0);
    } 

    public static void datos(Persona persona, int opc){
        boolean reiniciar = true;
        switch (opc) {
            case 1:
                do {
                    try {
                        System.out.println(Color.amarillo(Color.negrita(" Nombre ")));
                        nombre(persona);
                        reiniciar = false;
                    } catch (Exception e) {
                        System.out.println("\n" + Color.rojo(e.getMessage()));
                    }
                } while (reiniciar);
                break;
            case 2:
                do {
                    try {
                        System.out.println(Color.amarillo(Color.negrita(" Fecha de nacimiento")));
                        nacimiento(persona);
                        reiniciar = false;
                    } catch (Exception e) {
                        System.out.println("\n" + Color.rojo(e.getMessage()));
                    }
                } while (reiniciar);
                break;
            case 3:
                do {
                    try {
                        System.out.println(Color.amarillo(Color.negrita(" Direccion")));

                        direccion(persona);
                        reiniciar = false;
                    } catch (Exception e) {
                        System.out.println("\n" + Color.rojo(e.getMessage()));
                        
                    }
                } while (reiniciar);
                break;
            case 4:
                do {
                    try {
                        System.out.println(Color.amarillo(Color.negrita(" Contacto")));

                        contacto(persona);
                        reiniciar = false;
                    } catch (Exception e) {
                        System.out.println("\n" + Color.rojo(e.getMessage()));
                    }
                } while (reiniciar);
                break;

            case 0:
                break;
            default:
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Opcion no valida")));
        }

    }

    public static void pedirDatos(Persona persona){
        ModPersona.datos(persona, 1);
        ModPersona.datos(persona, 2);
        ModPersona.datos(persona, 3);
        ModPersona.datos(persona, 4);
    }

    public static void nombre(Persona persona) {
        String nombreHold = "Juan";
        String pateHold = "Manuel";
        String mateHold = "Obrador";
        if (persona != null) {
            nombreHold = persona.getNombre() == null ? nombreHold : persona.getNombre();
            pateHold = persona.getApellidoPa() == null ? pateHold : persona.getApellidoPa();
            mateHold = persona.getApellidoMa() == null ? mateHold : persona.getApellidoMa();
        }

        persona.setNombre(Leer.cadena(Color.cian(Color.negrita(" > *Nombre: ")), nombreHold));
        persona.setApellidoPa(Leer.cadena(Color.cian(Color.negrita(" > *Apellido Paterno: ")), pateHold));
        persona.setApellidoMa(Leer.cadena(Color.cian(" > Apellido Materno: "), mateHold));
    }

    public static void nacimiento(Persona persona) {
        String diaHold = "DD";
        String mesHold = "MM";
        String anioHold = "AAAA";

        if (persona.getFecNac() != null) {
            diaHold = String.valueOf(FormatoFecha.getDia(persona.getFecNac()));
            mesHold = String.valueOf(FormatoFecha.getMes(persona.getFecNac()));
            anioHold = String.valueOf(FormatoFecha.getAnio(persona.getFecNac()));
        }
        int dia = Leer.entero(Color.cian(Color.negrita(" > *Dia: ")), diaHold);
        int mes = Leer.entero(Color.cian(Color.negrita(" > *Mes: ")), mesHold);
        int anio = Leer.entero(Color.cian(Color.negrita(" > *Año: ")), anioHold);
        persona.setFecNac(dia, mes, anio);
    }

    public static void direccion(Persona persona) {
        String coloniaHold = "El Refugio";
        String calleHold = "Quintas Campestre" ;
        String extHold = "";
        String intHold = "";
        String cpHold = "22253";
        if (persona != null) {
            coloniaHold = persona.getColonia() == null ? "" : persona.getColonia();
            calleHold = persona.getCalle() == null ? "" : persona.getCalle();
            extHold = persona.getNumExt() == 0 ? "" : String.valueOf(persona.getNumExt());
            intHold = persona.getNumInt() == 0 ? "" : String.valueOf(persona.getNumInt());
            cpHold = persona.getCp() == null ? "" : persona.getCp();
        }

        persona.setColonia(Leer.cadena(Color.cian(" > Colonia: "), coloniaHold));
        persona.setCalle(Leer.cadena(Color.cian(" > Calle: "), calleHold));
        persona.setNumExt(Leer.entero(Color.cian(" > Numero Exterior: "), extHold));
        persona.setNumInt(Leer.entero(Color.cian(" > Numero Interior: "), intHold));
        persona.setCp(Leer.cadena(Color.cian(" > Codigo Postal: "), cpHold));
    }

    public static void contacto(Persona persona) {
        String telefonoHold = "664 969 4700";
        String correoHold = "quejas@uttijuana.edu.mx";
        if (persona != null) {
            telefonoHold = persona.getTelefono() == null ? "" : persona.getTelefono();
            correoHold = persona.getCorreo() == null ? "" : persona.getCorreo();
        }

        persona.setTelefono(Leer.cadena(Color.cian(Color.negrita(" > *Telefono : ")), telefonoHold));
        persona.setCorreo(Leer.cadena(Color.cian(" > Correo: "), correoHold));
    }
}
