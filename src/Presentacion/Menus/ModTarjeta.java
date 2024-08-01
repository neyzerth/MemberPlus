package Presentacion.Menus;

import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
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
                case 1: submodTarjeta();
                    break;
                case 2:
                    break;
                case 3:
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

        return false;
    }

    @Override
    public boolean actualizar(int id){

    }

    @Override 
    public boolean eliminar(int id){
        return Tarjeta.eliminarTarjeta(id);
    }
    public boolean eliminar(String numTarjeta){
        return Tarjeta.eliminarTarjeta(numTarjeta);
    }

    
}
