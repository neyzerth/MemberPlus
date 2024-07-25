package Persistencia;

import java.util.List;

import Persistencia.Tablas.TarjetaEnt;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();

        // Conecta sin imprimir
        conexion.conectar();

        // Imprime estatus de conexion
        conexion.probarConexion();

        TarjetaEnt tarjeta = new TarjetaEnt();
        
        for (int i = 0; i < tarjeta.getCantColumnas(); i++) {
            System.out.print(tarjeta.getNomColumna(i) + "\t");
        }
        System.out.println();
        for (Object[] columna : tarjeta.ejecutarSelect()) {
            for (Object fila : columna) {
                System.out.print(fila + "\t");
            }
            System.out.println();
        }



    
    }
}
