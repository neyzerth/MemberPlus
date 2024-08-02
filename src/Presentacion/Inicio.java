package Presentacion;

import Presentacion.Formato.*;
import Presentacion.Menus.Bienvenida;
import Presentacion.Menus.IniciarSesion;
import Presentacion.Menus.Principal;

public class Inicio {
    public static void main(String[] args) {
        do{
            Bienvenida.menu();

            boolean sesionIniciada = false;
            do{
                try{
                    sesionIniciada = IniciarSesion.menu();

                } catch (Exception e) {
                    Texto.esperarEnter("Error al iniciar sesion: " + e.getMessage());
                } //Si da un error, lo mandara al inicio de sesion
            //No saldra del ciclo hasta que inicie sesion
            } while (!sesionIniciada);

            //try {
                //Hasta que salga del ciclo, se desplegara el menu principal
                Principal.menu();

            //} catch (Exception e) {

            //    Texto.esperarEnter(Color.rojo("Error: " + e));
            //}
        } while ( true ); //El programa nunca se termina, incluso si da error
    }

}