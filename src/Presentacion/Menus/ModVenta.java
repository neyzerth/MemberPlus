package Presentacion.Menus;

import Logica.Objetos.Compra;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ModVenta {
    public static void menu() {
        boolean salir = false;
        
        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Venta <")));

            System.out.println();

            Cuadro cuadroTarjeta = new Cuadro(
                Color.morado("> Numero de tarjeta")
            );

            Cuadro recogerTotal = new Cuadro(
                Color.morado("> Recoger el total")
            );


            cuadroTarjeta.imprimirCuadro();

            String numTarjeta = Texto.leerString("> ");

            if(!Tarjeta.validarNumTarjeta(numTarjeta)){
                Texto.esperarEnter(Color.rojo("No se encontro la tarjeta " + Texto.tarjeta(numTarjeta)));
                return;
            }

            Compra compra = new Compra(numTarjeta);

            recogerTotal.imprimirCuadro();

            float total = Texto.leerFloat("> $");
            System.out.println(Texto.moneda(total));
            
            compra.empezarVenta(total);

            if(compra.tuvoBeneficios()){
            System.out.println("Con esta compra, usted... ");
                if(compra.getPuntos() > 0)
                    System.out.println("obtuvo " + compra.getPuntos()+ " puntos!");
                if(compra.getCashback() > 0)
                    System.out.println("obtuvo " + Texto.moneda(compra.getCashback())+ " de cashback!");
                if(compra.getDescuento() > 0)
                    System.out.println("ahorró $" + compra.getDescuento()+ "!");
            }


            boolean usarPuntos = false;
            boolean usarSaldo = false;
            if(compra.tarjeta.getPuntos() > 0){
                System.out.println(Color.verde(Color.negrita(" Desea usar sus puntos acumulados? ("+compra.tarjeta.getPuntos()+" -> $"+ compra.tarjeta.getPuntosConvertidos()+")?")));
                usarPuntos = Texto.leerString(Color.verde(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            if(compra.tarjeta.getSaldo() > 0){
                System.out.println(Color.verde(Color.negrita(" Desea usar su saldo ("+Texto.moneda(compra.tarjeta.getSaldo())+")?")));
                usarSaldo = Texto.leerString(Color.verde(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            compra.usarBeneficios(usarPuntos, usarSaldo);


            System.out.println("Su nuevo total es de "  + Texto.moneda(compra.getSubtotal()));
            if(compra.insertarCompras())
                System.out.println("La compra fue exitosa");
            else
                System.out.println("Error en la compra");
            Texto.esperarEnter();
            salir = true;
        }
    }
    
}
