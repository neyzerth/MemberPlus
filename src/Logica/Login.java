package Logica;

import Persistencia.Conexion;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public static void main(String[] args) {
        Console console = System.console();

        // Colores ANSI
        String reset = "\u001B[0m";
        String white = "\u001B[37m"; // Blanco
        String blue = "\u001B[34m"; // Azul
        String green = "\u001B[32m"; // Verde
        String red = "\u001B[31m"; // Rojo

        System.out.println(white + "╔══════════════════════════════════════╗" + reset);
        System.out.println(white + "║ " + blue + "     Sistema de Membresías           " + white + "║" + reset);
        System.out.println(white + "║ " + blue + "               Member+               " + white + "║" + reset);
        System.out.println(white + "╚──────────────────────────────────────╝" + reset);

        // Solicitar nombre de usuario
        System.out.print(white + "║ Usuario: " + reset);
        String username = console.readLine();

        // Solicitar contraseña de forma segura
        char[] passwordArray = console.readPassword(white + "║ Contraseña: " + reset);

        // Convertir el array de caracteres a String
        String password = new String(passwordArray);

        // Verificación de credenciales
        if (isValidCredentials(username, password)) {
            System.out.println(white + "╔──────────────────────────────────────╗" + reset);
            System.out.println(white + "║ " + green + "¡Inicio de sesión exitoso!           " + white + "║" + reset);
            System.out.println(white + "║ " + green + "Acceso a contenido exclusivo:        " + white + "║" + reset);
            System.out.println(white + "║ " + green + "- Artículos premium                  " + white + "║" + reset);
            System.out.println(white + "║ " + green + "- Descuentos especiales              " + white + "║" + reset);
            System.out.println(white + "╚══════════════════════════════════════╝" + reset);
        } else {
            System.out.println(white + "╔──────────────────────────────────────╗" + reset);
            System.out.println(white + "║ " + red + "Credenciales incorrectas. Inténtalo  " + white + "║" + reset);
            System.out.println(white + "║ " + red + "de nuevo o regístrate para obtener   " + white + "║" + reset);
            System.out.println(white + "║ " + red + "una membresía.                       " + white + "║" + reset);
            System.out.println(white + "╚══════════════════════════════════════╝" + reset);
        }
    }

    private static boolean isValidCredentials(String username, String password) {
        boolean isValid = false;
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasena = ?";

        Conexion conexion = new Conexion();
        conexion.setLocal();

        try (Connection connection = conexion.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValid = resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}