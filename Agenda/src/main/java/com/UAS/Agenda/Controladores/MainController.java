package Controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextFormatter; 
import javafx.stage.Stage;
import java.util.function.UnaryOperator; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
        String cuenta = numeroCuentaField.getText();
        String pin = pinField.getText();
        
        System.out.println("Login Intentado - Cuenta: " + cuenta + ", PIN: " + pin);
        if (cuenta.equals("123") && pin.equals("456")) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Panel.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel");
            stage.show();
            ((Stage) LabelRegistrar.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        	}
        }
    }
}
