package Presentacion.Despliegue;

import Presentacion.Formato.Texto;

public class Cuadro{
    // tipos de linea para las tablas
    private String [] texto;
    private int maxLinea;
    protected String colum, techo, 
        borIzqSup, borIzqInf, borDerSup, borDerInf;

    protected int tipoLinea; // Tipo de linea a desplegar

    public Cuadro(int tipoLinea, String... texto){
        this.texto = texto;
        this.tipoLinea = tipoLinea;
        this.maxLinea = longMayor(texto); // Llama a textoMayor después de inicializar texto
        setTipoLineas(this.tipoLinea);
    }

    public Cuadro(String... texto){
        this(1, texto);
    }

    public void imprimirCuadro(){
        String linea = linea(maxLinea);
        String cuadro = borIzqSup + linea + borDerSup ;
        cuadro += "\n" ;
        for (String txt : texto) {
            int longFila = txt.length();
            if(contieneColor(txt))
                longFila -= 9;
            
            String fila = colum;
            fila += txt;
            fila += Texto.espacio(maxLinea - longFila);
            fila += colum;

            cuadro += fila+  "\n";
        }
        cuadro += borIzqInf + linea + borDerInf;

        System.out.println(cuadro);
    }

    public void imprimirCuadroNum(){
        String linea = linea(maxLinea + 3);
        String cuadro = borIzqSup + linea + borDerSup ;
        cuadro += "\n" ;
        for (int i = 0; i < texto.length; i++) {
            int longFila = texto[i].length();
            if(contieneColor(texto[i]))
                longFila -= 9;
            
            String fila = colum;
            fila += (i + 1) + ". ";
            fila += texto[i];
            fila += Texto.espacio(maxLinea - longFila);
            fila += colum;

            cuadro += fila+  "\n";
        }
        cuadro += borIzqInf + linea + borDerInf;

        System.out.println(cuadro);
    }

    public void imprimirCuadroList(String caracter){
        String linea = linea(maxLinea + caracter.length() + 1);
        String cuadro = borIzqSup + linea + borDerSup ;
        cuadro += "\n" ;
        for (String txt : texto) {
            int longFila = txt.length();
            if(contieneColor(txt))
            longFila -= 9;
            
            String fila = colum;
            fila += caracter + " ";
            fila += txt;
            fila += Texto.espacio(maxLinea - longFila);
            fila += colum;

            cuadro += fila+  "\n";
        }
        cuadro += borIzqInf + linea + borDerInf;

        System.out.println(cuadro);
    }

        // --------- ITERANDO SIMBOLOS -----
    // iterando linea plana
    protected String linea(int n) {
        String lineas = "";
        for (int i = 0; i < n; i++) {
            lineas += techo;
        }
        return lineas;
    }

    // calcular la longitud de cadena mayor en cada columna
    private int longMayor(String[] filas) {
        if(filas.length == 0)
            return 0;

        int maximo = 0;

        for (String fila : filas) {
            int longFila = fila.length();
            if(contieneColor(fila))
                longFila -= 7;

            maximo = Math.max(maximo, longFila);
        }
        return maximo;
    }

    protected void setTipoLineas(int tipo) {
        switch (tipo) {
            default:
            case 1:
                setLineasPlanas("│", "─");
                setLineasBordes("┌", "┐", "└", "┘");
                break;

            case 2:
                setLineasPlanas("║", "═");
                setLineasBordes("╔", "╗", "╚", "╝");
                break;

            case 3:
                setLineasPlanas("|", "-");
                setLineasBordes("+", "+", "+", "+");
                break;

            case 4:
                setLineasPlanas("│", "─");
                setLineasBordes("╔", "╗", "╚", "╝");
                break;
        }
    }
    
    // CLASIFICAR TIPOS DE LINEAS PARA CAMBIAR SIMBOLO
    protected void setLineasPlanas(String columna, String techo) {
        this.colum = " " + columna + " ";
        this.techo = techo;
    }

    // CLASIFICAR TIPOS DE LINEAS PARA CAMBIAR SIMBOLO
    protected void setLineasBordes(String supIzq, String supDer, String infIzq, String infDer) {
        this.borIzqSup = " " + supIzq + techo;
        this.borIzqInf = " " + infIzq + techo;
        this.borDerSup = techo + supDer;
        this.borDerInf = techo + infDer;
    }

    protected boolean contieneColor(String texto) {
        return texto.contains("\u001B[");
    }

}
