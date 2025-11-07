package main.java.com.UAS.Agenda.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Label myLabel; // Puedes enlazar a cualquier Label en tu FXML

    @FXML
    private Button myButton; // Puedes enlazar al botón

    @FXML
    private TextField numeroCuentaField;

    @FXML
    private TextField pinField;

    @FXML
    private void onButtonClick() {
        myLabel.setText("¡Hola desde el Controller!");
        System.out.println("Número de cuenta: " + numeroCuentaField.getText());
        System.out.println("PIN: " + pinField.getText());
    }
}
