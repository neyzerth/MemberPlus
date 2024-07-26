package Persistencia.Tablas;

import java.sql.Date;


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
    
    // Funciones para operar tarjetas
    public Object [] obtenerTarjetaPorId(int idTarjeta) {
        return ejecutarSelectPorID(idTarjeta);
    }

    // Método para interactuar con el usuario y mostrar la tarjeta por número
    public Object [] obtenerTarjetaPorNum(String numTarjeta) {
        return ejecutarSelectUno(getNomColumna(1),numTarjeta);
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
        return existeRegistro(getNomColumna(1), numTarjeta);
    }

    // Eliminar una tarjeta
    public boolean eliminarTarjetaDB(int idTarjeta) {
        String query = delete(idTarjeta);
        return ejecutarDelete(query);
    }

}
