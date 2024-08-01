package Presentacion.Menus;

import Presentacion.Despliegue.*;
import Presentacion.Formato.Color;
import Presentacion.Formato.Texto;

public abstract class Menu {

    private String modSing;
    private String modPlur;
    public Tabla tabla;
    public Cuadro opciones;

    public Menu(String singular, String plural){
        setNombres(singular, plural);
        this.opciones = new Cuadro(
            Color.morado("Lista de " + modPlur),
            Color.morado("Información de " + modSing),
            Color.morado("Registrar " + modSing),
            Color.morado("Modificar " + modSing),
            Color.morado("Eliminar "+ modSing),
            Color.rojo("Volver")
        );
    }

    
    public abstract void tabla();
    public abstract boolean tabla(int id);

    public void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de " + modPlur + " <")));

            opciones.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian("> Seleccione una opción: "));
            
            salir = conexionMenus(opcion);
            
        }
    }

    public boolean conexionMenus(int opcion){
        switch (opcion) {
            case 1: menuVerTodos();
                break;
            case 2: menuVerUno();
                break;
            case 3: menuRegistrar();
                break;
            case 4: menuActualizar();
                break;
            case 5: menuEliminar();
                break;
                
            case 6: return true;

            default:
                System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                Texto.esperar(1);
            break;
        }
        return false;

    }

    private void menuVerTodos(){
        Texto.limpiarPantalla();

        Cuadro lista = new Cuadro(Color.amarillo("> Lista de " + modPlur));
        lista.imprimirCuadro();
        tabla();

        Texto.esperarEnter();
    }
    
    private void menuVerUno(){
        
        Cuadro info = new Cuadro( Color.amarillo("> Informacion de " + modSing));
        
        Texto.limpiarPantalla();
        info.imprimirCuadro();

        tabla();

        int id = Texto.leerInt("> ID del " + modSing + " a ver: ");
        if(!tabla(id)) { 
            Texto.esperarEnter("No existe " + modSing + " con ID " + id + "...");
            return;
        }
    
        Texto.esperarEnter();
    }

    public void menuRegistrar(){
        Texto.limpiarPantalla();

        Cuadro modificar = new Cuadro(Color.amarillo("> Registrar " + modSing));
        modificar.imprimirCuadro();
        

        if(registrar())
            Texto.esperarEnter(modSing + " registrado con exito");
        else
            Texto.esperarEnter("Error al registrar " + modSing);
    

    }

    public void menuActualizar(){

        Cuadro actualizar = new Cuadro(Color.amarillo("> Modificar informacion de " + modSing));
        Texto.limpiarPantalla();

        actualizar.imprimirCuadro();
        tabla();

        int id = Texto.leerInt("> ID del " + modSing +" a modificar: ");

        if(!tabla(id)) { 
            Texto.esperarEnter("No existe " + modSing + " con ID " + id + "...");
            return;
        }

        if(actualizar(id)){
            Texto.esperarEnter(modSing + " actualizado con exito");
            return;
        }
        else
            Texto.esperarEnter("Error al actualizar " + modSing);
            
    }

    private void menuEliminar(){
        boolean repetir = false;

        Cuadro eliminar = new Cuadro(
                Color.amarillo("> Eliminar " + modSing));
        
        do{
            Texto.limpiarPantalla();

            eliminar.imprimirCuadro();
            tabla();

            int id = Texto.leerInt("> ID de " + modSing + " a eliminar");

            if(!tabla(id)) { 
                Texto.esperarEnter("No existe " + modSing + " con ID " + id + "...");
                repetir = true;
                return;
            }

            System.out.println(Color.rojo(Color.negrita("Seguro que desea eliminar este " + modSing + "?")));
            boolean conf = Texto.leerString("SI[s]  NO[n]: ").toLowerCase().equals("s");

            if (conf)
                if(eliminar(id)){
                    tabla();
                    Texto.esperarEnter(modSing + " eliminado con exito");
                    repetir = false;
                }
                else
                    Texto.esperarEnter(" Error al eliminar " + modSing);
        } while (repetir);
        
    }

    public abstract boolean registrar();
    public abstract boolean actualizar(int id);
    public abstract boolean eliminar(int id);

    public void setNombres(String singular, String plural){
        this.modSing = singular;
        this.modPlur = plural;
    }
}
