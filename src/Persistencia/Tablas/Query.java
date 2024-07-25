package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import Persistencia.Conexion;

public class Query {

    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String[] getNomColumnas() {
        return this.nomColumnas;
    }

    public String getNomColumna(int i) {
        return this.nomColumnas[i];
    }

    public void setNomColumnas(String[] nomColumnas) {
        this.nomColumnas = nomColumnas;
    }

    public void setNomColumna(int i, String nomColumna) {
        this.nomColumnas[i] = nomColumna;
    }


    public int getCantColumnas(){
        return nomColumnas.length;
    }
    private String tabla;
    private String [] nomColumnas;

    public Query(String tabla, String... nomColumnas){
        this.tabla = tabla;
        this.nomColumnas = nomColumnas;
    }

    public String select(){
        String query = "SELECT ";
        for(int i = 0; i < nomColumnas.length; i++){
            query += nomColumnas[i];
            if(i != nomColumnas.length - 1){
                query += ", ";
            }
        }
        query += " FROM " + tabla;
        return query;
    }

    public Object[][] ejecutarSelect() {
        Object[][] registros = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(select());

                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();
                
                int cantColumnas = getCantColumnas();
                registros = new Object[rowCount][cantColumnas];

                int rowIndex = 0;
                while (rs.next()) {
                    for(int i = 0; i < cantColumnas; i++){
                        registros[rowIndex][i] = rs.getObject(getNomColumna(i));
                    }
                    rowIndex++;
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registros;
    }

    public String selectUno(){
        String query = select() + " WHERE " + nomColumnas[0] + " = ?";
        return query;
    }

    public String insert(Object[] valores) {
        String query = "INSERT INTO " + tabla + " (";
        
        // Construir la lista de columnas
        for (int i = 0; i < nomColumnas.length; i++) {
            query += nomColumnas[i];
            if (i != nomColumnas.length - 1) {
                query += ", ";
            } else {
                query += ") VALUES (";
            }
        }
        
        // Construir la lista de valores
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] instanceof String || valores[i] instanceof Date) {
                query += "'" + valores[i] + "'";
            } else {
                query += valores[i];
            }
            
            if (i != valores.length - 1) {
                query += ", ";
            }
        }
        
        query += ")";
        
        return query;
    }

    public boolean ejecutarInsert(String query) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean resultado = false;

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                int filasInsertadas = stmt.executeUpdate(query);

                if (filasInsertadas > 0) {
                    resultado = true;
                }

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    public String update(Object[] valores, int id) {
        String query = "UPDATE " + tabla + " SET ";

        // Construir la lista de columnas y valores a actualizar
        for (int i = 0; i < nomColumnas.length; i++) {
            query += nomColumnas[i] + " = ";
        
            if (valores[i] instanceof String || valores[i] instanceof Date) {
                query += "'" + valores[i] + "'";
            } else {
                query += valores[i];
            }

            if (i != nomColumnas.length - 1) {
                query += ", ";
            } else {
                query += " ";
            }
        }

        query += "WHERE " + nomColumnas[0] + " = " + id;

        return query;
    }

    public boolean ejecutarUpdate(String query) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean resultado = false;

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                int filasActualizadas = stmt.executeUpdate(query);

                if (filasActualizadas > 0) {
                    resultado = true;
                }

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    public String delete(int id) {
        String query = "DELETE FROM " + tabla + " WHERE " + nomColumnas[0] + " = " + id;
        return query;
    }

    public boolean ejecutarDelete(String query) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        boolean resultado = false;

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                int filasEliminadas = stmt.executeUpdate(query);

                if (filasEliminadas > 0) {
                    resultado = true;
                }

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }
}
