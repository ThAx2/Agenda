package Controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextFormatter; 
import javafx.stage.Stage;
import java.util.function.UnaryOperator;
import ConexionBD.ConexionBD;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class MainController {
    @FXML
    private Hyperlink LabelRegistrar;
    @FXML
    private Button myButton;
    @FXML
    private TextField numeroCuentaField;
    @FXML
    private PasswordField pinField;
    @FXML
    private Label LabelLoginFail;
    @FXML
    public void initialize() {
    		UnaryOperator<TextFormatter.Change> filterCuenta = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,8}")) { 
                return change;
            }
            return null;
        };
        
        	UnaryOperator<TextFormatter.Change> filterPin = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,6}")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> cuentaFormatter = new TextFormatter<>(filterCuenta);
        numeroCuentaField.setTextFormatter(cuentaFormatter);
        
        TextFormatter<String> pinFormatter = new TextFormatter<>(filterPin);
        pinField.setTextFormatter(pinFormatter);
        LabelRegistrar.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/registro.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Formulario de Registro");
                stage.show();
                ((Stage) LabelRegistrar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        String cuenta = numeroCuentaField.getText();
        String pin = pinField.getText();
    }
    @FXML
    private void onButtonClick() {
    	String cuenta = numeroCuentaField.getText(); // Captura el valor del campo Cuenta
        String pin = pinField.getText();             // Captura el valor del campo PIN
        System.out.println("Login Intentado - Cuenta: " + cuenta + ", PIN: " + pin);

        // 1. Llama al método estático para validar contra la Base de Datos
        boolean accesoPermitido = ConexionBD.validarLogin(cuenta, pin);
        if (accesoPermitido) {
            // 2. ACCESO CONCEDIDO: Carga el formulario Panel
            try {
                // Asegúrate de tener los imports de JavaFX (Parent, FXMLLoader, Stage, Scene)
                Parent root = FXMLLoader.load(getClass().getResource("/Panel.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Panel");
                stage.show();
                // Cierra la ventana de login
                ((Stage) LabelRegistrar.getScene().getWindow()).close();
                
            } catch (IOException e) {
                System.err.println("Error al cargar el panel de usuario.");
                e.printStackTrace();
            }
        } else {
            // 3. ACCESO DENEGADO: Muestra un mensaje de error en la interfaz
        	LabelLoginFail.setVisible(true);
            System.out.println("ACCESO DENEGADO: Cuenta o PIN incorrectos o problema de BD.");
            // Implementa aquí la lógica para mostrar una alerta o mensaje en tu GUI.
        }
    }
}
