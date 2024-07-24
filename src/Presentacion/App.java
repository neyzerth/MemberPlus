package Presentacion;

public class App {
    public static void main(String[] args) {
    
        //Tabla vacia, solo con cabeceras
        Tabla prueba = new Tabla("Nombre", "Edad", "Altura");

        //Insertar datos
        prueba.agregarFila("Luis", 17, 1.76);
        prueba.imprimirTabla();
        prueba.agregarFila("Pablo", 66, 1.26);
        prueba.imprimirTablaSimple(2);
        
        //Arreglo de las cabeceras
        String [] titulos = {"ID", "Nombre", "Numero"};

        //arreglos de datos
        Object [][] datos = {
            {1, "Pepa", 664}, 
            {2, "Spiterman", 69}, 
            {3, "vegeta", 777},
            {4, "polosex", 69696969} 
        };

        //Tabla con tipo de linea, cabeceras y datos
        Tabla prueba2 = new Tabla(2, titulos, datos);
        prueba2.imprimirTablaSimple();

        //agregar datos
        prueba2.agregarFila(5, "Pablo", 66);
        prueba2.imprimirTablaSimple();

    } 
}
