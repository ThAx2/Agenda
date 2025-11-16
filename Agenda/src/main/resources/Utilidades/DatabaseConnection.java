package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/MUCU"; // Cambia 'nombre_de_tu_base'
    private static final String USER = "root"; // Ej: 'root'
    private static final String PASSWORD = "NuevaClave"; // Ej: '' o '1234'
    
    // Driver de MySQL. Asegúrate de tener el JAR en tu proyecto (classpath).
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    /**
     * Establece y devuelve una conexión a la base de datos.
     * @return Objeto Connection, o null si la conexión falla.
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. Cargar el Driver
            Class.forName(DRIVER);
            
            // 2. Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado. Asegúrate de incluir el JAR.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos. Verifica URL, usuario y contraseña.");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Cierra la conexión si no es nula.
     * @param conn Objeto Connection a cerrar.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}