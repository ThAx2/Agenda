package ConexionBD;
import java.io.IOError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexionBD {
	 private static final String DB_URL = "jdbc:mariadb://localhost:3306/bd1";
	 private static final String DB_USER = "root";
	 private static final String DB_PASSWORD = "12346";
	 public static void main(String[] args) {
		 conectar();
		}
	 public static void conectar() {
		 Scanner read = new Scanner(System.in);
	        Connection conexion = null;
	        try {   
	            System.out.println("Intentando conectar a MariaDB...");
	            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	            System.out.println("¡Conexión exitosa a MariaDB!");
	        } catch (SQLException e) {
	            System.out.println("Error al conectar o ejecutar la consulta:");
	        } finally {
	            }
	        }
	    }
