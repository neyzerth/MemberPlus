package Logica;

import Persistencia.Conexion;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Persona {
    // ATRIBUTOS
    private String nombre, apellidoMa, apellidoPa, colonia, calle, telefono, correo, cp;
    private int numExt, numInt;
    private Date fecNac;

    // CONSTRUCTORES
    public Persona() {}

    public Persona(String nombre, String apellidoMa, String apellidoPa, String colonia, String calle, int numExt,
                   int numInt, String cp, String telefono, String correo, Date fecNac) {
        this.nombre = nombre;
        this.apellidoMa = apellidoMa;
        this.apellidoPa = apellidoPa;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.numInt = numInt;
        this.cp = cp;
        this.telefono = telefono;
        this.correo = correo;
        this.fecNac = fecNac;
    }

    // GETTERS AND SETTERS
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMa() {
        return this.apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa;
    }

    public String getApellidoPa() {
        return this.apellidoPa;
    }

    public void setApellidoPa(String apellidoPa) {
        this.apellidoPa = apellidoPa;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumExt() {
        return this.numExt;
    }

    public void setNumExt(int numExt) {
        this.numExt = numExt;
    }

    public int getNumInt() {
        return this.numInt;
    }

    public void setNumInt(int numInt) {
        this.numInt = numInt;
    }

    public String getCp() {
        return this.cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecNac() {
        return this.fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    // Método para modificar y verificar los datos
    public void modificar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Ingrese el nuevo apellido materno: ");
        String nuevoApellidoMa = scanner.nextLine();

        System.out.print("Ingrese el nuevo apellido paterno: ");
        String nuevoApellidoPa = scanner.nextLine();

        System.out.print("Ingrese la nueva colonia: ");
        String nuevaColonia = scanner.nextLine();

        System.out.print("Ingrese la nueva calle: ");
        String nuevaCalle = scanner.nextLine();

        System.out.print("Ingrese el nuevo número exterior: ");
        String nuevoNumExtStr = scanner.nextLine();

        System.out.print("Ingrese el nuevo número interior: ");
        String nuevoNumIntStr = scanner.nextLine();

        System.out.print("Ingrese el nuevo código postal: ");
        String nuevoCp = scanner.nextLine();

        System.out.print("Ingrese el nuevo teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        System.out.print("Ingrese el nuevo correo: ");
        String nuevoCorreo = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de nacimiento (yyyy-MM-dd): ");
        String nuevaFecNacStr = scanner.nextLine();

        int nuevoNumExt = 0;
        int nuevoNumInt = 0;
        Date nuevaFecNac = null;

        try {
            nuevoNumExt = Integer.parseInt(nuevoNumExtStr);
        } catch (NumberFormatException e) {
            System.out.println("Número exterior no válido");
            return;
        }

        try {
            nuevoNumInt = Integer.parseInt(nuevoNumIntStr);
        } catch (NumberFormatException e) {
            System.out.println("Número interior no válido");
            return;
        }

        try {
            nuevaFecNac = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFecNacStr);
        } catch (ParseException e) {
            System.out.println("Fecha de nacimiento no válida");
            return;
        }

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
        } else if (nuevoApellidoMa == null || nuevoApellidoMa.trim().isEmpty()) {
            System.out.println("El apellido materno no puede estar vacío");
        } else if (nuevoApellidoPa == null || nuevoApellidoPa.trim().isEmpty()) {
            System.out.println("El apellido paterno no puede estar vacío");
        } else if (nuevaColonia == null || nuevaColonia.trim().isEmpty()) {
            System.out.println("La colonia no puede estar vacía");
        } else if (nuevaCalle == null || nuevaCalle.trim().isEmpty()) {
            System.out.println("La calle no puede estar vacía");
        } else if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) {
            System.out.println("El teléfono no puede estar vacío");
        } else if (nuevoCorreo == null || nuevoCorreo.trim().isEmpty()) {
            System.out.println("El correo no puede estar vacío");
        } else if (nuevoNumExt <= 0) {
            System.out.println("El número exterior no puede estar vacío o ser negativo");
        } else if (nuevoNumInt < 0) {
            System.out.println("El número interior no puede ser negativo");
        } else if (nuevoCp == null || nuevoCp.trim().isEmpty()) {
            System.out.println("El código postal no puede estar vacío");
        } else if (nuevaFecNac == null) {
            System.out.println("La fecha de nacimiento no puede estar vacía");
        } else {
            this.setNombre(nuevoNombre);
            this.setApellidoMa(nuevoApellidoMa);
            this.setApellidoPa(nuevoApellidoPa);
            this.setColonia(nuevaColonia);
            this.setCalle(nuevaCalle);
            this.setNumExt(nuevoNumExt);
            this.setNumInt(nuevoNumInt);
            this.setCp(nuevoCp);
            this.setTelefono(nuevoTelefono);
            this.setCorreo(nuevoCorreo);
            this.setFecNac(nuevaFecNac);
            System.out.println("Datos modificados correctamente");
        }
    }

    // Método para imprimir una tabla con los datos de los usuarios
    private static void imprimirTabla(ResultSet resultSet) throws SQLException {
        System.out.println(String.format("%-5s %-20s %-20s %-20s %-30s %-30s %-10s %-10s %-10s %-15s %-40s %-15s",
                "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Colonia", "Calle", "Num Ext", "Num Int", "CP", "Teléfono", "Correo", "Fecha Nac"));

        while (resultSet.next()) {
            System.out.println(String.format("%-5d %-20s %-20s %-20s %-30s %-30s %-10d %-10d %-10s %-15s %-40s %-15s",
                    resultSet.getInt("idPersona"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellidoPa"),
                    resultSet.getString("apellidoMa"),
                    resultSet.getString("colonia"),
                    resultSet.getString("calle"),
                    resultSet.getInt("numExt"),
                    resultSet.getInt("numInt"),
                    resultSet.getString("cp"),
                    resultSet.getString("telefono"),
                    resultSet.getString("correo"),
                    resultSet.getDate("fecNac").toString()));
        }
    }

    // Método para listar usuarios en formato tabular
    public static void listarUsuarios() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.conectar();

        if (connection != null) {
            String sql = "SELECT p.idPersona, p.nombre, p.apellidoPa, p.apellidoMa, p.colonia, p.calle, p.numExt, p.numInt, p.cp, p.telefono, p.correo, p.fecNac " +
                         "FROM persona p " +
                         "JOIN usuario u ON p.idPersona = u.persona";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                imprimirTabla(resultSet);
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Método para obtener un usuario por ID
    private static Persona obtenerPersonaPorId(int idPersona) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.conectar();

        if (connection != null) {
            String sql = "SELECT nombre, apellidoPa, apellidoMa, colonia, calle, numExt, numInt, cp, telefono, correo, fecNac " +
                         "FROM persona WHERE idPersona = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idPersona);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellidoPa = resultSet.getString("apellidoPa");
                    String apellidoMa = resultSet.getString("apellidoMa");
                    String colonia = resultSet.getString("colonia");
                    String calle = resultSet.getString("calle");
                    int numExt = resultSet.getInt("numExt");
                    int numInt = resultSet.getInt("numInt");
                    String cp = resultSet.getString("cp");
                    String telefono = resultSet.getString("telefono");
                    String correo = resultSet.getString("correo");
                    Date fecNac = resultSet.getDate("fecNac");

                    Persona persona = new Persona(nombre, apellidoMa, apellidoPa, colonia, calle, numExt, numInt, cp, telefono, correo, fecNac);
                    connection.close();
                    return persona;
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Método para actualizar un usuario en la base de datos
    private static void actualizarPersonaEnBaseDeDatos(Persona usuario, int idPersona) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.conectar();

        if (connection != null) {
            String sql = "UPDATE persona SET nombre = ?, apellidoPa = ?, apellidoMa = ?, colonia = ?, calle = ?, numExt = ?, numInt = ?, cp = ?, telefono = ?, correo = ?, fecNac = ? WHERE idPersona = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, usuario.getNombre());
                preparedStatement.setString(2, usuario.getApellidoPa());
                preparedStatement.setString(3, usuario.getApellidoMa());
                preparedStatement.setString(4, usuario.getColonia());
                preparedStatement.setString(5, usuario.getCalle());
                preparedStatement.setInt(6, usuario.getNumExt());
                preparedStatement.setInt(7, usuario.getNumInt());
                preparedStatement.setString(8, usuario.getCp());
                preparedStatement.setString(9, usuario.getTelefono());
                preparedStatement.setString(10, usuario.getCorreo());
                preparedStatement.setDate(11, new java.sql.Date(usuario.getFecNac().getTime()));
                preparedStatement.setInt(12, idPersona);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Usuario actualizado exitosamente");
                } else {
                    System.out.println("Error al actualizar el usuario");
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Método para modificar un usuario
    public static void modificarUsuario() {
        Scanner scanner = new Scanner(System.in);

        listarUsuarios();

        System.out.print("Ingrese el ID del usuario que desea modificar: ");
        int idPersona = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Persona persona = obtenerPersonaPorId(idPersona);

        if (persona != null) {
            persona.modificar();
            actualizarPersonaEnBaseDeDatos(persona, idPersona);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

}
