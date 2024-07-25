package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Persistencia.Conexion;

public class TarjetaEnt extends Query {

    public TarjetaEnt(){
        super("tarjeta",
            "idTarjeta",
            "numTarjeta", 
            "fecExp", 
            "fecVen", 
            "activo", 
            "saldo", 
            "puntos", 
            "cliente"
        );
    }
    
    // Mostrar todas las tarjetas
    public Object[][] obtenerTodasLasTarjetasDB() {
        Object[][] tarjetaArray = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel FROM tarjeta";

                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);

                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();

                tarjetaArray = new Object[rowCount][9]; 

                int rowIndex = 0;
                while (rs.next()) {
                    tarjetaArray[rowIndex][0] = rs.getInt("idTarjeta");
                    tarjetaArray[rowIndex][1] = rs.getString("numTarjeta");
                    tarjetaArray[rowIndex][2] = rs.getDate("fecExp");
                    tarjetaArray[rowIndex][3] = rs.getDate("fecVen");
                    tarjetaArray[rowIndex][4] = rs.getBoolean("activo");
                    tarjetaArray[rowIndex][5] = rs.getFloat("saldo");
                    tarjetaArray[rowIndex][6] = rs.getInt("puntos");
                    tarjetaArray[rowIndex][7] = rs.getInt("cliente");
                    tarjetaArray[rowIndex][8] = rs.getInt("nivel"); 
                    rowIndex++;
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tarjetaArray;
    }

    // Imprimir todas las tarjetas
    public void imprimirTarjetas(Object[][] tarjetaArray) {
        if (tarjetaArray == null || tarjetaArray.length == 0) {
            System.out.println("No hay tarjetas para mostrar.");
            return;
        }

        for (Object[] row : tarjetaArray) {
            System.out.println("ID: " + row[0] + ", Número: " + row[1] + ", Fecha Exp: " + row[2] +
                    ", Fecha Ven: " + row[3] + ", Activo: " + row[4] + ", Saldo: " + row[5] +
                    ", Puntos: " + row[6] + ", Cliente: " + row[7] + ", Nivel: " + row[8]);
        }
    }

    // Mostrar una tarjeta específica
    public Object[] obtenerTarjetaPorIdDB(int idTarjeta) {
        Object[] tarjeta = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel FROM tarjeta WHERE idTarjeta = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, idTarjeta);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    tarjeta = new Object[9]; 
                    tarjeta[0] = rs.getInt("idTarjeta");
                    tarjeta[1] = rs.getString("numTarjeta");
                    tarjeta[2] = rs.getDate("fecExp");
                    tarjeta[3] = rs.getDate("fecVen");
                    tarjeta[4] = rs.getBoolean("activo");
                    tarjeta[5] = rs.getFloat("saldo");
                    tarjeta[6] = rs.getInt("puntos");
                    tarjeta[7] = rs.getInt("cliente");
                    tarjeta[8] = rs.getInt("nivel"); 
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }
        return tarjeta;
    }

    // Actualizar una tarjeta
    public boolean actualizarTarjetaDB(int idTarjeta, String numTarjeta, Date fecExp, Date fecVen,
            boolean activo, float saldo, int puntos, int cliente, int nivel) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean actualizado = false;

        if (conn != null) {
            try {
                String updateQuery = "UPDATE tarjeta SET numTarjeta = ?, fecExp = ?, fecVen = ?, activo = ?, saldo = ?, puntos = ?, cliente = ?, nivel = ? WHERE idTarjeta = ?";
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, numTarjeta);
                pstmt.setDate(2, fecExp);
                pstmt.setDate(3, fecVen);
                pstmt.setBoolean(4, activo);
                pstmt.setFloat(5, saldo);
                pstmt.setInt(6, puntos);
                pstmt.setInt(7, cliente);
                pstmt.setInt(8, nivel); 
                pstmt.setInt(9, idTarjeta);

                int rowsAffected = pstmt.executeUpdate();
                actualizado = (rowsAffected > 0);

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }

        return actualizado;
    }

    // Insertar una tarjeta
    public boolean insertarTarjetaDB(String numTarjeta, Date fecExp, Date fecVen, boolean activo,
            float saldo, int puntos, int cliente, int nivel) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean insertado = false;

        if (conn != null) {
            try {
                String insertQuery = "INSERT INTO tarjeta (numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, numTarjeta);
                pstmt.setDate(2, fecExp);
                pstmt.setDate(3, fecVen);
                pstmt.setBoolean(4, activo);
                pstmt.setFloat(5, saldo);
                pstmt.setInt(6, puntos);
                pstmt.setInt(7, cliente);
                pstmt.setInt(8, nivel);

                int rowsAffected = pstmt.executeUpdate();
                insertado = (rowsAffected > 0);

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }

        return insertado;
    }

    // Eliminar una tarjeta
    public boolean eliminarTarjetaDB(int idTarjeta) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean eliminado = false;

        if (conn != null) {
            try {
                String deleteQuery = "DELETE FROM tarjeta WHERE idTarjeta = ?";
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
                pstmt.setInt(1, idTarjeta);

                int rowsAffected = pstmt.executeUpdate();
                eliminado = (rowsAffected > 0);

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }

        return eliminado;
    }

    
    // Funciones para operar tarjetas
    public void mostrarTodasLasTarjetas() {
        try {
            Object[][] tarjetaArray = obtenerTodasLasTarjetasDB();
            imprimirTarjetas(tarjetaArray);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void obtenerTarjetaPorId(int idTarjeta) {
        try {
            Object[] tarjeta = obtenerTarjetaPorIdDB(idTarjeta);
            if (tarjeta != null) {
                System.out.println("ID: " + tarjeta[0] + ", Número: " + tarjeta[1] + ", Fecha Exp: " + tarjeta[2] +
                        ", Fecha Ven: " + tarjeta[3] + ", Activo: " + tarjeta[4] + ", Saldo: " + tarjeta[5] +
                        ", Puntos: " + tarjeta[6] + ", Cliente: " + tarjeta[7] + ", Nivel: " + tarjeta[8]);
            } else {
                System.out.println("No se encontró la tarjeta con ID: " + idTarjeta);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void actualizarTarjeta(int idTarjeta, String numTarjeta, Date fecExp, Date fecVen,
            boolean activo, float saldo, int puntos, int cliente, int nivel) {
        try {
            boolean resultado = actualizarTarjetaDB(idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel);
            if (resultado) {
                System.out.println("La tarjeta con ID " + idTarjeta + " se actualizó correctamente.");
            } else {
                System.out.println("Hubo un error al actualizar la tarjeta con ID: " + idTarjeta);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void insertarTarjeta(String numTarjeta, Date fecExp, Date fecVen, boolean activo,
            float saldo, int puntos, int cliente, int nivel) {
        try {
            boolean resultado = insertarTarjetaDB(numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel);
            if (resultado) {
                System.out.println("La tarjeta con número " + numTarjeta + " se insertó correctamente.");
            } else {
                System.out.println("Hubo un error al insertar la tarjeta con número: " + numTarjeta);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void eliminarTarjeta(int idTarjeta) {
        try {
            boolean resultado = eliminarTarjetaDB(idTarjeta);
            if (resultado) {
                System.out.println("La tarjeta con ID " + idTarjeta + " se eliminó correctamente.");
            } else {
                System.out.println("Hubo un error al eliminar la tarjeta con ID: " + idTarjeta);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
