package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Persistencia.Conexion;

public class InicioSesion {
    Usuario est=new Usuario();
    private Conexion mysql=new Conexion();
    private Connection cn=mysql.conectar();
    Scanner insertar=new Scanner(System.in);
    
    public void mostrarEstudiantes(){
        try {
            //String Query = "SELECT id,nombe FROM estudiantes"; 
            String Query="SELECT id,nombre_Est,apellido_Est,fecha_Nac,email,carrera FROM estudiantes JOIN carreras ON estudiantes.id_Carrera = carreras.id_Carrera;";

            PreparedStatement preparedStatement = cn.prepareStatement(Query);
            ResultSet resultado = preparedStatement.executeQuery(Query);
            System.out.println("\nID\t" + "Nombre\t" +   "Apellido\t" +
            "Fecha nac\t" + "Email\t\t\t" + "Carrera");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre_Est");
                String apellido = resultado.getString("apellido_Est");
                Date fecha = resultado.getDate("fecha_Nac");
                String email = resultado.getString("email");
                String carrera = resultado.getString("carrera");
                System.out.println(id + "\t" + nombre + 
                "\t" + apellido+ "\t\t" + fecha
                + "\t" + email
                + "\t" + carrera);
            }                           
            System.out.println("\n");
           
        }
         catch (Exception e) {
            System.out.println(e);
        }
    }


    public void insertarEstudiantes(){
        
        java.util.Date fecha;
        try {
            // Preparar la sentencia INSERT
            String sentenciaInsert = "INSERT INTO estudiantes (id, nombre_Est, apellido_Est,fecha_Nac, email, id_Carrera) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pSt = cn.prepareStatement(sentenciaInsert);

            System.out.println("Introduce el Nombre: ");
            String nombre=insertar.nextLine();
            est.setNombre(nombre);
            System.out.println("Introduce el Apellido: ");
            String apellido=insertar.nextLine();
            est.setApellido(apellido);
            System.out.println("Introduce la fecha de nacimiento: ");
            String fecha_n=insertar.nextLine();
            System.out.println("Introduce el Email: ");
            String email=insertar.nextLine();
            est.setEmail(email);
            System.out.println("Introduce la Carrera: ");
            int idcarrera=insertar.nextInt();
            est.setId_carrera(idcarrera);
         
            // Asignar valores a los parámetros
            pSt.setInt(1, 0);
            pSt.setString(2,est.getNombre() );
            pSt.setString(3,est.getApellido() );
            //Convertir string a fecha.
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(fecha_n);
            fecha =formato.parse(fecha_n); 
            System.out.println(fecha); 
            pSt.setDate(4,new java.sql.Date(fecha.getTime()));
            pSt.setString(5,est.getEmail() );
            pSt.setInt(6, est.getId_carrera());
            // Ejecutar la sentencia
            int filasInsertadas = pSt.executeUpdate();
            System.out.println("Filas insertadas: " + filasInsertadas);
            // Cerrar recursos
            pSt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarEstudiantes(){
        java.util.Date fecha;
        
        try {
            // Preparar la sentencia INSERT
            //String sentenciaupdate = "UPDATE estudiantes SET nombre_Est=?, apellido_Est=?,fecha_Nac=?, email=?, id_Carrera=? WHERE id=?";
            String sentenciaupdate="UPDATE estudiantes SET nombre_Est = ?, apellido_Est = ?, fecha_Nac=?,email = ?, id_Carrera = ? WHERE estudiantes.id = ?";
            PreparedStatement pSt = cn.prepareStatement(sentenciaupdate);
            System.out.println("Inserta el id del estudiante a modificar: ");
            int id=insertar.nextInt();
            est.setId(id);

            System.out.println("Introduce el Nombre: ");
            String nombre1=insertar.next();
            est.setNombre(nombre1);
            System.out.println("Introduce el Apellido: ");
            String apellido=insertar.next();
            est.setApellido(apellido);
            System.out.println("Introduce la fecha de nacimiento: ");
            String fecha_n=insertar.next();
            System.out.println("Introduce el Email: ");
            String email=insertar.next();
            est.setEmail(email);
            System.out.println("Introduce la Carrera: ");
            int idcarrera=insertar.nextInt();
            est.setId_carrera(idcarrera);
         
            // Asignar valores a los parámetros
            
            pSt.setString(1,est.getNombre() );
            pSt.setString(2,est.getApellido() );
            //Convertir string a fecha.
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(fecha_n);
            fecha =formato.parse(fecha_n); 
            System.out.println(fecha); 
            pSt.setDate(3,new java.sql.Date(fecha.getTime()));
            pSt.setString(4,est.getEmail() );
            pSt.setInt(5, est.getId_carrera());
            pSt.setInt(6, est.getId());
            // Ejecutar la sentencia
            int filasInsertadas = pSt.executeUpdate();
            System.out.println("Filas insertadas: " + filasInsertadas);
            // Cerrar recursos
            pSt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    public void eliminarEstudiantes(){
        System.out.println("Inserta el id del estudiante a modificar: ");
        int id=insertar.nextInt();
        est.setId(id);
        try{
        String sentenciaEliminar = "DELETE FROM estudiantes WHERE id = ?";
        PreparedStatement pSt = cn.prepareStatement(sentenciaEliminar);
        
            pSt.setInt(1,est.getId());

        int filasAfectadas = pSt.executeUpdate();
        if (filasAfectadas > 0) {
                System.out.println("Registro eliminado exitosamente.");
        } else {
                System.out.println("No se encontró el registro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el registro: " + e.getMessage());
        }
}