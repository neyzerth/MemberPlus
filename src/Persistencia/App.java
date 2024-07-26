package Persistencia;

import java.util.List;
import Persistencia.Tablas.BeneficioEnt;
import Persistencia.Tablas.PersonaEnt;
import Persistencia.Tablas.RolEnt;
import Persistencia.Tablas.TarjetaEnt;
import Persistencia.Tablas.UsuarioEnt;
import Persistencia.Tablas.MovimientoEnt;
import Persistencia.Tablas.CompraEnt;
import Persistencia.Tablas.NivelEnt;
import Persistencia.Tablas.TipoMovimientoEnt;


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

        // TarjetaEnt tarjeta = new TarjetaEnt();

        // tarjeta.ejecutarSelect();
        // // tarjeta.actualizarTarjeta(1, "1", java.sql.Date.valueOf("2020-2-2"),
        // java.sql.Date.valueOf("2020-2-2"),
        // // false, 12, 12,1, 1 );
        // tarjeta.insertarTarjeta( 17,"2", java.sql.Date.valueOf("2020-2-2"),
        // java.sql.Date.valueOf("2020-2-2"),
        // true, 1, 1,1, 1 );
        // tarjeta.obtenerTarjetaPorNum("2");

        // RolEnt rolEnt = new RolEnt();
        // for (int i = 0; i < rolEnt.getCantColumnas(); i++) {
        //     System.out.print(rolEnt.getNomColumna(i) + "\t");
        // }
        // System.out.println();
        // for (Object[] columna : rolEnt.ejecutarSelect()) {
        //     for (Object fila : columna) {
        //         System.out.print(fila + "\t");
        //     }
        //     System.out.println();
        // }
        // rolEnt.ejecutarSelect();

        // BeneficioEnt beneficioEnt = new BeneficioEnt();

        // for (int i = 0; i < beneficioEnt.getCantColumnas(); i++) {
        //     System.out.print(beneficioEnt.getNomColumna(i) + "\t");
        // }
        // System.out.println();
        // for (Object[] columna : beneficioEnt.ejecutarSelect()) {
        //     for (Object fila : columna) {
        //         System.out.print(fila + "\t");
        //     }
        //     System.out.println();

        //     beneficioEnt.ejecutarSelect();

        // }

         CompraEnt compraEnt = new CompraEnt();

        //  for (int i = 0; i < compraEnt.getCantColumnas(); i++) {
        //     System.out.print(compraEnt.getNomColumna(i) + "\t");
        // }
        // System.out.println();
        // for (Object[] columna : compraEnt.ejecutarSelect()) {
        //     for (Object fila : columna) {
        //         System.out.print(fila + "\t");
        //     }
        //     System.out.println();

        //     compraEnt.ejecutarSelect();
        // }

        // MovimientoEnt movimientoEnt = new MovimientoEnt();

        // for (int i = 0; i < movimientoEnt.getCantColumnas(); i++) {
        //     System.out.print(movimientoEnt.getNomColumna(i) + "\t");
        // }
        // System.out.println();
        // for (Object[] columna : movimientoEnt.ejecutarSelect()) {
        //     for (Object fila : columna) {
        //         System.out.print(fila + "\t");
        //     }
        //     System.out.println();

        //     movimientoEnt.ejecutarSelect();
        // }

        NivelEnt nivelEnt = new NivelEnt();

        for (int i = 0; i < nivelEnt.getCantColumnas(); i++) {
            System.out.print(nivelEnt.getNomColumna(i) + "\t");
        }
        System.out.println();
        for (Object[] columna : nivelEnt.ejecutarSelect()) {
            for (Object fila : columna) {
                System.out.print(fila + "\t");
            }
            System.out.println();

            nivelEnt.ejecutarSelect();
        }




    }
}
