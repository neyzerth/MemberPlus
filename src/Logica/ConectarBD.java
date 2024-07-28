package Logica;

import Persistencia.Conexion;

public class ConectarBD {

    public static String[] probar(){
        Conexion conexion = new Conexion();
        conexion.conectar();
        String [] atributos = new String[3];

            atributos[0] = conexion.getHost(); 
            atributos[1] = conexion.getError();
            atributos[2] =  conexion.getSqlState();

        return atributos;
    }
    
}
