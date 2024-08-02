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

        System.out.println();
        String nombre = Texto.leerString(Color.cian(Color.negrita(" > Nombre del usuario: ")));
        String contrasena = Texto.leerContra(Color.cian(Color.negrita(" > Contraseña del usuario: ")));
        String rfc = Texto.leerString(Color.cian(" > RFC del usuario: "));
        int rol = Texto.leerInt(Color.cian(Color.negrita(" > ROL del usuario: ")));

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
            Texto.esperarEnter(Color.rojo(Color.negrita(" DATO NO VALIDO")));
        }

        return false;
    }

    @Override
    public boolean actualizar(int id){
        Usuario usuario = Usuario.importarUsuarios(id);
        
        do{
            System.out.println();
            usuario.setNomUsuario(Texto.leerString(Color.cian(Color.negrita(" > Nombre del usuario: "))));
            usuario.setContrasena(Texto.leerContra(Color.cian(Color.negrita(" > Contraseña del usuario: "))));
            usuario.setRfc(Texto.leerString(Color.cian(" > RFC del usuario: ")));
            usuario.setIdRol(Texto.leerInt(Color.cian(Color.negrita(" > ROL del usuario: "))));

            try {
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Desea modificar la informacion personal del usuario??")));
                boolean conf = Texto.leerString(Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
                System.out.println();

                if(conf){
                    Persona persona = ModPersona.datosPersona();
                    persona.setIdPersona(usuario.getIdPersona());
                }
            

                if(usuario.actualizarUsuario()){
                    return true;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Texto.esperarEnter(Color.rojo(Color.negrita(" DATO NO VALIDO")));
                
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

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("Nombre"), Color.amarillo("Correo"), Color.amarillo("RFC"));

        Usuario usuario = Usuario.importarUsuarios(id);

        tabla.agregarFila(
            usuario.getIdUsuario(),
            usuario.getNomUsuario(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getRfc()
        );
        tabla.imprimirTablaSimple();

        return true;
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"),Color.amarillo( "Nombre Usuario"), Color.amarillo("RFC"));
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
