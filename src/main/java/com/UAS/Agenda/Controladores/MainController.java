package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class MainController {

    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    @FXML
    private TextField numeroCuentaField;

    @FXML
    private PasswordField pinField;
    
    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,8}")) {
                return change;
            }
            return null;
        };
        UnaryOperator<TextFormatter.Change> filter2 = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,6}")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> cuentaFormatter = new TextFormatter<>(filter);
        numeroCuentaField.setTextFormatter(cuentaFormatter);
        TextFormatter<String> pinFormatter = new TextFormatter<>(filter2);
        pinField.setTextFormatter(pinFormatter);
    }

    @FXML
    private void onButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panel.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nueva Ventana");
            stage.setScene(new Scene(root));
            stage.show();

            String cuenta = numeroCuentaField.getText();        
            String pin = pinField.getText();
            System.out.println(cuenta + " " + pin);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
