package Persistencia.Tablas;

import java.sql.Date;

import Persistencia.Query;

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
    public Object[] obtenerTarjetaPorId(int idTarjeta) {
        return ejecutarSelectPorID(idTarjeta);
    }

    // Método para interactuar con el usuario y mostrar la tarjeta por número
    public Object[] obtenerTarjetaPorNum(String numTarjeta) {
        return ejecutarSelectUno(selectUno(getNomColumna(1)), numTarjeta);
    }
    public Object[] obtenerTarjetaPorCliente(int idCliente) {
        return ejecutarSelectUno(selectUno(getNomColumna(7)), idCliente);
    }

    // Actualizar una tarjeta
    public boolean actualizarTarjetaDB(int idTarjeta, String numTarjeta, Date fecExp, Date fecVen,
    boolean activo, float saldo, int puntos, int cliente, int nivel) {
        Object[] valores = { idTarjeta, numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel };
        String query = update(valores, idTarjeta);
        return ejecutarUpdate(query);
    }

    // Insertar una tarjeta solo si el número de tarjeta no existe previamente
    public boolean insertarTarjetaDB( String numTarjeta, Date fecExp, Date fecVen, boolean activo,
    float saldo, int puntos, int cliente, int nivel) {
        if (!existeNumTarjeta(numTarjeta)) { // Verifica que no exista una tarjeta con ese número
            Object[] valores = {numTarjeta, fecExp, fecVen, activo, saldo, puntos, cliente, nivel };
            String query = insert(valores);
            return ejecutarInsert(query);
        } else {
            return false;
        }
    }

    

    // Verificar si el numTarjeta ya existe en la base de datos
    public boolean existeNumTarjeta(String numTarjeta) {
        return existeRegistro(getNomColumna(1), numTarjeta);
    }

    public boolean existeIdTarjeta(int id){
        return existeRegistro(getNomColumna(0), id);
    }

    // Eliminar una tarjeta
    public boolean eliminarTarjetaDB(int idTarjeta) {
        String query = delete(idTarjeta);
        return ejecutarDelete(query);
    }

    public boolean cambiarActivo(int id){
        String query=updateColumna(getNomColumna(4),"0",id);
        return ejecutarUpdate(query);
    }

}
