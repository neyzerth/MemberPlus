package Presentacion;

import Persistencia.Conexion;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.Color;
import Presentacion.Menus.Menu;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Login {

    public static void interfaz() {
        Scanner scanner = new Scanner(System.in);
        Console consola = System.console();
        verificarConsola(consola);

        Cuadro inicioS = new Cuadro(Color.morado(" > Iniciar sesión <"));
        inicioS.imprimirCuadro();
        System.out.println();

        // Solicitar nombre de usuario
        System.out.print(Color.cian(" > Usuario: "));
        String usuario = consola.readLine();

        // Solicitar contraseña de forma segura
        char[] contrasenaArreglo = consola.readPassword(Color.cian(" > Contraseña: "));

        // Convertir el array de caracteres a String
        String contrasena = new String(contrasenaArreglo);

        Color.limpiarPantalla();
        // Verificación de credenciales
        if (validarDatos(usuario, contrasena)) {
            Cuadro exitoso = new Cuadro(
                Color.verde("¡Inicio de sesión exitoso!"),
                Color.verde("Acceso a contenido exclusivo:"),
                Color.verde("- Artículos premium"),
                Color.verde("- Descuentos especiales")
            );
            exitoso.imprimirCuadro()
            ;
            System.out.println(Color.morado(" > Presione para continuar"));
            scanner.nextLine();
            Color.limpiarPantalla();

            Menu.main(new String[]{});
        } else {
            Cuadro incorrecto = new Cuadro(
                Color.rojo("Contraseña o Usuario incorrecto. Inténtalo"),
                Color.rojo("de nuevo o regístrate para obtener"),
                Color.rojo("una membresía.")
            );
            incorrecto.imprimirCuadro();
        }
    }

    private static boolean validarDatos(String usuario, String contrasena) {
        boolean esValido = false;
        Conexion conexion = new Conexion();
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasena = ?";

        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contrasena);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                esValido = resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return esValido;
    }

    public static void verificarConsola(Console consola){
        // Verificar si console es null
        if (consola == null) {
            System.err.println("No se puede obtener la consola. Ejecute este programa en una consola o terminal.");
            System.exit(1);
        }
    }
}