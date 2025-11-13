package Controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PanelController {

    @FXML
    private Button BTN_LOGOUT;

    @FXML
    private void onLogoutClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) BTN_LOGOUT.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al volver al login:");
            e.printStackTrace();
        }
    }
}
