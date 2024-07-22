package Presentacion;

import Persistencia.Conexion;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static void interfaz() {
        Console consola = System.console();
        verificarConsola(consola);

        System.out.println(Color.blanco("╔══════════════════════════════════════╗"));
        System.out.println(Color.blanco("║ ") +Color.azul("     Sistema de Membresías           ") + Color.blanco("║"));
        System.out.println(Color.blanco("║ ") + Color.azul("               Member+               ") + Color.blanco ("║"));
        System.out.println(Color.blanco("╚──────────────────────────────────────╝"));

        // Solicitar nombre de usuario
        System.out.print(Color.blanco("Usuario: "));
        String usuario = consola.readLine();

        // Solicitar contraseña de forma segura
        char[] passwordArray = consola.readPassword(Color.blanco("Contraseña: "));

        // Convertir el array de caracteres a String
        String password = new String(passwordArray);

        // Verificación de credenciales
        if (isValidCredentials(usuario, password)) {
            System.out.println(Color.blanco("╔──────────────────────────────────────╗"));
            System.out.println(Color.blanco("║ ") + Color.verde("¡Inicio de sesión exitoso!           ") + Color.blanco("║"));
            System.out.println(Color.blanco("║ ") + Color.verde("Acceso a contenido exclusivo:        ") + Color.blanco("║"));
            System.out.println(Color.blanco("║ ") + Color.verde("- Artículos premium                  ") + Color.blanco("║"));
            System.out.println(Color.blanco("║ ") + Color.verde("- Descuentos especiales              ") + Color.blanco("║"));
            System.out.println(Color.blanco("╚══════════════════════════════════════╝"));
        } else {
            System.out.println(Color.blanco("║ ") + Color.rojo("Credenciales incorrectas. Inténtalo  ") + Color.blanco("║"));
            System.out.println(Color.blanco("║ ") + Color.rojo("de nuevo o regístrate para obtener   ") + Color.blanco("║"));
            System.out.println(Color.blanco("║ ") + Color.rojo("una membresía.                       ") + Color.blanco("║"));
            System.out.println(Color.blanco("╚══════════════════════════════════════╝"));
        }
    }

    private static boolean isValidCredentials(String username, String password) {
        boolean isValid = false;
        Conexion conexion = new Conexion();
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasena = ?";

        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValid = resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

    public static void verificarConsola(Console consola){
        // Verificar si console es null
        if (consola == null) {
            System.err.println("No se puede obtener la consola. Ejecute este programa en una consola o terminal.");
            System.exit(1);
        }
    }
}