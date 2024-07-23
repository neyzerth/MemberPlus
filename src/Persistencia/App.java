package Persistencia;

import java.util.List;

import Persistencia.Tablas.PersonaEnt;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();
        
        //Conecta sin imprimir
        conexion.conectar();

        //Imprime estatus de conexion
        conexion.probarConexion();

        PersonaEnt persona = PersonaEnt.selectPersona(1);

        System.out.println(persona.getNombre()); 
        System.out.println(persona.getFecNac()); 

        List<PersonaEnt> personas = PersonaEnt.selectAllPersona();

        for (PersonaEnt person : personas) {
            System.out.println("ID: " + person.getId() + ", Nombre: " + person.getNombre());
        }
    }
}
