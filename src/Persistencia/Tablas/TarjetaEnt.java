package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Persistencia.Conexion;

public class TarjetaEnt {

    public Object[][] obtenerTodasLasTarjetas() {
        Object[][] tarjetasArray = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente FROM tarjeta";
                
                // Crear un Statement con ResultSet para poder ir hasta el final de la tabla y saber las columnas
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);

                // Contar el número de filas
                rs.last(); // Moverse a la última fila para contar
                int rowCount = rs.getRow();
                rs.beforeFirst(); 

            
                tarjetasArray = new Object[rowCount][8]; // 8 columnas en la tabla

                int rowIndex = 0;
                while (rs.next()) {
                    tarjetasArray[rowIndex][0] = rs.getInt("idTarjeta");
                    tarjetasArray[rowIndex][1] = rs.getString("numTarjeta");
                    tarjetasArray[rowIndex][2] = rs.getDate("fecExp");
                    tarjetasArray[rowIndex][3] = rs.getDate("fecVen");
                    tarjetasArray[rowIndex][4] = rs.getBoolean("activo");
                    tarjetasArray[rowIndex][5] = rs.getFloat("saldo");
                    tarjetasArray[rowIndex][6] = rs.getInt("puntos");
                    tarjetasArray[rowIndex][7] = rs.getInt("cliente");
                    rowIndex++;
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tarjetasArray;
    }
}
