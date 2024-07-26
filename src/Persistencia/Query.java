package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Query {

    private String tabla;
    private String [] columnas;

    public Query(String tabla, String... nomColumnas){
        this.tabla = tabla;
        this.columnas = nomColumnas;
    }

    public String select(String... columnas){
        String query = "SELECT ";
        for(int i = 0; i < columnas.length; i++){
            query += columnas[i];
            if(i != columnas.length - 1){
                query += ", ";
            }
        }
        query += " FROM " + tabla;
        return query;
    }

    public String select(){
        return select(getColumnas());
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
            }
        }
        return registros;
    }

    public String selectUno(String... columna){
        String query = select() + " WHERE " + columna[0] + " = ?";
        for (int i = 1; i < columna.length; i++) {
            query += " AND " + columna[i] + " = ?";
        }
        return query;
    }

    public Object[] ejecutarSelectUno(String select, Object... valor) {
        Object[] registro = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(select);
                for (int i = 0; i < valor.length; i++) {
                    pstmt.setObject((i + 1), valor[i]);
                }

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    registro = new Object[getCantColumnas()];
                    for (int i = 0; i < getCantColumnas(); i++) {
                        registro[i] = rs.getObject(getNomColumna(i));
                    }
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public Object[] ejecutarSelectPorID(int valor){
        return ejecutarSelectUno(selectUno(getNomColumna(0)), valor);
    }

    public boolean existeRegistro(String[] columna, Object... valor) {
        boolean existe = false;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = "SELECT EXISTS (SELECT 1 FROM " + tabla + " WHERE ";
                for (int i = 0; i < columna.length; i++) {
                    query += "\'" + columna[i] +"\'" + " = ? ";
                    if(i < columna.length - 1)
                        query+= "AND ";
                }
                query += ")";

                PreparedStatement pstmt = conn.prepareStatement(query);
                for (int i = 0; i < valor.length; i++) {
                    pstmt.setObject(i, valor[i]);
                }

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    existe = rs.getBoolean(1);
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return existe;
    }
    
    public boolean existeRegistro(String columna, Object valor) {
        String[] columnas = {columna};
        return existeRegistro(columnas, valor);
    }


    public String insert(Object[] valores) {
        String query = "INSERT INTO " + tabla + " (";
        
        // Construir la lista de columnas
        for (int i = 0; i < columnas.length; i++) {
            query += columnas[i];
            if (i != columnas.length - 1) {
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
            }
        }

        return resultado;
    }

    public String update(Object[] valores, int id) {
        String query = "UPDATE " + tabla + " SET ";
    
        // Construir la lista de columnas y valores a actualizar
        for (int i = 0; i < columnas.length; i++) {
            query += columnas[i] + " = ";
            
            if (valores[i] instanceof String || valores[i] instanceof Date) {
                query += "'" + valores[i] + "'";
            } else {
                query += valores[i];
            }
    
            if (i != columnas.length - 1) {
                query += ", ";
            } else {
                query += " ";
            }
        }
    
        query += "WHERE " + columnas[0] + " = " + id;
    
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
            }
        }

        return resultado;
    }

    public String delete(int id) {
        String query = "DELETE FROM " + tabla + " WHERE " + columnas[0] + " = " + id;
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
            }
        }

        return resultado;
    }

     // Método para formatear un registro de la tabla como cadena para cuando busquemos un registro salga asi 
     //idTarjeta: 17, numTarjeta: 2, fecExp: 2020-02-02, fecVen: 2020-02-02, activo: true, saldo: 1.0, puntos: 1, cliente: 1, nivel: 1
    public String formatearRegistro(Object[] registro) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < registro.length; i++) {
            sb.append(columnas[i]).append(": ").append(registro[i]);
            if (i < registro.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String[] getColumnas() {
        return this.columnas;
    }

    public String[] getColumnas(int... pos) {
        String[] columnasSelec = new String[pos.length];
        for (int i = 0; i < pos.length; i++) {
            columnasSelec[i] = this.columnas[pos[i]];
        }
        return columnasSelec;
    }

    public String getNomColumna(int i) {
        return this.columnas[i];
    }

    public void setColumnas(String[] nomColumnas) {
        this.columnas = nomColumnas;
    }

    public void setNomColumna(int i, String nomColumna) {
        this.columnas[i] = nomColumna;
    }


    public int getCantColumnas(){
        return columnas.length;
    }
}
