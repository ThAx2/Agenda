package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ConexionBD {    
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/bd1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";
    public static boolean validarLogin(String cuenta, String pin) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean accesoPermitido = false; // Valor por defecto: denegado

        try {
            System.out.println("Intentando conectar a MariaDB para validar...");
            // 1. Establecer la Conexión
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexión a BD establecida.");
            // 2. Definir la Consulta SQL (Asumiendo una tabla 'usuarios')
             String sql = "SELECT * FROM Tabla WHERE CuentaUAS = ? AND PIN = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cuenta); 
            ps.setString(2, pin);    
            // 4. Ejecutar la Consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                accesoPermitido = true;
                System.out.println("Validación exitosa.");
            } else {
                System.out.println("Credenciales incorrectas.");
            }

        } catch (SQLException e) {
            System.err.println("Error crítico de Base de Datos (URL, driver o servicio):");
            e.printStackTrace();
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos.");
                e.printStackTrace();
            }
        }
        return accesoPermitido;
    }
    public static boolean insertarRegistro(
            String nombre, String apellido1, String apellido2, String correo, 
            String numUAS, String pin, String numTelefono, String semestre, String carrera) {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        boolean exito = false;
        try {
            System.out.println("Intentando conectar a MariaDB para insertar registro...");
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexión a BD establecida.");
            // 1. Definir la Consulta SQL de Inserción
            String sql = "INSERT INTO Tabla (`Apellido 1`, `Apellido 2`, nombres, Correo, CuentaUAS, PIN, numTelefono, Semestre, Carrera) "
                       + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            
            // 3. Asignar los valores a los parámetros (?)
            // El orden aquí DEBE coincidir con el orden de las columnas en la consulta SQL.
            ps.setString(1, apellido1);
            ps.setString(2, apellido2);
            ps.setString(3, nombre);
            ps.setString(4, correo);
            ps.setString(5, numUAS);
            ps.setString(6, pin);
            ps.setString(7, numTelefono);
            ps.setString(8, semestre);
            ps.setString(9, carrera);
            
            // 4. Ejecutar la Inserción
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Registro insertado exitosamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar registro en la Base de Datos:");
            e.printStackTrace();
            
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos.");
                e.printStackTrace();
            }
        }
        return exito;
    }
}