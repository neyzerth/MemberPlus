package Logica;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatoFecha {

    public static Date fecha(String fecha){
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecNac = new java.sql.Date(formato.parse(fecha).getTime());
            return fecNac;
        } catch (ParseException e) {
            return null;
        }        
    }

    // Crea un objeto Date de SQL a partir de valores de día, mes y año.
    
    public static Date fecha(int dia, int mes, int anio){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, anio);
            calendar.set(Calendar.MONTH, mes - 1);
            calendar.set(Calendar.DAY_OF_MONTH, dia);
            
            long tiempo = calendar.getTimeInMillis();
            Date fecha = new java.sql.Date(tiempo);
            return fecha;
        } catch (Exception e){
            return null;
        }
    }

    public static Date fechaActual(){
        Calendar calendar = Calendar.getInstance();
        long tiempo = calendar.getTimeInMillis();
        Date fecha = new java.sql.Date(tiempo);
        return fecha;
    }

    public static int diaActual(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int mesActual(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int anioActual(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

}