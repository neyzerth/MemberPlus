package Presentacion.Menus;

import Logica.Objetos.Beneficio;
import Logica.Objetos.Nivel;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

public class ModTarjeta {
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                Color.morado("Administrar tarjetas"),
                Color.morado("Administrar Niveles de tarjetas"),
                Color.morado("Administrar beneficios"),
                Color.rojo("Volver al menú principal")
            );
            
            tarjeta.imprimirCuadroNum();

            System.out.println();

            int option = Texto.leerInt(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
                case 1: SubmodTarjeta.desplegarMenu();
                    break;
                case 2: SubmodNivel.desplegarMenu();
                    break;
                case 3: SubmodBeneficio.desplegarMenu();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }

    }
}

class SubmodTarjeta extends Menu{

    public SubmodTarjeta(){
        super("Tarjeta", "Tarjetas");
    }

    public static void desplegarMenu(){
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        modTarjeta.menu();
    }

    @Override
    public boolean registrar(){
        Tarjeta tarjeta = new Tarjeta();
        Tarjeta.generarNumeroTarjeta();

        return false;
    }

    @Override
    public boolean actualizar(int id){
        Tarjeta tarjeta = Tarjeta.importarTarjeta(id);
        return false;


    }

    @Override 
    public boolean eliminar(int id){
        return Tarjeta.eliminarTarjeta(id);
    }
    public boolean eliminar(String numTarjeta){
        return Tarjeta.eliminarTarjeta(numTarjeta);
    }

	@Override
	public void tabla() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tabla'");
	}

	@Override
	public boolean tabla(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tabla'");
	}

    //public Tarjeta pedirDatos(){
    //    Tarjeta tarjeta = new Tarjeta();

        //tarjeta.set
    //}

    //Pedir datos de la tarjeta

    
}

class SubmodNivel extends Menu {

    public SubmodNivel(){
        super("Nivel", "Niveles");
    }

    public static void desplegarMenu(){
        SubmodNivel modNivel = new SubmodNivel();
        modNivel.menu();
    }

    @Override
    public boolean registrar() {
        Nivel nivel = pedirDatos();

        if(nivel == null)
            return false;

        tabla(nivel.getIdNivel());
        return nivel.insertarNivel();
    }

    @Override
    public boolean actualizar(int id) {
        Nivel nivel = pedirDatos();
        if(nivel == null)
            return false;
        
        nivel.setIdNivel(id);
        return nivel.actualizarNivel();
    }

    @Override
    public boolean eliminar(int id) {
        return eliminar(id);
    }

    @Override
    public void tabla() {
        tabla = new Tabla("ID", "Nombre", "Anualidad");
        
        Nivel [] niveles = Nivel.importarNiveles();

        for (Nivel nivel : niveles) {
            tabla.agregarFila(
                nivel.getIdNivel(),
                nivel.getNombre(),
                nivel.getAnualidad()
            );
        }
        tabla.imprimirTablaSimple();

        
    }

    @Override
    public boolean tabla(int id) {
        Nivel nivel = Nivel.importarNiveles(id);
        if(!nivel.validarNivel())
            return false;
        
        tabla = new Tabla("ID", "Nombre", "Costo Apertura", "Anualidad", "Beneficios");
        

        tabla.agregarFila(
            nivel.getIdNivel(),
            nivel.getNombre(),
            nivel.getCostoApertura(),
            nivel.getAnualidad(),
            nombresBeneficios(nivel.getBeneficios())

        );
        tabla.imprimirTablaSimple();

        return true;
    }

    public Nivel pedirDatos(){
        Nivel nivel = new Nivel();
        try{
            nivel.setNombre(Texto.leerString("> *Nombre del nivel: "));
            nivel.setCostoApertura(Texto.leerInt("> *Costo de apertura: $"));
            nivel.setAnualidad(Texto.leerString("> *Costo de la anualidad: $"));
            return nivel;
        } catch (Exception e){
            Texto.esperarEnter("Dato no valido");
            return null;
        }
    }
    
    public String nombresBeneficios(Beneficio [] beneficios){
        String nomBeneficios = "";
        for (int i = 0; i < beneficios.length; i++) {
            nomBeneficios += beneficios[i].getNombre();
            if((i < beneficios.length - 1))
                nomBeneficios += " - ";
        }
        return nomBeneficios;
    }

}

class SubmodBeneficio extends Menu{

    public SubmodBeneficio(){
        super("Beneficio", "Beneficios");
    }

    public static void desplegarMenu(){
        SubmodBeneficio menuBeneficio = new SubmodBeneficio();
        menuBeneficio.menu();
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"),Color.amarillo( "Nombre"),Color.amarillo("Puntos"), Color.amarillo("Cashback"), Color.amarillo("Descuento"));

        Beneficio [] beneficios = Beneficio.importarBeneficios();

        for (Beneficio beneficio : beneficios) {  
            tabla.agregarFila(
                beneficio.getIdBeneficio(),
                beneficio.getNombre(),
                beneficio.getPorcPuntos()  + "%",
                beneficio.getPorcCashBack()  + "%",
                beneficio.getPorcDescuento() + "%"
            );
        }

        tabla.imprimirTablaSimple();
    }

    @Override
    public boolean tabla(int id) {
        if(!Beneficio.validarBeneficio(id))
            return false;

        tabla = new Tabla (Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Fecha de inicio"), Color.amarillo("Fecha de vencimiento"), Color.amarillo("Puntos"), Color.amarillo("Cashback"), Color.amarillo("Descuento"));

        Beneficio beneficio = Beneficio.importarBeneficios(id);

        tabla.agregarFila(
            beneficio.getIdBeneficio(),
            beneficio.getNombre(),
            beneficio.getFecInicio(),
            beneficio.getFecVen(),
            beneficio.getPorcPuntos()  + "%",
            beneficio.getPorcCashBack()  + "%",
            beneficio.getPorcDescuento() + "%"
        );

        tabla.imprimirTablaSimple();
        return true;
    }

    @Override
    public boolean registrar() {
        Beneficio beneficio = new Beneficio();
        try {
            beneficio = pedirDatos();

            if(beneficio != null){
                tabla(beneficio.getIdBeneficio());
                beneficio.insetarBeneficio();
                return true;
            }
            
        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita("Dato incorrecto")));
        }
        
        return false;
    }

    @Override
    public boolean actualizar(int id) {
        Beneficio beneficio = Beneficio.importarBeneficios(id);
        if(beneficio == null){
            System.out.println();
            Texto.esperarEnter(Color.rojo("Beneficio no encontrado"));
            return false;
        }

        try {
            beneficio = pedirDatos();

            if(beneficio != null){
                tabla(id);
                return beneficio.actualizarBeneficio();
            }

        } catch (Exception e) {
            Texto.esperarEnter("Dato incorrecto");
        }
        
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Beneficio.eliminarBeneficio(id);
    }

    public Beneficio pedirDatos(){
        Beneficio beneficio = new Beneficio();
        int dia, mes, anio;
        try {
            System.out.println();
            beneficio.setNombre(Texto.leerString(Color.cian(Color.negrita(" > Nombre del beneficio: "))));
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de inicio")));
            dia = Texto.leerInt(Color.cian(Color.negrita(" > Dia: ")));
            mes = Texto.leerInt(Color.cian(Color.negrita(" > Mes: ")));
            anio = Texto.leerInt(Color.cian(Color.negrita(" > Año: ")));
            beneficio.setFecInicio(dia, mes, anio);

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de vencimiento")));
            dia = Texto.leerInt(Color.cian(Color.negrita(" > Dia: ")));
            mes = Texto.leerInt(Color.cian(Color.negrita(" > Mes: ")));
            anio = Texto.leerInt(Color.cian(Color.negrita(" > Año: ")));
            beneficio.setFecVen(dia, mes, anio);
            
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Beneficios")));
            beneficio.setPorcPuntos(Texto.leerInt(Color.cian(Color.negrita(" > Porcentaje de puntos: "))));
            beneficio.setPorcCashBack(Texto.leerInt(Color.cian(Color.negrita(" > Porcentaje de CashBack: "))));
            beneficio.setPorcDescuento(Texto.leerInt(Color.cian(Color.negrita(" > Porcentaje de Descuento: "))));

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita("Dato incorrecto")));
            return null;
        }

        return beneficio;
        
    }

}

