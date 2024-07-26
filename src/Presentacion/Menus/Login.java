package Presentacion.Menus;

import Persistencia.Conexion;
import Presentacion.Despliegue.Cuadro;
import Presentacion.Formato.Color;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    public static void interfaz() {
        String usuario;
        String contrasena;
        
        do {
            Scanner scanner = new Scanner(System.in);
            Console consola = System.console();
            verificarConsola(consola);

            Cuadro inicioS = new Cuadro(Color.morado(" > Iniciar sesión <"));
            inicioS.imprimirCuadro();
            System.out.println();

            // Solicitar nombre de usuario
            System.out.print(Color.cian(" > Usuario: "));
            usuario = consola.readLine();

            // Solicitar contraseña de forma segura
            char[] contrasenaArreglo = consola.readPassword(Color.cian(" > Contraseña: "));

            // Convertir el array de caracteres a String
            contrasena = new String(contrasenaArreglo);

            Color.limpiarPantalla();
            // Verificación de credenciales
            if(!validarDatos(usuario, contrasena)){
                System.out.println(Color.rojo(Color.negrita(" ¡Datos incorrectos! ")));
                System.out.println(Color.rojo(" Intentelo de nuevo "));
            }

        } while (!validarDatos(usuario, contrasena));

        Menu.main(new String[] {});
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
        }

        return esValido;
    }

    public static void verificarConsola(Console consola) {
        // Verificar si console es null
        if (consola == null) {
            System.err.println("No se puede obtener la consola. Ejecute este programa en una consola o terminal.");
            System.exit(1);
        }
    }
}