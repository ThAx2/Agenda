package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextFormatter; // ¡Importante!
import java.util.function.UnaryOperator; // ¡Importante!
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HomeController {

    @FXML
    private Text Text_Fecha; // Puedes enlazar a cualquier Label en tu FXML

    
    @FXML
    public void initialize() {
     Text_Fecha.setText("Hola");
        
    }
    


}
