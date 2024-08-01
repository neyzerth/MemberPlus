//-- Active: 1719932866993@@127.0.0.1@3306
package Presentacion.Menus;

import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

import Logica.Objetos.Persona;
import Logica.Objetos.Usuario;

public class ModUsuario extends Menu {

    public ModUsuario(){
        super("Usuario", "Usuarios");
    }
    public static void desplegarMenu() {
        ModUsuario modUsuario = new ModUsuario();
        modUsuario.menu();
    }

    @Override
    public boolean registrar() {

        Usuario usuario = new Usuario();

        String nombre = Texto.leerString("> * Nombre del usuario: ");
        String contrasena = Texto.leerContra("> * Contraseña del usuario: ");
        String rfc = Texto.leerString("> RFC del usuario: ");
        int rol = Texto.leerInt("> ROL del usuario: ");

        try {
            Persona persona = ModPersona.datosPersona();

            if(persona.insertarPersona()){
                persona.setIdPersona();
                usuario = new Usuario(0, nombre, contrasena, rfc, persona, rol);
            }

            if( usuario.insertarUsuario()){
                tabla(usuario.getIdUsuario());
                return true;
            }

        } catch (Exception e) {
            Texto.esperarEnter("DATO NO VALIDO");
        }

        return false;
    }

    @Override
    public boolean actualizar(int id){
        Usuario usuario = Usuario.importarUsuarios(id);
        do{

            usuario.setNomUsuario(Texto.leerString(Color.amarillo(Color.negrita("> Nombre del usuario: "))));
            usuario.setContrasena(Texto.leerContra(Color.amarillo(Color.negrita("> Contraseña del usuario: "))));
            usuario.setRfc(Texto.leerString("> RFC del usuario: "));
            usuario.setIdRol(Texto.leerInt(Color.amarillo(Color.negrita("> ROL del usuario: "))));

            try {
                System.out.println(Color.rojo(Color.negrita("Desea modificar la informacion personal del usuario??")));
                boolean conf = Texto.leerString(Color.rojo("SI[s]  NO[n]: ")).toLowerCase().equals("s");

                if(conf){
                    Persona persona = ModPersona.datosPersona();
                    persona.setIdPersona(usuario.getIdPersona());
                }
            

                if(usuario.actualizarUsuario()){
                    tabla(id);
                    return true;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Texto.esperarEnter("DATO NO VALIDO");
                
            }
            return false;
        } while (false);
    }

    @Override
    public boolean eliminar(int id) {
        return Usuario.eliminarUsuario(id);
    }

    @Override
    public boolean tabla(int id) {
        
        if (!Usuario.validarUsuario(id)){
            return false;
        }

        Tabla tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("RFC"));

        Usuario usuario = Usuario.importarUsuarios(id);

        tabla.agregarFila(
            usuario.getIdUsuario(),
            usuario.getNomUsuario(),
            usuario.getRfc()
        );
        tabla.imprimirTablaSimple();

        return true;
    }

    @Override
    public void tabla() {
        Tabla tabla = new Tabla("ID", "Nombre Usuario", "RFC");
        Usuario[] usuarios =  Usuario.importarUsuarios();

        for (Usuario usuario : usuarios) {
            tabla.agregarFila(
                usuario.getIdUsuario(),
                usuario.getNomUsuario(),
                usuario.getRfc()
            );
        }
        tabla.imprimirTablaSimple();

    }
}
