package Controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Label myLabel;
    @FXML
    private Button myButton;
    @FXML
    private TextField numeroCuentaField;
    @FXML
    private TextField pinField;

    @FXML
    private void onButtonClick() {
        String cuenta = numeroCuentaField.getText();
        String pin = pinField.getText();

        myLabel.setText("Verificando credenciales...");

        // Simulación de autenticación
        if (cuenta.equals("123") && pin.equals("456")) {
            myLabel.setText("Acceso concedido. Cargando panel...");
            cambiarEscena("/Panel.fxml", "Panel Principal");
        } else {
            myLabel.setText("Credenciales incorrectas.");
        }
    }

    private void cambiarEscena(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Obtener la ventana actual
            Stage stage = (Stage) myButton.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.sizeToScene(); // Ajusta al tamaño del nuevo FXML
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar FXML: " + fxmlPath);
            e.printStackTrace();
            myLabel.setText("Error al cargar la vista: " + e.getMessage());
        }
    }
}
