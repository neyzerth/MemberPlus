package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Persistencia.Conexion;

public class Query {
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
        String query = select() + "WHERE " + nomColumnas[0] + "= ?";
        return query;
    };


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

    
}
