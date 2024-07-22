package Persistencia;

import Persistencia.Tablas.PersonaEnt;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();
        
        conexion.conectar();

        PersonaEnt persona = new PersonaEnt(100);

        System.out.println(persona.getNombre()); 

    }
}
