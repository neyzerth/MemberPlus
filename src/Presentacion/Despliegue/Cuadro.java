package Presentacion.Despliegue;

import Presentacion.Formato.Texto;

public class Cuadro{
    // tipos de linea para las tablas
    private String [] texto;
    private int maxLinea;
    private int lineaDinamico;
    private boolean centrado;
    protected String colum, techo, 
        borIzqSup, borIzqInf, borDerSup, borDerInf;

    protected int tipoLinea; // Tipo de linea a desplegar

    public Cuadro(int tipoLinea, String... texto){
        this.texto = texto;
        this.tipoLinea = tipoLinea;
        this.maxLinea = 0;
        this.lineaDinamico = 0;
        this.maxLinea = longMayor(texto); // Llama a textoMayor después de inicializar texto
        setTipoLineas(this.tipoLinea);
    }

    public Cuadro(String... texto){
        this(1, texto);
    }

    private String hacerCuadro(boolean num, String simbolo, int sumLinea){
        String linea = linea(getMaxLinea() + sumLinea);
        String cuadro = "";

        //PRIMERA LINEA
        cuadro += borIzqSup + linea + borDerSup ;
        cuadro += "\n" ;

        //CADA FILA
        for (int i = 0; i < texto.length; i++) {            
            String fila = "";
            if(num)
                simbolo = (i + 1) + ".";
            if(this.centrado)
                fila += filaCentrada(texto[i], simbolo);
            else
                fila += fila(texto[i], simbolo);

            cuadro += fila;
        }
        cuadro += borIzqInf + linea + borDerInf;

        return cuadro;
    }

    private String fila(String texto, String simbolo){
        String fila = "";
        String[] saltos = texto.split("\n");

        for (int i = 0; i < saltos.length; i++) {
            int longFila = saltos[i].length();

            if(contieneColor(saltos[i]))
                longFila -= 9;
            if(i != 0 ){
                fila += colum;
                if(!simbolo.equals(""))
                    fila +=Texto.espacio(simbolo.length() + 1);
            }
            fila += saltos[i];
            fila += Texto.espacio(getMaxLinea() - longFila);
            fila += colum + "\n";
    
        }
        if(!simbolo.equals(""))
            return colum + simbolo + " " + fila;

        return colum + fila;
    }

    private String filaCentrada(String texto, String simbolo){
        String fila = colum;
        String[] saltos = texto.split("\n");

        if(!simbolo.isEmpty())
            simbolo += " ";
        fila += simbolo;
        
        for (int i = 0; i < saltos.length; i++) {
            int longFila = saltos[i].length();
            if(contieneColor(saltos[i]))
                longFila -= 9;

            int espacio = getMaxLinea() - longFila;
            int espacio2 = espacio/2;
            int espacio1 = espacio - espacio2;

            if(i != 0 ){
                fila += colum;
                fila += Texto.espacio(simbolo.length());
            }
            fila += Texto.espacio(espacio1);
            fila += saltos[i];
            fila += Texto.espacio(espacio2);
            fila += colum + "\n";
    
        }

        return fila;
    }

    public void imprimirCuadro(){
        System.out.println(hacerCuadro(false, "", 0));
    }

    public void imprimirCuadroNum(){
        System.out.println(hacerCuadro(true, "", 3));
    }

    public void imprimirCuadroList(String caracter){
        System.out.println(hacerCuadro(false, caracter, (caracter.length() + 1)));
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

        int maximo = maxLinea;

        for (String fila : filas) {
            String [] saltos = fila.split("\n");
            int longFila = 0;
            for (String salto : saltos) {
                longFila = salto.length();
                if(contieneColor(salto))
                    longFila -= 7;
                maximo = Math.max(maximo, longFila);
            }
        }
        return maximo + lineaDinamico;
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

    public void setMaxLinea(int maximo){
        this.maxLinea = Math.max(this.maxLinea, maximo);
    }

    public void setLineaDinamico(int maxDinamico){
        if(maxDinamico > 0)
            this.lineaDinamico = maxDinamico;
    }

    public int getMaxLinea(){
        return this.maxLinea + this.lineaDinamico;
    }

    public void centrado(boolean opc){
        this.centrado = opc;
    }

    public void agregarTexto(String... texto){
        String[] temp = new String[this.texto.length + texto.length];
        
        System.arraycopy(this.texto, 0, temp, 0, this.texto.length);
        System.arraycopy(texto, 0, temp, this.texto.length, texto.length);

        this.texto = temp;
    }

}
