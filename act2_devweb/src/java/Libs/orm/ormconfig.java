package Libs.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ormconfig {

    private static final String URL = "jdbc:mysql://localhost:3306/act2_devweb";
    private static final String USER = "ramiro_espana";
    private static final String PASSWORD = "AbcdeUdeC";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MySQL: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Cierra la conexión si está abierta
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}