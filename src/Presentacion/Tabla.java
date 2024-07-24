package Presentacion;

public class Tabla {
    
    private String [] cabeceras;
    private Object[][] datos;
    private int cantColumnas;
    private int [] maxColumna;
    
    private String colum, techo, borIzqSup, borIzqInf, borDerSup, borDerInf, cruz, norT, invT, derT, IzqT;


    public Tabla(int tipo, String... cabeceras){
        this.cabeceras = cabeceras;
        this.cantColumnas = cabeceras.length;
        this.maxColumna = new int[cantColumnas];
        setTipoLineas(tipo);
    }

    public Tabla(String... cabeceras){
        this(1, cabeceras);
    }
    
    public Tabla(int tipo, String [] cabeceras, Object[]... datos){
        if(cabeceras.length != datos.length)
            throw new IllegalArgumentException("Cabecera y Datos[] deben tener la misma longitud");

        this(tipo, cabeceras);
        this.datos = datos;
        this.maxColumna = longMayor(cabeceras, datos);
    }
    public Tabla(String [] cabeceras, Object[]... datos){
        this(1, cabeceras, datos);
    }
    
    
    public void imprimirTabla(){
        System.out.println(linCabecera());
        System.out.println(cabecera());
        System.out.println(filas());
        System.out.println(linFinal());
    
    }
    public void imprimirTabla(int tipo){
        setTipoLineas(tipo);
        imprimirTabla();
    }

    public void imprimirTablaSimple(){
        System.out.println(linCabecera());
        System.out.println(cabecera());
        System.out.println(filasSinInterlineas());
        System.out.println(linFinal());
    }

    public void imprimirTablaSimple(int tipo){
        setTipoLineas(tipo);
        imprimirTablaSimple();
    }
    private void setTipoLineas(int tipo){
        switch (tipo) {
            default:
            case 1:
                setLineasPlanas("║", "═");
                setLineasBordes("╔", "╗", "╚", "╝" );
                setLineasT("╦", "╩", "╠", "╣", "╬");
                break;

            case 2:
                setLineasPlanas("│", "─");
                setLineasBordes("┌", "┐", "└", "┘");
                setLineasT("┬", "┴", "├", "┤", "┼");
                break;

            case 3:
                setLineasPlanas("│", "─");
                setLineasBordes("╔", "╗", "╚", "╝");
                setLineasT("╤", "╧", "╟", "╢", "╪");
                break;

            case 4:
                setLineasPlanas("|", "-");
                setLineasBordes("+", "+", "+", "+");
                setLineasT("+", "+", "+", "+", "+");
                break;

        }

    }

    private void setLineasPlanas(String columna, String techo){
        this.colum = " " + columna + " ";
        this.techo = techo;
    }

    private void setLineasBordes( String supIzq, String supDer, String infIzq, String infDer){
        this.borIzqSup = " " + supIzq + techo;
        this.borIzqInf = " " + infIzq + techo;
        this.borDerSup = techo + supDer;
        this.borDerInf = techo + infDer;
    }
    private void setLineasT(String normal, String invertida, String derecha, String izquierda , String cruz){
        this.norT = techo + normal + techo;
        this.invT = techo + invertida + techo;
        this.derT = " " + derecha + techo;
        this.IzqT = techo +izquierda;
        this.cruz = techo + cruz + techo;
    }


    

    private String linTabla(String lin1, String lin2, String lin3){
        String linTabla = lin1;
        
        for (int i = 0; i < cantColumnas; i++) {
            linTabla += linea(maxColumna[i]);
            
            if( i < cantColumnas - 1){
                linTabla += lin2;
            }
        }
        linTabla += lin3;
        return linTabla;
    }

    private String celdas(Object [] celdas){
        String fila = colum;
        
        for (int i = 0; i < cantColumnas; i++) {
            String dato = String.valueOf(celdas[i]);
            fila += dato;;
            fila += espacio(maxColumna[i] - dato.length());
            fila += colum;
        }
        return fila;
    }
    private String cabecera(){
        return celdas(getCabeceras());
    }

    private String filas(){
        String filas = "";
        int i = 0;
        
        for (Object[] fila : getDatos()) {
            filas += linFilas();
            filas += "\n";
            filas += celdas(fila);
            i++;
            if(i < fila.length)
                filas += "\n";
        }
        return filas;
    }
    private String filasSinInterlineas(){
        String filas = linFilas() + "\n";
        int i = 0;
        
        for (Object[] fila : getDatos()) {
            filas += celdas(fila);
            i++;
            if(i < fila.length)
                filas += "\n";
        }
        return filas;
    }

    private String linCabecera(){
        return linTabla(borIzqSup, norT, borDerSup);
    }
    private String linFilas(){
        return linTabla(derT, cruz, IzqT);
    }
    private String linFinal(){
        return  linTabla(borIzqInf, invT, borDerInf);
    }

    
    private String linea(int n){
        String lineas = "";
        for (int i = 0; i < n; i++) {
            lineas += techo;
        }
        return lineas;
    }
    public static String espacio(int n){
        String espacios = "";
        for (int i = 0; i < n; i++) {
            espacios += " ";
        }
        return espacios;
    }
    
    private int [] longMayor(String [] columnas, Object[][] datos){
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


    public String[] getCabeceras() {
        return this.cabeceras;
    }
    public String getCabecera(int i) {
        return this.cabeceras[i];
    }

    public void setCabeceras(String[] cabeceras) {
        this.cabeceras = cabeceras;
    }

    public void setCabecera(int i, String cabecera) {
        this.cabeceras[i] = cabecera;
        if(cabecera.length() > maxColumna[i])
            maxColumna[i] = cabecera.length();
    }

    public Object[][] getDatos() {
        return this.datos;
    }

    public void setDatos(Object[][] datos) {
        this.datos = datos;
    }
}

