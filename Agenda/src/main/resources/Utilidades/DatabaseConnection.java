package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Ajusta la URL, usuario y contraseña a tu base de datos MUCU
    private static final String URL = "jdbc:mariadb://localhost:3306/MUCU";
    private static final String USER = "root";
    private static final String PASSWORD = "NuevaClave";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // ✅ PASO 1: Usar el driver correcto para MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MariaDB."); // Mensaje de éxito
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado. Asegúrate de incluir el JAR.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }
    
    // Método para cerrar la conexión
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}