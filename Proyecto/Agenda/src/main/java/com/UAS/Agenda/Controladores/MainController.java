package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextFormatter; // ¡Importante!
import java.util.function.UnaryOperator; // ¡Importante!
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Label myLabel; // Puedes enlazar a cualquier Label en tu FXML

    @FXML
    private Button myButton; // Puedes enlazar al botón

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
            if (newText.matches("\\d{0,4}")) {
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
    	String cuenta= numeroCuentaField.getText();        
       String pin=pinField.getText();
       System.out.println(cuenta+" "+pin);
    }
}
