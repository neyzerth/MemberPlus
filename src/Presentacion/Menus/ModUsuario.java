package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

import Logica.Objetos.Persona;
import Logica.Objetos.Rol;
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
        usuario = pedirDatos();

        try {
            Persona persona = ModPersona.datosPersona();

            if(persona.insertarPersona()){
                persona.setIdPersona();
                usuario.setIdPersona(persona.getIdPersona());
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
        int idPersona = usuario.getIdPersona();
        
        System.out.println();
        usuario = pedirDatos();
        usuario.setIdUsuario(id);
        usuario.setIdPersona(idPersona);

        try {
            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" Desea modificar la informacion personal del usuario??")));
            boolean conf = Leer.cadena(Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            System.out.println();

            if(conf){
                Persona persona = ModPersona.datosPersona();
                if(persona != null){
                    persona.setIdPersona(idPersona);
                    persona.actualizarPersona();
                } else return false;
            
            }
        
            
            return usuario.actualizarUsuario();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Texto.esperarEnter(Color.rojo(Color.negrita(" DATO NO VALIDO")));
            
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Usuario.eliminarUsuario(id);
    }

    public Usuario pedirDatos(){
        Usuario usuario = new Usuario();
        try{
            usuario.setNomUsuario(Leer.cadena(Color.cian(Color.negrita(" > Nombre del usuario: "))));
            usuario.setContrasena(Leer.contra(Color.cian(Color.negrita(" > Contraseña del usuario: "))));
            usuario.setRfc(Leer.cadena(Color.cian(" > RFC del usuario: ")));

            System.out.println();
            System.out.println(Texto.espacio(35) + Color.amarillo(Color.negrita("> Roles <")));

            System.out.println();
            System.out.println(Texto.espacio(35) + Color.amarillo(Color.negrita("> Roles <")));

            Tabla tablaRol = new Tabla("ID", "Nombre", "Descripcion");
            Rol[] roles = Rol.importarRoles();
            for (Rol rol : roles) {
                tablaRol.agregarFila(
                    rol.getIdRol(), rol.getNombre(), rol.getDescripcion()
                );
            }
            tablaRol.imprimirTablaSimple();
            
            System.out.println();
            usuario.setRol(Leer.entero(Color.cian(Color.negrita(" > ID del ROL del usuario: "))));

            return usuario;   
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean tabla(int id) {
        
        if (!Usuario.validarUsuario(id)){
            return false;
        }

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("Nombre completo"), Color.amarillo("Correo"), Color.amarillo("RFC"));

        Usuario usuario = Usuario.importarUsuarios(id);

        tabla.agregarFila(
            usuario.getIdUsuario(),
            usuario.getNomUsuario(),
            usuario.getNombre() + " " + usuario.getApellidoPa() + " " + usuario.getApellidoMa(),
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
