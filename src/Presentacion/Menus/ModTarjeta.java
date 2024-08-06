package Presentacion.Menus;

import Logica.Objetos.Beneficio;
import Logica.Objetos.Nivel;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.*;
import Presentacion.Formato.*;

//------------ MODULO PRINCIPAL ---------
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

//------------ MODULO DE TARJETA ---------
class SubmodTarjeta extends Menu{

    public SubmodTarjeta(){
        super("Membresia", "Membresias");
        this.opciones = new Cuadro(
            Color.morado("Lista de " + modPlur),
            Color.morado("Información de " + modSing),
            Color.morado("Actualizar " + modSing),
            Color.morado("Eliminar "+ modSing),
            Color.rojo("Volver")
        );
    }



    public static void desplegarMenu(){
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        modTarjeta.menu();
    }

    @Override
    public boolean conexionMenus(int opcion){
        switch (opcion) {
            case 1: menuVerTodos();
                break;
            case 2: menuVerUno();
                break;
            case 3: menuActualizar();
                break;
            case 4: menuEliminar();
                break;
                
            case 5: return true;

            default:
                System.out.println();
                System.out.println(Color.rojo(" Opción inválida, por favor intente de nuevo."));
                Texto.esperar(1);
            break;
        }
        return false;

    }

    @Override
    public void menuVerUno(){
        Cuadro info = new Cuadro( Color.morado(" Informacion de " + modSing));
        
        Texto.limpiarPantalla();
        info.imprimirCuadro();

        tabla();

        System.out.println();
        String numTarjeta = Texto.leerString(Color.cian(" > Numero de " + modSing + " a ver: "));
        if(!tabla(numTarjeta)) { 
            System.out.println();
            Texto.esperarEnter(Color.rojo(" No existe " + modSing + " con numero de tarjeta " + numTarjeta + "..."));
            return;
        }
    
        Texto.esperarEnter();
    }

    @Override 
    public void menuActualizar(){

        Cuadro actualizar = new Cuadro(Color.morado(" Modificar informacion de " + modSing));
        Texto.limpiarPantalla();

        actualizar.imprimirCuadro();

        System.out.println();
        String numTarjeta = Texto.leerString(Color.cian(" > Numero de tarjeta a modificar: "));

        if(!tabla(numTarjeta)) { 
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" No existe Tajeta con el numero " + numTarjeta + "...")));
            return;
        }

        Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
        boolean salir= false;

        do{
            System.out.println("Que desea realizar?");
            System.out.println("Renovar $"+ tarjeta.nivel.getAnualidad() +"[1] - Cambiar nivel [2] - Salir [3]");
            int opc = Texto.leerInt("> ");
            switch (opc) {
                case 1:
                    
                    System.out.println("Fecha de vencimiento: " + tarjeta.getFecVen());
                    tarjeta.renovar();
                    System.out.println("Nueva fecha de vencimiento: " + tarjeta.getFecVen());
                    tarjeta.actualizarTarjeta();
                    System.out.println(Color.cian("Tarjeta renovada con exito..."));
                    Texto.esperarEnter();
                    
                    break;
                case 2:
                    System.out.println("Nivel actual: " + tarjeta.nivel.getNombre());
                    int idNivel = Texto.leerInt("> ID del nuevo nivel: ");
                    Nivel nivel = Nivel.importarNiveles(idNivel);
                    tarjeta.nivel = nivel;
                    tarjeta.actualizarTarjeta();
                    
                    break;
                case 3:
                    salir = true;
                    break;
            
                default:
                    Texto.esperarEnter("Opcion no valida");
                    break;
            }
        } while (!salir);
        
    }

    @Override
    public void menuEliminar(){
        boolean repetir = false;

        Cuadro eliminar = new Cuadro(
                Color.morado(" Eliminar " + modSing));
        
        do{
            Texto.limpiarPantalla();

            eliminar.imprimirCuadro();
            tabla();

            System.out.println();
            String numTarjeta = Texto.leerString(Color.cian(" > Numero de " + modSing + " a eliminar: "));

            if(!tabla(numTarjeta)) { 
                System.out.println();
                Texto.esperarEnter(Color.rojo(Color.negrita(" No existe " + modSing + " con numero " + numTarjeta + "...")));
                repetir = true;
                return;
            }

            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" ¿Seguro que desea eliminar este " + modSing + "?")));
            boolean conf = Texto.leerString (Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            System.out.println();

            if (conf)
                if(eliminar(numTarjeta)){
                    tabla();
                    System.out.println();
                    Texto.esperarEnter(Color.verde(" " + modSing + " eliminado con exito"));
                    repetir = false;
                    return;
                }
                else
                    System.out.println();
                    Texto.esperarEnter(Color.rojo(" Error al eliminar " + modSing));
        } while (repetir);
    }

    @Override
    public boolean registrar(){

        return false;
    }

    @Override 
    public boolean actualizar(int id){
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
        Tarjeta[] tarjetas = Tarjeta.importarTarjeta();
        
		tabla = new Tabla("Numero de tarjeta", "Fecha vencimiento", "Cliente", "Nivel");


        for (Tarjeta tarjeta : tarjetas) {
            tabla.agregarFila(
                Texto.tarjeta(tarjeta.getNumTarjeta()),
                Texto.fecha(tarjeta.getFecVen()),
                tarjeta.cliente.getNombre(),
                tarjeta.nivel.getNombre()
            );
        }

        tabla.imprimirTablaSimple();
	}

	@Override
	public boolean tabla(int id) {
        if(!Tarjeta.validarIdTarjeta(id))
            return false;
        Tarjeta tarjeta = Tarjeta.importarTarjeta(id);
		tabla = new Tabla("Numero de tarjeta", "Cliente", "Nivel", "Saldo", "Puntos", "Fecha vencimiento" );

        tabla.agregarFila(
            Texto.tarjeta(tarjeta.getNumTarjeta()),
            tarjeta.cliente.getNombre(),
            tarjeta.nivel.getNombre(),
            tarjeta.getSaldo(),
            tarjeta.getPuntos(),
            Texto.fecha(tarjeta.getFecVen())
        );

        tabla.imprimirTablaSimple();
        return true;
	}

	public boolean tabla(String numTarjeta) {
        if(!Tarjeta.validarNumTarjeta(numTarjeta))
            return false;
        Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
		return tabla(tarjeta.getIdTarjeta());
	}

    
}

//------------ MODULO DE NIVEL ---------
class SubmodNivel extends Menu {

    public SubmodNivel(){
        super("Nivel", "Niveles");
    }
    public static void desplegarNiveles(){
        SubmodNivel niveles = new SubmodNivel();
        niveles.tabla();
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
        return Nivel.eliminarNivel(id);
    }

    @Override
    public void tabla() {
        tabla = new Tabla("ID", "Nombre", "Anualidad");
        
        Nivel [] niveles = Nivel.importarNiveles();

        for (Nivel nivel : niveles) {
            tabla.agregarFila(
                nivel.getIdNivel(),
                nivel.getNombre(),
                Texto.moneda(nivel.getAnualidad())
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
            Texto.moneda(nivel.getCostoApertura()),
            Texto.moneda(nivel.getAnualidad()),
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
            SubmodBeneficio verBeneficios = new SubmodBeneficio();
            
            boolean agregarNivel = false;
            verBeneficios.tabla();
            do{
                int idBeneficio = Texto.leerInt("> Selecciona ID del beneficio: ");
                if(Beneficio.validarBeneficio(idBeneficio)){
                    try{
                        nivel.agregarBeneficio(idBeneficio);
                        System.out.println("Desea agregar otro beneficio?");
                        agregarNivel = 1 == Texto.leerInt("> SI[1] NO[2]: ");

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            } while (agregarNivel);
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

//------------ MODULO DE BENEFICIO ---------
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
                beneficio.insetarBeneficio();
                tabla(beneficio.getIdBeneficio());
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

