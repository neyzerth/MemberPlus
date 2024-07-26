package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Persistencia.Conexion;

public class RolEnt extends Query {

    public RolEnt() {
        super("rol",
                "idRol",
                "nombre",
                "descripcion");
    }

    // Obtener un rol específico por su ID
    public Object[] obtenerRolPorIdDB(int idRol) {
        Object[] rol = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = selectUno();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, idRol);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    rol = new Object[getCantColumnas()];
                    for (int i = 0; i < getCantColumnas(); i++) {
                        rol[i] = rs.getObject(getNomColumna(i));
                    }
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rol;
    }

    //Actualizar Rol
    public boolean actualizarRolDB(int idRol, String nombre, String descripcion) {
        Object[] valores = { idRol,nombre, descripcion };
        String query = update(valores, idRol);
        return ejecutarUpdate(query);
    }
    
    
    // Insertar un rol
    public boolean insertarRolDB(int idRol,String nombre, String descripcion) {
        Object[] valores = { idRol,nombre, descripcion };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Verificar si un rol ya existe en la base de datos por nombre
    public boolean existeRol(String nombreRol) {
        boolean existe = false;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) FROM rol WHERE nombre = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, nombreRol);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
    }

    // Eliminar un rol
    public boolean eliminarRolDB(int idRol) {
        String query = delete(idRol);
        return ejecutarDelete(query);
    }

    // método para obtener y mostrar un rol por ID
    public void obtenerRolPorId(int idRol) {
        try {
            Object[] rol = obtenerRolPorIdDB(idRol);
            if (rol != null) {
                System.out.println("ID Rol: " + rol[0] + ", Nombre: " + rol[1] + ", Descripción: " + rol[2]);
            } else {
                System.out.println("No se encontró el rol con ID: " + idRol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar detalles de un rol por nombre
    public void obtenerRolPorNombre(String nombreRol) {
        try {
            Object[] rol = obtenerPorNombreDB(nombreRol);
            if (rol != null) {
                String detallesRol = formatearRegistro(rol);
                System.out.println(detallesRol);
            } else {
                System.out.println("No se encontró el rol con nombre: " + nombreRol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método para obtener un rol por nombre desde la base de datos
    private Object[] obtenerPorNombreDB(String nombreRol) {
        Object[] rol = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT * FROM rol WHERE nombre = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, nombreRol);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    rol = new Object[getCantColumnas()];
                    for (int i = 0; i < getCantColumnas(); i++) {
                        rol[i] = rs.getObject(getNomColumna(i));
                    }
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rol;
    }

    // Método para formatear los detalles de un rol para mostrar al usuario
    public String formatearRegistro(Object[] rol) {
        // Implementa el formateo según tu necesidad
        return  formatearRegistro(rol);
    }

    public void actualizarRol(int idRol, String nombre, String descripcion) {
        try {
            boolean resultado = actualizarRolDB(idRol, nombre, descripcion);
            if (resultado) {
                System.out.println("El rol con ID " + idRol + " se actualizó correctamente.");
            } else {
                System.out.println("Hubo un error al actualizar el rol con ID: " + idRol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // Método para insertar un rol con verificación de existencia
    public void insertarRol(int idRol,String nombre, String descripcion) {
        try {
            if (existeRol(nombre)) {
                System.out.println("Error: El rol con nombre " + nombre + " ya existe en la base de datos.");
            } else {
                boolean resultado = insertarRolDB(idRol,nombre, descripcion);
                if (resultado) {
                    System.out.println("El rol con nombre " + nombre + " se insertó correctamente.");
                } else {
                    System.out.println("Hubo un error al insertar el rol con nombre: " + nombre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para  eliminar un rol por ID
    public void eliminarRol(int idRol) {
        try {
            boolean resultado = eliminarRolDB(idRol);
            if (resultado) {
                System.out.println("El rol con ID " + idRol + " se eliminó correctamente.");
            } else {
                System.out.println("Hubo un error al eliminar el rol con ID: " + idRol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

