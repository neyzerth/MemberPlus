package Presentacion.Menus;

import Logica.Objetos.Beneficio;
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
                case 1: //submodTarjeta();
                    break;
                case 2:
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

//class SubmodTarjeta extends Menu{
//
//    public SubmodTarjeta(){
//        super("Tarjeta", "Tarjetas");
//    }
//
//    public static void desplegarMenu(){
//        SubmodTarjeta modTarjeta = new SubmodTarjeta();
//        modTarjeta.menu();
//    }
//
//    @Override
//    public boolean registrar(){
//        Tarjeta tarjeta = new Tarjeta();
//
//        return false;
//    }
//
//    @Override
//    public boolean actualizar(int id){
//
//    }
//
//    @Override 
//    public boolean eliminar(int id){
//        return Tarjeta.eliminarTarjeta(id);
//    }
//    public boolean eliminar(String numTarjeta){
//        return Tarjeta.eliminarTarjeta(numTarjeta);
//    }
//
//    
//}

class SubmodNivel extends Menu {

    public SubmodNivel(){
        super("Nivel", "Nivel");
    }

    @Override
    public boolean registrar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrar'");
    }

    @Override
    public boolean actualizar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public boolean eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
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
        tabla = new Tabla("ID", "Nombre", "Puntos", "Cashback", "Descuento");

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

        tabla = new Tabla("ID", "Nombre", "Fecha de inicio", "Fecha de vencimiento", "Puntos", "Cashback", "Descuento");

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
            Texto.esperarEnter("Dato incorrecto");
        }
        
        return false;
    }

    @Override
    public boolean actualizar(int id) {
        Beneficio beneficio = Beneficio.importarBeneficios(id);
        if(beneficio == null){
            Texto.esperarEnter("Beneficio no encontrado");
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
            beneficio.setNombre(Texto.leerString("> * Nombre del beneficio: "));
            System.out.println("\n- FECHA DE INICIO");
            dia = Texto.leerInt("> * Dia: ");
            mes = Texto.leerInt("> * Mes: ");
            anio = Texto.leerInt("> * Año: ");
            beneficio.setFecInicio(dia, mes, anio);

            System.out.println("\n- FECHA DE VENCIMIENTO");
            dia = Texto.leerInt("> * Dia: ");
            mes = Texto.leerInt("> * Mes: ");
            anio = Texto.leerInt("> * Año: ");
            beneficio.setFecVen(dia, mes, anio);
            
            System.out.println("\n- Beneficios");
            beneficio.setPorcPuntos(Texto.leerInt("> Porcentaje de puntos: "));
            beneficio.setPorcCashBack(Texto.leerInt("> Porcentaje de CashBack: "));
            beneficio.setPorcDescuento(Texto.leerInt("> Porcentaje de Descuento: "));

            
        } catch (Exception e) {
            Texto.esperarEnter("Dato incorrecto");
            return null;
        }

        return beneficio;
        
    }

}

