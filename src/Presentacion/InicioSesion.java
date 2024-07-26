package Presentacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Logica.Usuario;
import Persistencia.Conexion;

public class InicioSesion {
    Usuario usuario = new Usuario();
    // ! Las conexiones y consultas van en la capa de persistencia
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    Scanner insertar = new Scanner(System.in);

    public void mostrarUsuarios() {
        try {
            String query = "SELECT idUsuario, nombreUsuario, contrasena, rfc, persona, rol FROM usuario";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            ResultSet resultado = preparedStatement.executeQuery(query);

            System.out.println("\nID\tNombre de Usuario\tContraseña\tRFC\t\tPersona\tRol");
            while (resultado.next()) {
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombreUsuario");
                String contrasena = resultado.getString("contrasena");
                String rfc = resultado.getString("rfc");
                int persona = resultado.getInt("persona");
                int rol = resultado.getInt("rol");

                System.out.println(idUsuario + "\t" + nombreUsuario +
                        "\t" + contrasena + "\t" + rfc +
                        "\t" + persona + "\t" + rol);
            }
            System.out.println("\n");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertarUsuario() {
        try {
            String sentenciaInsert = "INSERT INTO usuario (nombreUsuario, contrasena, rfc, persona, rol) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pSt = cn.prepareStatement(sentenciaInsert);

            System.out.println("Introduce el Nombre de Usuario: ");
            String nombreUsuario = insertar.nextLine();
            usuario.setNomUsuario(nombreUsuario);

            System.out.println("Introduce la Contraseña: ");
            String contrasena = insertar.nextLine();
            usuario.setContrasena(contrasena);

            System.out.println("Introduce el RFC: ");
            String rfc = insertar.nextLine();
            usuario.setRfc(rfc);
            
            //? No hay idPersona como atributo
            //System.out.println("Introduce el ID de la Persona: ");
            //int persona = insertar.nextInt();
            //usuario.setPersona(persona);

            //? No hay idRol como atributo
            //System.out.println("Introduce el ID del Rol: ");
            //int rol = insertar.nextInt();
            //usuario.setRol(rol);

            // Asignar valores a los parámetros
            pSt.setString(1, usuario.getNomUsuario());
            pSt.setString(2, usuario.getContrasena());
            pSt.setString(3, usuario.getRfc());
            //pSt.setInt(4, usuario.getPersona());
            //pSt.setInt(5, usuario.getRol());

            // Ejecutar la sentencia
            int filasInsertadas = pSt.executeUpdate();
            System.out.println("Filas insertadas: " + filasInsertadas);

            // Cerrar recursos
            pSt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarUsuario() {
        try {
            String sentenciaUpdate = "UPDATE usuario SET nombreUsuario = ?, contrasena = ?, rfc = ?, persona = ?, rol = ? WHERE idUsuario = ?";
            PreparedStatement pSt = cn.prepareStatement(sentenciaUpdate);

            System.out.println("Introduce el ID del Usuario a modificar: ");
            //?int idUsuario = insertar.nextInt();
            //?usuario.setIdUsuario(idUsuario);
            insertar.nextLine(); // Para consumir el salto de línea

            System.out.println("Introduce el Nombre de Usuario: ");
            String nombreUsuario = insertar.nextLine();
            usuario.setNomUsuario(nombreUsuario);

            System.out.println("Introduce la Contraseña: ");
            String contrasena = insertar.nextLine();
            usuario.setContrasena(contrasena);

            System.out.println("Introduce el RFC: ");
            String rfc = insertar.nextLine();
            usuario.setRfc(rfc);

            //System.out.println("Introduce el ID de la Persona: ");
            //int persona = insertar.nextInt();
            //usuario.setPersona(persona);

            //System.out.println("Introduce el ID del Rol: ");
            //int rol = insertar.nextInt();
            //usuario.setRol(rol);

            // Asignar valores a los parámetros
            pSt.setString(1, usuario.getNomUsuario());
            pSt.setString(2, usuario.getContrasena());
            pSt.setString(3, usuario.getRfc());
            //pSt.setInt(4, usuario.getPersona());
            //pSt.setInt(5, usuario.getRol());
            pSt.setInt(6, usuario.getIdUsuario());

            // Ejecutar la sentencia
            int filasActualizadas = pSt.executeUpdate();
            System.out.println("Filas actualizadas: " + filasActualizadas);

            // Cerrar recursos
            pSt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminarUsuario() {
        System.out.println("Introduce el ID del Usuario a eliminar: ");
        //?int idUsuario = insertar.nextInt();
        //?usuario.setIdUsuario(idUsuario);

        try {
            String sentenciaEliminar = "DELETE FROM usuario WHERE idUsuario = ?";
            PreparedStatement pSt = cn.prepareStatement(sentenciaEliminar);

            pSt.setInt(1, usuario.getIdUsuario());

            int filasAfectadas = pSt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Registro eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el registro con el ID proporcionado.");
            }

            // Cerrar recursos
            pSt.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el registro: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}