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
            Cuadro cuadroTarjeta = new Cuadro(
                Color.morado("Numero de tarjeta")
            );
            cuadroTarjeta.imprimirCuadro();

            System.out.println();

            String numTarjeta = Leer.cadena(Color.cian(Color.negrita(" > Ingrese los numeros de su tarjeta: ")));

            if(!Tarjeta.validarNumTarjeta(numTarjeta)){
                System.out.println();
                Texto.esperarEnter(Color.rojo(" No se encontro la tarjeta " + Texto.tarjeta(numTarjeta)));
                return;
            }

            Compra compra = new Compra(numTarjeta);
    
            System.out.println();
            float total = Leer.flotante(Color.cian(Color.negrita(" > Ingrese el monto total: $")));

            System.out.println();
            System.out.println(" " + Color.amarillo(Texto.moneda( total)));
            
            compra.empezarVenta(total);

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
            if(compra.tarjeta.getPuntos() > 0){
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Desea usar sus puntos acumulados? ("+compra.tarjeta.getPuntos()+" -> $"+ compra.tarjeta.getPuntosConvertidos()+")?")));
                usarPuntos = Leer.cadena(Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            if(compra.tarjeta.getSaldo() > 0){
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Desea usar su saldo ("+Texto.moneda(compra.tarjeta.getSaldo())+")?")));
                usarSaldo = Leer.cadena(Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            }
            compra.usarBeneficios(usarPuntos, usarSaldo);


            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Su nuevo total es de "  + Texto.moneda(compra.getSubtotal()))));
            if(compra.insertarCompras()){
                System.out.println();
                System.out.println(Color.verde(" La compra fue exitosa"));
            }else
                 System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Error en la compra")));
            Texto.esperarEnter();
            salir = true;
        }
    }
    
}
