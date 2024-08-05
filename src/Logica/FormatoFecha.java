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
    
    public static Date fecha(int dia, int mes, int anio){

        //Comprobar que el año este entre 1900 y 2200
        if (anio < 1900 || anio > 2200) {
            throw new IllegalArgumentException("Año "+anio+" no valido");
        }

        //Comprobar que el mes este entre 1 y 12
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes "+mes+" no valido.");

        } else { //Si es valido, que compruebe el dia

            //Comprobar que el dia este entre 1 y 31
            if(dia > 31 || dia < 1)
                throw new IllegalArgumentException("Dia "+dia+" no valido");

            switch (mes) { //Excepciones en meses

                //Comprobar que el dia no sea mayor a 30 en los siguientes meses:
                //30 dias: Abril[4], Junio[6], Septiembre[9], Noviembre[11]] 
                case 4: case 6: case 9: case 11:
                    if (dia > 30) {
                        throw new IllegalArgumentException("Dia "+dia+" no valido para el mes "+mes);
                    }
                    break;
                
                //Comprobar febrero
                //28-29 dias: Febrero[2] 
                case 2:
                    //Comprobar si el año es bisiesto
                    if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)){
                        if(dia > 29) //Comprobar que no sea mayor a 29
                            throw new IllegalArgumentException("Dia "+dia+" no valido para febrero en año bisiesto ");
                    } else {
                        if(dia > 28) //Si no es bisiesto, comprobar que no sea mayor a 28
                            throw new IllegalArgumentException("Dia "+dia+" no valido para febrero");
                    }
                    break;
            }
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anio);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        
        long tiempo = calendar.getTimeInMillis();
        Date fecha = new java.sql.Date(tiempo);
        return fecha;
    }

}