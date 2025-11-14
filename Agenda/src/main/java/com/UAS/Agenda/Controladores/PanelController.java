package Controladores;

import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PanelController {

    private final String COLOR_ACTIVO = "-fx-background-color: #4c5363; -fx-background-radius: 50px;";
    private final String COLOR_BASE = "-fx-background-color: #2B2F38;";
    private final double ZOOM_SCALE = 1.15; 
    @FXML private Button BTN_LOGOUT;
    @FXML private Button BTN_HOME; 
    @FXML private Button BTN_Tareas;      
    @FXML private Button BTN_Materias;    
    @FXML private Button BTN_Profesores; 
    @FXML private Button BTN_Calendario; 
    @FXML private Button BTN_Solicitudes; 
    @FXML private Button BTN_Tramites; 
    @FXML private Button BTN_Ajustes; 
    
    private Button botonActivo;

    private void addZoomEffect(Button button) {
        final Duration DURATION = Duration.millis(150);
        ScaleTransition scaleUp = new ScaleTransition(DURATION, button);
        scaleUp.setToX(ZOOM_SCALE);
        scaleUp.setToY(ZOOM_SCALE);
        ScaleTransition scaleDown = new ScaleTransition(DURATION, button);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);
        button.setOnMouseEntered(e -> {
            scaleUp.play();
            if (button != botonActivo) {
                button.setStyle(COLOR_ACTIVO);
            }
        });
        button.setOnMouseExited(e -> {
            scaleDown.play();
            if (button != botonActivo) {
                button.setStyle(COLOR_BASE);
            }
        });
    }
    @FXML
       private void marcarBotonActivo(Button botonPresionado) {
        Button[] todosLosBotones = {BTN_HOME, BTN_Tareas, BTN_Materias, BTN_Profesores, BTN_Calendario, BTN_Solicitudes, BTN_Tramites, BTN_Ajustes};
        for (Button b : todosLosBotones) {
            b.setStyle(COLOR_BASE);
        }
        
        botonPresionado.setStyle(COLOR_ACTIVO);
        botonPresionado.requestFocus(); // Establecer foco
        this.botonActivo = botonPresionado;
    }
    @FXML
    private void onLogoutClick() {
    	marcarBotonActivo(BTN_LOGOUT); 
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

   @FXML
    public void initialize() {        
    	addZoomEffect(BTN_HOME);
        addZoomEffect(BTN_Tareas);
        addZoomEffect(BTN_Materias);
        addZoomEffect(BTN_Profesores);
        addZoomEffect(BTN_Calendario);
        addZoomEffect(BTN_Solicitudes);
        addZoomEffect(BTN_Tramites);
        addZoomEffect(BTN_Ajustes);
        addZoomEffect(BTN_LOGOUT);
        marcarBotonActivo(BTN_HOME);
    }
}