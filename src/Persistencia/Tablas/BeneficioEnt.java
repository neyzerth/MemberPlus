package Persistencia.Tablas;

import Persistencia.Query;
import java.sql.Date;

//La clase Beneficio reprensenta un beneficio que puede tener una persona
//contiene informacion sobre el nombre del beneficio, su identificador, porcentajes relacionados
//con puntos y Cashback y fechas de inicio y vencimiento

public class BeneficioEnt extends Query {
    public BeneficioEnt() {
        super("beneficio",
                "idBeneficio",
                "nombre",
                "fecInicio",
                "fecVen",
                "porcentajePuntos",
                "porcentajeCashBack",
                "descuento"); 
    }

    // Obtener un beneficio específico por su ID
    public Object[] obtenerBeneficioPorIdDB(int idBeneficio) {
        return ejecutarSelectPorID(idBeneficio);
    }

    // Verificar si un beneficio ya existe en la base de datos por nombre
    public boolean existeBeneficio(String nombreBeneficio) {
        return existeRegistro(getNomColumna(1), nombreBeneficio);
    }

    // Actualizar un beneficio
    public boolean actualizarBeneficioDB(int idBeneficio, String nombre,Date fecInicio, Date fecVen, int porcentajePuntos, int porcentajeCashBack, int descuento) {
        Object[] valores = { idBeneficio, nombre, fecInicio, fecVen, porcentajePuntos, porcentajeCashBack, descuento };
        String query = update(valores, idBeneficio);
        return ejecutarUpdate(query);
    }

    // Insertar un beneficio
    public boolean insertarBeneficioDB( String nombre, Date fecInicio, Date fecVen, int porcentajePuntos, int porcentajeCashBack, int descuento) {
        Object[] valores = { nombre, fecInicio, fecVen, porcentajePuntos, porcentajeCashBack, descuento };
        String query = insert(valores);
        return ejecutarInsert(query);
    }

    // Eliminar un beneficio
    public boolean eliminarBeneficioDB(int idBeneficio) {
        String query = delete(idBeneficio);
        return ejecutarDelete(query);
    }

    // Método para obtener un beneficio por nombre desde la base de datos
    public Object[] obtenerPorNombreDB(String nombreBeneficio) {
        return ejecutarSelectUno(getNomColumna(1), nombreBeneficio);
    }

}
