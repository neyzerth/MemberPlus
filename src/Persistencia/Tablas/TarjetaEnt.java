package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Persistencia.Conexion;


//Falto trar el nivel jajaja
public class TarjetaEnt {

    public Object[][] obtenerTodasLasTarjetas() {
        Object[][] tarjetaArray = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente FROM tarjeta";
                
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);

                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();

                tarjetaArray = new Object[rowCount][8];

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

 //Funcion que se puede utlizar en otra capa 
    public void imprimirTarjeta(Object[][] tarjetaArray) {
        for (Object[] row : tarjetaArray) {
            System.out.println("ID: " + row[0] + ", Número: " + row[1] + ", Fecha Exp: " + row[2] +
                               ", Fecha Ven: " + row[3] + ", Activo: " + row[4] + ", Saldo: " + row[5] +
                               ", Puntos: " + row[6] + ", Cliente: " + row[7]);
        }
    }

    public boolean actualizarTarjeta(int idTarjeta, String numTarjeta, java.sql.Date fecExp, java.sql.Date fecVen, boolean activo, float saldo, int puntos, int cliente) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean actualizado = false;

        if (conn != null) {
            try {
                String updateQuery = "UPDATE tarjeta SET numTarjeta = ?, fecExp = ?, fecVen = ?, activo = ?, saldo = ?, puntos = ?, cliente = ? WHERE idTarjeta = ?";
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, numTarjeta);
                pstmt.setDate(2, fecExp);
                pstmt.setDate(3, fecVen);
                pstmt.setBoolean(4, activo);
                pstmt.setFloat(5, saldo);
                pstmt.setInt(6, puntos);
                pstmt.setInt(7, cliente);
                pstmt.setInt(8, idTarjeta);

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

    //Funcion que deberia de ir en otra capa 
    //ponerle un nombre mas adeucado
    public  void realizarOperaciones() {
        try {
            TarjetaEnt tarjetaEnt = new TarjetaEnt();

        // Obtener y imprimir todas las tarjetas se imprime antes para ver los datos
        Object[][] tarjetaArray = tarjetaEnt.obtenerTodasLasTarjetas();
        tarjetaEnt.imprimirTarjeta(tarjetaArray);

        // Datos de ejemplo para actualizar 
        //se tiene que hacer con entrada de teclado
        int idTarjeta = 1;
        String numTarjeta = "1234567890123456";
        java.sql.Date fecExp = java.sql.Date.valueOf("2023-01-01");
        java.sql.Date fecVen = java.sql.Date.valueOf("2026-01-01");
        boolean activo = true;
        float saldo = 179;
        int puntos = 109;
        int cliente = 1;

        boolean resultado = tarjetaEnt.actualizarTarjeta(idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente);

        if (resultado) {
            System.out.println("La tarjeta se actualizó correctamente.");
        } else {
            System.out.println("Hubo un error al actualizar la tarjeta.");
        }

        // Obtener y imprimir todas las tarjetas nuevamente después de la actualización
        tarjetaArray = tarjetaEnt.obtenerTodasLasTarjetas();
        tarjetaEnt.imprimirTarjeta(tarjetaArray);
            
        } catch (Exception e) {
            e.printStackTrace();    
        }
        
    }
}
