package Presentacion.Despliegue;


class Prueba {
    public static void main(String[] args) {

        Cuadro prueba = new Cuadro(
            "Módulo de Ventas",
            "Módulo de Clientes",
            "Módulo de Tarjeta",
            "Salir"
        );
        prueba.imprimirCuadro();
        prueba.imprimirCuadroNum();
        prueba.imprimirCuadroList("-");
    }

}

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
        this.maxLinea = textoMayor(texto); // Llama a textoMayor después de inicializar texto
        setTipoLineas(this.tipoLinea);
    }

    public Cuadro(String... texto){
        this(1, texto);
    }

    public void imprimirCuadro(){
        String linea = linea(maxLinea);
        System.out.println(borIzqSup + linea + borDerSup );
        for (String txt : texto) {
            System.out.println(colum + txt + espacio(maxLinea - txt.length()) + colum );
        }
        System.out.println(borIzqInf + linea + borDerInf );
    }

    public void imprimirCuadroNum(){
        String linea = linea(maxLinea + 3);
        System.out.println(borIzqSup + linea + borDerSup );
        for (int i = 0; i < texto.length; i++) {
            System.out.println(colum + (i+1) + ". " + texto[i] + espacio(maxLinea - texto[i].length()) + colum );
        }
        System.out.println(borIzqInf + linea + borDerInf );
    }

    public void imprimirCuadroList(String caracter){
        String linea = linea(maxLinea + caracter.length() + 1);
        System.out.println(borIzqSup + linea + borDerSup );
        for (int i = 0; i < texto.length; i++) {
            System.out.println(colum + caracter + " " + texto[i] + espacio(maxLinea - texto[i].length()) + colum );
        }
        System.out.println(borIzqInf + linea + borDerInf );
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

    // iterando linea plana
    public static String espacio(int n) {
        String espacios = "";
        for (int i = 0; i < n; i++) {
            espacios += " ";
        }
        return espacios;
    }

    // calcular la longitud de cadena mayor en cada columna
    private int textoMayor(String[] filas) {
        if(filas.length == 0)
            return 0;

        int maximo = filas[0].length();

        for (String fila : filas) {
            maximo = Math.max(maximo, fila.length());
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

}
