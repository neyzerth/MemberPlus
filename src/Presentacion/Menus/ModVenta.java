package Presentacion.Menus;

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

            Tarjeta tarjetaCompra = Tarjeta.importarTarjeta(numTarjeta);

            recogerTotal.imprimirCuadro();

            float total = Texto.leerFloat("> $");
            System.out.println(Texto.moneda(total));
            
            //Compra compra = Compra.realizarVenta(tarjetaCompra, total);

            //if(compra.tuvoBeneficios)){
            System.out.println("Con esta compra, usted... ");
                //if(compra.getPuntos() > 0)
                    System.out.println("ganó " + /*compra.getPuntos()+*/ "puntos!");
                //if(compra.getCashback() > 0)
                    System.out.println("ganó " + /*Texto.moneda(compra.getCashback())+*/ "!");
                //if(compra.getDescuento() > 0)
                    System.out.println("ahorró $" + /*compra.getDescuento()+*/ "!");
            //}


            //if(tarjetaCompra.getPuntos() > 0){
                System.out.println(Color.verde(Color.negrita(" Desea usar sus puntos acumulados? ("+/*tajeta.getPuntos+*/")?")));
                boolean usarPuntos = Texto.leerString(Color.verde(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            //}
            //if(tarjeta.getSaldo() > 0){
                System.out.println(Color.verde(Color.negrita(" Desea usar su saldo ("+/*Texto.moneda(tajeta.getSaldo)+*/")?")));
                boolean usarSaldo = Texto.leerString(Color.verde(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            //}
            //compra.usarBeneficios(tarjetaCompra, usarPuntos, usarSaldo)


            System.out.println("Su nuevo total es de " /* + Texto.moneda(compra.getSubtotal())*/);
            Texto.esperarEnter();
            salir = true;
        }
    }
    
}
