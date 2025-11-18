package Controladores;

import Modelo.Usuario;
import Utilidades.DatabaseConnection; // Asegúrate que esta clase funciona
import java.io.IOException;
import java.net.URL; 
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField; 
import javafx.stage.Stage;

public class MainController { 

    @FXML private Label myLabel;
    @FXML private Button myButton;
    @FXML private TextField matriculaField; 
    @FXML private PasswordField pinField;   
    @FXML
    private void onButtonClick() {
        String matricula = matriculaField.getText();
        String contrasena = pinField.getText();

        myLabel.setText("Verificando credenciales...");
        Usuario usuarioAutenticado = autenticarUsuario(matricula, contrasena);
        if (usuarioAutenticado != null) {
            myLabel.setText("Acceso concedido. Bienvenido, " + usuarioAutenticado.getNombreCompleto() + ".");
            cambiarEscena("/Panel.fxml", "Panel Principal del Estudiante", usuarioAutenticado);
        } else {
            myLabel.setText("Matrícula o Contraseña incorrectos.");
        }
    }

    private Usuario autenticarUsuario(String matricula, String contrasena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuarioResult = null;
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDO_1, APELLIDO_2, CORREO " +
                     "FROM USUARIO WHERE ID_MATRICULA = ? AND PIN = ?"; 
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Fallo al obtener la conexión.");
                return null;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, matricula);
            ps.setString(2, contrasena); 
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("ID_USUARIO");
                String nombre = rs.getString("NOMBRE");
                String apellidoP = rs.getString("APELLIDO_1"); 
                String apellidoM = rs.getString("APELLIDO_2"); 
                String correo = rs.getString("CORREO");
                double promedioGeneral = 0.0; // Se cargará en el HomeController
                usuarioResult = new Usuario(id, nombre, apellidoP, apellidoM, matricula, correo, promedioGeneral);
            }
            
        } catch (SQLException e) {
            System.err.println("Error de BD durante la autenticación: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorar */ }
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignorar */ }
            DatabaseConnection.closeConnection(conn); 
        }
        return usuarioResult;
    }
    private void cambiarEscena(String fxmlPath, String title, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            PanelController panelController = loader.getController();
            
            if (panelController != null) {
                panelController.setUsuario(usuario); 
            }

            Stage stage = (Stage) myButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("Error de IO al cargar FXML: " + fxmlPath);
            e.printStackTrace();
            myLabel.setText("Error al cargar la vista: " + e.getMessage());
        }
    }
}