package Presentacion;

public class App {
    public static void main(String[] args) {
    
        String [] columnas = {"12", "Columna200000000000000000000", "Columna3"};
        Object [] [] filas = {
            {"Ped", 1, true },
            {"Luis", 2, true },
            {"Maria", 5, false }
        };
    
        
        Tabla prueba = new Tabla(columnas, filas);
        prueba.imprimirTablaSimple(4);
        
    } 
}
