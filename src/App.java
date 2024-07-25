import Logica.Persona;
import Logica.Usuario;
public class App {
    public static void main(String[] args) throws Exception {
        Usuario u = new Usuario();
        u.setFecNac("2005-01-30");
        //prueba de la impresion de datos
        u.imprimirEdad();
    }
}
