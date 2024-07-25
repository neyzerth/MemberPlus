package Persistencia;

import java.util.List;

import Persistencia.Tablas.PersonaEnt;
import Persistencia.Tablas.TarjetaEnt;
import Persistencia.Tablas.UsuarioEnt;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello UTT!\n");
        Conexion conexion = new Conexion();

        // Conecta sin imprimir
        conexion.conectar();

        // Imprime estatus de conexion
        conexion.probarConexion();

        // PersonaEnt persona = PersonaEnt.selectPersona(1);
        // UsuarioEnt usuario = UsuarioEnt.selectUsuario(1);

        // System.out.println(usuario.getNombreUsuario());

        // System.out.println(persona.getNombre());
        // System.out.println(persona.getFecNac());

        // List<PersonaEnt> personas = PersonaEnt.selectAllPersona();
        // List<UsuarioEnt> usuarios = UsuarioEnt.selectAllUsuario();

        // for (UsuarioEnt user : usuarios) {
        // System.out.println("ID: " + user.getId() + ", Nombre: " +
        // user.getNombreUsuario());
        // }
        // for (PersonaEnt person : personas) {
        // System.out.println("ID: " + person.getId() + ", Nombre: " +
        // person.getNombre());
        // }

        // List<UsuarioEnt> usu = UsuarioEnt.selectAllUsuario();
        // for (UsuarioEnt user : usu) {
        // System.out.println("ID: " + user.getId() + ", Nombre: ");
        // }

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

        tarjeta.ejecutarSelect();
        // tarjeta.actualizarTarjeta(1, "1", java.sql.Date.valueOf("2020-2-2"), java.sql.Date.valueOf("2020-2-2"), 
        // false, 12, 12,1, 1 );
        tarjeta.insertarTarjeta( 17,"2", java.sql.Date.valueOf("2020-2-2"), java.sql.Date.valueOf("2020-2-2"), 
        true, 1, 1,1, 1 );
        tarjeta.obtenerTarjetaPorNum("2");
        
        
        
    }
}
