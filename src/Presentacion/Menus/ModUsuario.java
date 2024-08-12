package Presentacion.Menus;

import Presentacion.Despliegue.Cuadro;
import Presentacion.Despliegue.Tabla;
import Presentacion.Formato.*;

import Logica.Objetos.Rol;
import Logica.Objetos.Usuario;

public class ModUsuario extends Menu {

    public ModUsuario() {
        super("Usuario", "Usuarios");
    }

    public static void desplegarMenu() {
        ModUsuario modUsuario = new ModUsuario();
        modUsuario.menu();
    }

    @Override
    public boolean registrar() {

        Usuario usuario = new Usuario();

        nomUsuario(usuario);
        contrasena(usuario);
        rfc(usuario);
        rol(usuario);
        ModPersona.pedirDatos(usuario);

        if (usuario.insertarPersona())
            return usuario.insertarUsuario();

        return false;
    }

    @Override
    public boolean actualizar(int id) {
        Usuario usuario = Usuario.importarUsuarios(id);

        System.out.println();
        datosUsuario(usuario);

        if (usuario.actualizarPersona())
            return usuario.actualizarUsuario();

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Usuario.eliminarUsuario(id);
    }

    public void datosUsuario(Usuario usuario) {
        int opc;
        do {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" ¿Que desea actualizar?")));
            System.out.println();
            // System.out.println(Color.cian(Color.negrita(" Nombre de usuario[1] Contraseña[2] RFC[3] Rol[4] Datos Personales[5] Terminar[ENTER]")));
            Cuadro cuadro = new Cuadro("Nombre de Usuario", "Contraseña", "RFC", "ROL", "Datos Personales");
            cuadro.agregarSalir();
            cuadro.imprimirCuadroNum();
            opc = Leer.entero(Color.cian(" > "));
            datos(usuario, opc);
        } while (opc != 0);
    }

    public void datos(Usuario usuario, int opc) {

        switch (opc) {
            case 1:
                nomUsuario(usuario);
                break;
            case 2:
                contrasena(usuario);
                break;
            case 3:
                rfc(usuario);
                break;
            case 4:
                rol(usuario);
                break;
            case 5:
                ModPersona.datosPersona(usuario);
                break;

            default:
                System.out.println();
                System.out.println(Color.rojo(Color.negrita(" Opcion no valida")));
                break;
        }
    }

    public void nomUsuario(Usuario usuario) {
        boolean repetir;
        do {
            repetir = false;

            try {
                usuario.setNomUsuario(Leer.cadena(Color.cian(Color.negrita(" > *Nombre del usuario: "))));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        } while (repetir);

    }

    public void contrasena(Usuario usuario) {
        boolean repetir;
        do {
            repetir = false;

            try {
                usuario.setContrasena(Leer.contra(Color.cian(Color.negrita(" > *Contraseña del usuario: "))));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        } while (repetir);
    }

    public void rfc(Usuario usuario) {
        boolean repetir;
        do {
            repetir = false;

            try {
                usuario.setRfc(Leer.cadena(Color.cian(" > RFC del usuario: ")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        } while (repetir);
    }

    public void rol(Usuario usuario) {
        boolean repetir;
        do {
            repetir = false;

            try {
                System.out.println();
                System.out.println(Texto.espacio(32) + Color.amarillo(Color.negrita("> Roles <")));

                Tabla tablaRol = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"),
                        Color.amarillo("Descripcion"));
                Rol[] roles = Rol.importarRoles();
                for (Rol rol : roles) {
                    tablaRol.agregarFila(
                            rol.getIdRol(), rol.getNombre(), rol.getDescripcion());
                }
                tablaRol.imprimirTablaSimple();

                System.out.println();
                usuario.setRol(Leer.entero(Color.cian(Color.negrita(" > *ID del ROL del usuario: "))));
            } catch (Exception e) {
                System.out.println(Color.rojo("Rol no valido"));
                repetir = true;
            }
        } while (repetir);
    }

    @Override
    public boolean tabla(int id) {

        if (!Usuario.validarUsuario(id)) {
            return false;
        }

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("Nombre completo"),
                Color.amarillo("Correo"), Color.amarillo("RFC"));

        Usuario usuario = Usuario.importarUsuarios(id);

        tabla.agregarFila(
                usuario.getIdUsuario(),
                usuario.getNomUsuario(),
                usuario.getNombre() + " " + usuario.getApellidoPa() + " " + usuario.getApellidoMa(),
                usuario.getCorreo(),
                usuario.getRfc());
        tabla.imprimirTablaSimple();

        return true;
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre Usuario"), Color.amarillo("RFC"));
        Usuario[] usuarios = Usuario.importarUsuarios();

        for (Usuario usuario : usuarios) {
            tabla.agregarFila(
                    usuario.getIdUsuario(),
                    usuario.getNomUsuario(),
                    usuario.getRfc());
        }
        tabla.imprimirTablaSimple();

    }
}
