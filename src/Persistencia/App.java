package Persistencia;

import Persistencia.Tablas.BdPersona;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();
        
        conexion.conectar();

        BdPersona persona = new BdPersona(100);

        System.out.println(persona.getNombre()); 

    }
}
