package Presentacion.Menus;

import Logica.Objetos.Compra;
import Logica.Objetos.Movimiento;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.*;

public class ModVenta {
    public static void desplegarMenu() {
        boolean salir = false;
        do{
            salir = menu();
        } while(!salir);
    }

    private static boolean menu() {
        
            Texto.limpiarPantalla();
            Cuadro cuadroTarjeta = new Cuadro(
                Color.morado("Numero de tarjeta")
            );
            cuadroTarjeta.imprimirCuadro();

            System.out.println();

            String numTarjeta1 = Leer.cadena(Color.cian(Color.negrita(" > Ingrese los numeros de su tarjeta: ")), "(ENTER para salir)");
            if(numTarjeta1.isBlank()){
                return true;
            }
            String numTarjeta = numTarjeta1.replace(" ", "");
            if(!Tarjeta.validarNumTarjeta(numTarjeta)){
                System.out.println();
                Texto.esperarEnter(Color.rojo(" No se encontro la tarjeta " + Texto.tarjeta(numTarjeta)));
                return false;
            }

            Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
            if(!Tarjeta.importarTarjeta(numTarjeta).pagada()){
                System.out.println();
                System.out.println(Color.rojo(" Estimado Cliente, Member+ le informa que su membresia "+Color.fondoRojo(Texto.tarjeta(numTarjeta))+"presenta un saldo vencido por "+ Color.rojo(Texto.moneda(tarjeta.nivel.getAnualidad()))  ));
                System.out.println();
                Texto.esperarEnter(Color.rojo(Color.negrita(" PAGO INMEDIATO.")));
                return false;
            }

            Compra compra = new Compra(numTarjeta);
    
            System.out.println();
            float total = Leer.flotante(Color.cian(Color.negrita(" > *Ingrese el monto total: "+Color.reset+"$")));

            try {
                compra.empezarVenta(total);
                
            } catch (Exception e) {
                System.out.println();
                System.out.println(Color.rojo(e.getMessage()));
                Texto.esperarEnter();
                return false;
            }

            System.out.println();
            System.out.println(" " + Color.amarillo(Texto.moneda( total)));
            

            if(compra.tuvoBeneficios()){
            System.out.println(Color.amarillo(" Con esta compra, usted... "));
                if(compra.getPuntos() > 0)
                    System.out.println();
                    System.out.println(Color.amarillo(Color.negrita(" Obtuvo " + compra.getPuntos()+ " puntos!")));
                if(compra.getCashback() > 0)
                    System.out.println(Color.amarillo(Color.negrita(" Obtuvo " + Texto.moneda(compra.getCashback())+ " de cashback!")));
                if(compra.getDescuento() > 0)
                    System.out.println();
                    System.out.println(Color.amarillo(Color.negrita(" Ahorró $" + compra.getDescuento()+ "!")));
            }


            boolean usarPuntos = false;
            boolean usarSaldo = false;
            if(compra.tarjeta.getPuntosConvertidos() > 0){
                System.out.println();
                System.out.println(Color.amarillo(Color.negrita(" ¿Desea usar sus puntos acumulados? ("+compra.tarjeta.getPuntos()+" -> $"+ compra.tarjeta.getPuntosConvertidos()+")?")));
                usarPuntos = Leer.cadena(Color.cian(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            if(compra.tarjeta.getSaldo() > 0){
                System.out.println();
                System.out.println(Color.amarillo(Color.negrita(" ¿Desea usar su saldo ("+Texto.moneda(compra.tarjeta.getSaldo())+")?")));
                usarSaldo = Leer.cadena(Color.cian(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            float saldoRestante= compra.usarBeneficios(usarPuntos, usarSaldo);


            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Su nuevo total es de "  + Texto.moneda(compra.getSubtotal()))));
            if(compra.insertarCompras()){
                Movimiento.compra(Color.amarillo(" Compra de ")+ Texto.moneda(compra.getSubtotal()), compra.tarjeta);
                System.out.println();
                System.out.println(Color.verde(" La compra fue exitosa"));
                if(usarSaldo){
                    System.out.println();
                    System.out.println(Color.amarillo(Color.negrita(" El saldo restante es:"+saldoRestante)));
                }
            
            } else{
                 System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Error en la compra")));
            }

            Texto.esperarEnter();
            return true;
    }
    
}
