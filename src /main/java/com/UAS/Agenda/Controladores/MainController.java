package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    @FXML
    private void onButtonClick() {
        myLabel.setText("¡Hola desde el Controller!");
    }
}
