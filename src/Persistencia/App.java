package Persistencia;

import java.util.List;

import Persistencia.Tablas.PersonaEnt;
import Persistencia.Tablas.TarjetaEnt;
import Persistencia.Tablas.UsuarioEnt;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();

        // Conecta sin imprimir
        conexion.conectar();

        // Imprime estatus de conexion
        conexion.probarConexion();

        PersonaEnt persona = PersonaEnt.selectPersona(1);
        UsuarioEnt usuario = UsuarioEnt.selectUsuario(1);

        System.out.println(usuario.getNombreUsuario());

        System.out.println(persona.getNombre());
        System.out.println(persona.getFecNac());

        List<PersonaEnt> personas = PersonaEnt.selectAllPersona();
        List<UsuarioEnt> usuarios = UsuarioEnt.selectAllUsuario();

        for (UsuarioEnt user : usuarios) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getNombreUsuario());
        }
        for (PersonaEnt person : personas) {
            System.out.println("ID: " + person.getId() + ", Nombre: " + person.getNombre());
        }

        List<UsuarioEnt> usu = UsuarioEnt.selectAllUsuario();
        for (UsuarioEnt user : usu) {
            System.out.println("ID: " + user.getId() + ", Nombre: ");
        }

        TarjetaEnt tarjetaEnt = new TarjetaEnt();
        tarjetaEnt.realizarOperaciones();


    
    }
}
