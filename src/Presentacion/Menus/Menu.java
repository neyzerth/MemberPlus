package Presentacion.Menus;

import Presentacion.Despliegue.*;
import Presentacion.Formato.Color;
import Presentacion.Formato.Texto;

//CLASE ABSTRACTA 
//Padre de la mayoria de los modulos/menus
public abstract class Menu {

    private String modSing; //Nombre del modulo en singular
    private String modPlur;  //Nombre del modulo en plural
    public Tabla tabla;     
    public Cuadro opciones; //El cuadro de opciones que van a tener

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

    //METODOS ABSTRACTOS
    //Despliega una tabla con todos los registros 
    public abstract void tabla();
    //Despliega una tabla con solo un registro (es mas especifica) 
    public abstract boolean tabla(int id);

    //Menu a desplegar
    public void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(6) + "> Módulo de " + modPlur + " <")));

            opciones.imprimirCuadroNum();

            System.out.println();

            int opcion = Texto.leerInt(Color.cian(" > Seleccione una opción: "));
            
            salir = conexionMenus(opcion);
            
        }
    }

    //Default de orden de las opciones
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
                System.out.println();
                System.out.println(Color.rojo(" Opción inválida, por favor intente de nuevo."));
                Texto.esperar(1);
            break;
        }
        return false;

    }

    //Mirar todos los registros
    private void menuVerTodos(){
        Texto.limpiarPantalla();

        Cuadro lista = new Cuadro(Color.morado(" Lista de " + modPlur));
        lista.imprimirCuadro();
        tabla();

        Texto.esperarEnter();
    }
    
    //mirar a detalle un registro a partir de su id
    private void menuVerUno(){
        
        Cuadro info = new Cuadro( Color.morado(" Informacion de " + modSing));
        
        Texto.limpiarPantalla();
        info.imprimirCuadro();

        tabla();

        System.out.println();
        int id = Texto.leerInt(Color.cian(" > ID del " + modSing + " a ver: "));
        if(!tabla(id)) { 
            System.out.println();
            Texto.esperarEnter(Color.rojo(" No existe " + modSing + " con ID " + id + "..."));
            return;
        }
    
        Texto.esperarEnter();
    }

    //Reegistrar
    public void menuRegistrar(){
        Texto.limpiarPantalla();

        Cuadro modificar = new Cuadro(Color.morado(" Registrar " + modSing));
        modificar.imprimirCuadro();
        
        //Metodo dentro de if despliega el metodo abstracto registrar y regresa un booleano
        if(registrar()){
            System.out.println();
            Texto.esperarEnter((" ")+Color.verde(modSing) + Color.verde(" registrado con exito"));
        } else
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" Error al registrar " + modSing)));
    

    }

    //Actualizar a partir del id
    public void menuActualizar(){

        Cuadro actualizar = new Cuadro(Color.morado(" Modificar informacion de " + modSing));
        Texto.limpiarPantalla();

        actualizar.imprimirCuadro();
        tabla();

        System.out.println();
        int id = Texto.leerInt(Color.cian(" > ID del " + modSing +" a modificar: "));

        if(!tabla(id)) { 
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita(" No existe " + modSing + " con ID " + id + "...")));
            return;
        }

        //Metodo dentro de if despliega el metodo abstracto actualizar y regresa un booleano
        if(actualizar(id)){
            tabla(id);
            System.out.println();
            Texto.esperarEnter(Color.verde(" "+ modSing + " actualizado con exito"));
            return;
        }
        else
            System.out.println();
            Texto.esperarEnter(Color.rojo(" Error al actualizar " + modSing));
    }

    //Pregunta y confirma si quiere eliminar un registro a partir de id
    private void menuEliminar(){
        boolean repetir = false;

        Cuadro eliminar = new Cuadro(
                Color.morado(" Eliminar " + modSing));
        
        do{
            Texto.limpiarPantalla();

            eliminar.imprimirCuadro();
            tabla();

            System.out.println();
            int id = Texto.leerInt(Color.cian(" > ID de " + modSing + " a eliminar: "));

            if(!tabla(id)) { 
                System.out.println();
                Texto.esperarEnter(Color.rojo(Color.negrita(" No existe " + modSing + " con ID " + id + "...")));
                repetir = true;
                return;
            }

            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" ¿Seguro que desea eliminar este " + modSing + "?")));
            boolean conf = Texto.leerString (Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            System.out.println();

            if (conf)
                if(eliminar(id)){
                    tabla();
                    System.out.println();
                    Texto.esperarEnter(Color.verde(" " + modSing + " eliminado con exito"));
                    repetir = false;
                }
                else
                    System.out.println();
                    Texto.esperarEnter(Color.rojo(" Error al eliminar " + modSing));
        } while (repetir);
        
    }

    //METODOS ABSTRACTOS
    //cada clase hija registra, actualiza y elimina de manera distinta
    public abstract boolean registrar();
    public abstract boolean actualizar(int id);
    public abstract boolean eliminar(int id);

    public void setNombres(String singular, String plural){
        this.modSing = singular;
        this.modPlur = plural;
    }
}
