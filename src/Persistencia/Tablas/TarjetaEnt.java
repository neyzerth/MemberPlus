package Persistencia.Tablas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Persistencia.Conexion;

public class TarjetaEnt extends Query {

    public TarjetaEnt() {
        super("tarjeta",
                "idTarjeta",
                "numTarjeta",
                "fecExp",
                "fecVen",
                "activo",
                "saldo",
                "puntos",
                "cliente",
                "nivel");
    }

    // Mostrar una tarjeta específica
    public Object[] obtenerTarjetaPorIdDB(int idTarjeta) {
        Object[] tarjeta = null;
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn != null) {
            try {
                String query = selectUno();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, idTarjeta);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    tarjeta = new Object[getCantColumnas()];
                    for (int i = 0; i < getCantColumnas(); i++) {
                        tarjeta[i] = rs.getObject(getNomColumna(i));
                    }
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
        Object[] valores = { idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel };
        String query = update(valores, idTarjeta);
        return ejecutarUpdate(query);
    }

    // Insertar una tarjeta
    public boolean insertarTarjetaDB(int idTarjeta, String numTarjeta, Date fecExp, Date fecVen, boolean activo,
            float saldo, int puntos, int cliente, int nivel) {
        Object[] valores = { idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel };
        String query = insert(valores);
        return ejecutarInsert(query);
    }
        // Verificar si el numTarjeta ya existe en la base de datos
        public boolean existeNumTarjeta(String numTarjeta) {
            boolean existe = false;
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();
    
            if (conn != null) {
                try {
                    String query = "SELECT COUNT(*) FROM tarjeta WHERE numTarjeta = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, numTarjeta);
    
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

    // Eliminar una tarjeta
    public boolean eliminarTarjetaDB(int idTarjeta) {
        String query = delete(idTarjeta);
        return ejecutarDelete(query);
    }

    // Funciones para operar tarjetas
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

  // Método para interactuar con el usuario y mostrar la tarjeta por número
    public void obtenerTarjetaPorNum(String numTarjeta) {
    try {
        Object[] tarjeta = obtenerPorNumDB(numTarjeta);
        if (tarjeta != null) {
            String detallesTarjeta = formatearRegistro(tarjeta);
            System.out.println(detallesTarjeta);
        } else {
            System.out.println("No se encontró la tarjeta con número: " + numTarjeta);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void actualizarTarjeta(int idTarjeta, String numTarjeta, Date fecExp, Date fecVen,
            boolean activo, float saldo, int puntos, int cliente, int nivel) {
        try {
            boolean resultado = actualizarTarjetaDB(idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos,
                    cliente, nivel);
            if (resultado) {
                System.out.println("La tarjeta con ID " + idTarjeta + " se actualizó correctamente.");
            } else {
                System.out.println("Hubo un error al actualizar la tarjeta con ID: " + idTarjeta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Insertar tarjeta con verificación de existencia
    public void insertarTarjeta(int idTarjeta,String numTarjeta, Date fecExp, Date fecVen, boolean activo,
            float saldo, int puntos, int cliente, int nivel) {
        try {
            if (existeNumTarjeta(numTarjeta)) {
                System.out.println("Error: El número de tarjeta " + numTarjeta + " ya existe en la base de datos.");
            } else {
                boolean resultado = insertarTarjetaDB(idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel );
                if (resultado) {
                    System.out.println("La tarjeta con número " + numTarjeta + " se insertó correctamente.");
                } else {
                    System.out.println("Hubo un error al insertar la tarjeta con número: " + numTarjeta);
                }
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
