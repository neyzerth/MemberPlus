package Presentacion.Despliegue;

import java.util.ArrayList;

import Presentacion.Formato.Texto;

public class Tabla extends Cuadro {

    private String[] cabeceras; // Titulos de las columnas
    private Object[][] datos; // contenido de cada cuadro
    private int[] maxColumna; // longitud de texto maxima por columna

    // tipos de linea para las tablas
    private String cruz, norT, invT, derT, IzqT;

    // --------- CONSTRUCTORES -------
    // CONSTRUCTOR CON TIPO DE LINEA Y CABECERAS
    public Tabla(int tipoLinea, String... cabeceras) {
        this.cabeceras = cabeceras;
        this.maxColumna = new int[getCantColumnas()];
        this.tipoLinea = tipoLinea;
        setTipoLineas(tipoLinea);
    }

    // CONSTRUCTOR SOLO CABECERAS
    public Tabla(String... cabeceras) {
        this(1, cabeceras);
    }

    // CONSTRUCTOR CON TIPO LINEA, CABECERAS Y DATOS
    public Tabla(int tipoLinea, String[] cabeceras, Object[]... datos) {
        this(tipoLinea, cabeceras);
        if (cabeceras.length != datos[0].length)
            throw new IllegalArgumentException("Cabecera y Datos[] deben tener la misma longitud");

        this.datos = datos;
        this.maxColumna = longMayor(cabeceras, datos);
    }

    // CONSTRUCTOR CON CABECERAS Y DATOS
    public Tabla(String[] cabeceras, Object[]... datos) {
        this(1, cabeceras, datos);
    }

    // ---------- IMPRIMIR TABLAS ----------
    // FUNCION PARA IMPRIMIR TABLA A PARTIR DE UN TIPO
    public void imprimirTabla(int tipo) {
        try {
            setTipoLineas(tipo);
            String tabla = linCabecera() + "\n";
            tabla += cabecera() + "\n";
            tabla += filas() + "\n";
            tabla += linFinal() + "\n";
            System.out.println(tabla);
        } catch (NullPointerException e) {
            System.out.println("Tabla sin datos.");
        }
    }

    // IMPRIMIR TABLA CON EL TIPO PREDETERMINADO
    public void imprimirTabla() {
        imprimirTabla(this.tipoLinea);
    }

    // TABLA SIN INTERLINES EN DATOS
    public void imprimirTablaSimple(int tipo) {

        try {
            setTipoLineas(tipo);
            System.out.println(linCabecera());
            System.out.println(cabecera());
            System.out.println(filasSinInterlineas());
            System.out.println(linFinal());
        } catch (NullPointerException e) {
            System.out.println("Tabla sin datos.");
        }
    }

    public void imprimirTablaSimple() {
        imprimirTablaSimple(this.tipoLinea);
    }

    // ---------- CADENA DE LINEAS ------------
    // CAMBIAR SIMBOLO DE CADA TIPO DE LINEA
    protected void setTipoLineas(int tipo) {
        super.setTipoLineas(tipo);
        switch (tipo) {
            default:
            case 1:
                setLineasT("┬", "┴", "├", "┤", "┼");
                break;

            case 2:
                setLineasT("╦", "╩", "╠", "╣", "╬");
                break;

            case 3:
                setLineasT("+", "+", "+", "+", "+");
                break;

            case 4:
                setLineasT("╤", "╧", "╟", "╢", "╪");
                break;
        }
    }

    // CLASIFICAR TIPOS DE LINEAS PARA CAMBIAR SIMBOLO
    private void setLineasT(String normal, String invertida, String derecha, String izquierda, String cruz) {
        this.norT = techo + normal + techo;
        this.invT = techo + invertida + techo;
        this.derT = " " + derecha + techo;
        this.IzqT = techo + izquierda;
        this.cruz = techo + cruz + techo;
    }

    // ESTRUCTURA DE ESCRIBIR DATOS EN UNA FILA
    private String celdas(Object[] celdas) {
        String fila = colum;

        for (int i = 0; i < getCantColumnas(); i++) {
            String dato = String.valueOf(celdas[i]);
            int longDato = dato.length();
            if (contieneColor(dato))
                longDato -= 9;

            fila += dato;
            fila += Texto.espacio(maxColumna[i] - longDato);
            fila += colum;
        }
        return fila;
    }

    // FILA CON LA CABECERA
    private String cabecera() {
        return celdas(getCabeceras());
    }

    // FILA CON LOS DATOS
    private String filas() {
        String filas = "";
        int i = 0;

        for (Object[] fila : getDatos()) {
            filas += linFilas();
            filas += "\n";
            filas += celdas(fila);
            i++;
            if (i < getDatos().length)
                filas += "\n";
        }
        return filas;
    }

    // FILA CON LOS DATOS SIN INTERLINEAS
    private String filasSinInterlineas() {
        String filas = linFilas() + "\n";
        int i = 0;

        for (Object[] fila : getDatos()) {
            filas += celdas(fila);
            i++;
            if (i < getDatos().length)
                filas += "\n";
        }
        return filas;
    }

    // LINEAS PLANAS CON BORDE DERECHO E IZQUIERDO
    private String linTabla(String lin1, String lin2, String lin3) {
        String linTabla = lin1;
        int cantColumnas = getCantColumnas();

        for (int i = 0; i < cantColumnas; i++) {
            linTabla += linea(maxColumna[i]);

            if (i < cantColumnas - 1) {
                linTabla += lin2;
            }
        }
        linTabla += lin3;
        return linTabla;
    }

    private int[] longMayor(String[] columnas, Object[][] datos) {
        int[] maximos = new int[columnas.length];

        for (int i = 0; i < maximos.length; i++) {
            maximos[i] = columnas[i].length();
        }

        for (Object[] fila : datos) {
            for (int i = 0; i < maximos.length; i++) {
                String txt = String.valueOf(fila[i]);
                maximos[i] = Math.max(maximos[i], txt.length());
            }
        }
        return maximos;
    }

    // LINEAS ARRIBA DE LAS COLUMNAS
    private String linCabecera() {
        return linTabla(borIzqSup, norT, borDerSup);
    }

    // LINEAS INTERMEDIAS
    private String linFilas() {
        return linTabla(derT, cruz, IzqT);
    }

    // ULTIMA LINEA
    private String linFinal() {
        return linTabla(borIzqInf, invT, borDerInf);
    }

    // SETTERS Y GETTERS
    public String[] getCabeceras() {
        return this.cabeceras;
    }

    public String getCabecera(int i) {
        return this.cabeceras[i];
    }

    public int getCantColumnas() {
        return this.cabeceras.length;
    }

    public void setCabeceras(String[] cabeceras) {
        if (cabeceras.length != datos[0].length)
            throw new IllegalArgumentException("Cabecera y Datos[] deben tener la misma longitud");

        this.cabeceras = cabeceras;
        setMaxColumna();
    }

    public void setCabecera(int i, String cabecera) {
        this.cabeceras[i] = cabecera;
        setMaxColumna(i, cabecera);
    }

    // ---------DATOS ---------
    // GETTERS DATOS
    public Object[][] getDatos() {
        return this.datos;
    }

    public Object getDato(int fila, int columna) {
        return this.datos[fila][columna];
    }

    public Object[] getFilaDatos(int fila) {
        return this.datos[fila];
    }

    public Object[] getColumnaDatos(int columna) {
        ArrayList<Object> columnaDatos = new ArrayList<>();
        for (int i = 0; i < getDatos().length; i++) {
            columnaDatos.add(this.datos[i][columna]);
        }
        return columnaDatos.toArray();
    }

    // SETTERS DATOS
    public void setDatos(Object[][] datos) {
        if (cabeceras.length != datos[0].length)
            throw new IllegalArgumentException("Cabecera y Datos[] deben tener la misma longitud");

        this.datos = datos;
        setMaxColumna();
    }

    public void setDato(int fila, int columna, Object dato) {
        this.datos[fila][columna] = dato;
        setMaxColumna(columna, String.valueOf(dato));
    }

    public void setFilaDatos(int fila, Object[] datos) {
        this.datos[fila] = datos;
    }

    public void agregarFila(Object... nuevaFila) {
        if (nuevaFila.length != getCantColumnas()) {
            throw new IllegalArgumentException("La nueva fila debe tener la misma cantidad de columnas que la tabla");
        }

        // Si es que no se inicializo
        if (this.datos == null) {
            this.datos = new Object[1][getCantColumnas()];
            this.datos[0] = nuevaFila;
            setMaxColumna();
            return;
        }

        Object[][] nuevosDatos = new Object[this.datos.length + 1][];
        System.arraycopy(this.datos, 0, nuevosDatos, 0, this.datos.length);
        nuevosDatos[this.datos.length] = nuevaFila;

        this.datos = nuevosDatos;
        this.maxColumna = longMayor(this.cabeceras, this.datos);
    }

    // ------ OTROS -----
    public void setMaxColumna() {
        this.maxColumna = longMayor(cabeceras, datos);
    }

    public void setMaxColumna(int i, String txt) {
        if (txt.length() > maxColumna[i])
            this.maxColumna[i] = txt.length();
    }

    public void setTipoLinea(int tipoLinea) {
        this.tipoLinea = tipoLinea;
    }
}
