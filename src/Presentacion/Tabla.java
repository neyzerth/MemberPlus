package Presentacion;

public class Tabla {
    private static String borIzqSup = " ╔═";
    private static String borIzqInf = " ╚═";
    private static String borDerSup = "═╗";
    private static String borDerInf = "═╝";
    private static String colum = " ║ ";
    private static String colIzq = "║ ";
    private static String colDer = " ║";
    private static String cruz = "═╬═";
    private static String norT = "═╦═";
    private static String invT = "═╩═";
    private static String derT = " ╠═";
    private static String IzqT = "═╣ ";

    public static void main(String[] args) {
        
        //printCelda("12345");
        //printCeldaColor(Color.amarillo("12345"));
        String [] columnas = {"Columna1", "Columna200000000000000000000", "Columna3"};
        Object [] [] filas = {
            {"Pedroaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1, true },
            {"Luis", 2, true },
            {"Maria", 5, false }
        };
        
        tabla(columnas, filas);
    }

    public static void tabla(String [] columnas, Object[][] datos){

        String linCabecera = borIzqSup;
        String cabecera = colum;
        String linFilas = derT;
        String filas = "";
        String linFinal = borIzqInf;
        int [] esp = longMayor(columnas, datos);
        
        for (int i = 0; i < columnas.length; i++) {
            linCabecera += linea(esp[i]);
            linFinal += linea(esp[i]);
            linFilas += linea(esp[i]);
            
            if( i < columnas.length - 1){
                linCabecera += norT;
                linFinal += invT;
                linFilas += cruz;
            }
            
            cabecera += columnas[i];
            cabecera += espacio(esp[i] - columnas[i].length());
            cabecera += colum;

        }
        linCabecera += borDerSup;
        linFinal += borDerInf;
        linFilas += IzqT;

        int k = 0;
        for (Object[] fila : datos) {
            filas += linFilas + "\n";
            filas += colum;
            for (int j = 0; j < fila.length; j++) {
                String dato = String.valueOf(fila[j]);
                filas += dato + espacio(esp[j] - dato.length());
                filas += colum;
            }
            k++;
            if(k < fila.length)
                filas += "\n";
        }

        System.out.println(linCabecera);
        System.out.println(cabecera);
        System.out.println(filas);
        System.out.println(linFinal);
    
    }

    public static int [] longMayor(String [] columnas, Object[][] datos){
        int [] maximos = new int [columnas.length];

        for (int i = 0; i < maximos.length; i++) {
            maximos[i] = columnas[i].length();
        }

        for (Object[] dato : datos) {
            for (int i = 0; i < maximos.length; i++) {
                String txt = String.valueOf(dato[i]);
                maximos[i] = Math.max(maximos[i], txt.length());
            }
        }
        return maximos;
    }
    
    private static String linea(int n){
        String lineas = "";
        for (int i = 0; i < n; i++) {
            lineas += "═";
        }
        return lineas;
    }
    private static String espacio(int n){
        String espacios = "";
        for (int i = 0; i < n; i++) {
            espacios += " ";
        }
        return espacios;
    }
    


    //OTROS
    private static void celda(String txt, int tamano){
        System.out.println(borIzqSup + linea(tamano) + borDerSup);
        System.out.println(colIzq + txt + colDer);
        System.out.println(borIzqInf + linea(tamano) + borDerInf);
    }
    public static void printCelda(String txt){
        celda(txt, txt.length());
    }
    public static void printCelda(Object obj){
        String txt = String.valueOf(obj);
        celda(txt, txt.length());
    }
    public static void printCeldaColor(String txt){
        celda(txt, txt.length()-9);
    }
    
    public static int sumarArreglo(String [] columnas){
        int cant = 0;
        for (String columna : columnas) {
            cant += columna.length() + 2;
        }
        return cant;
    }



    
}
