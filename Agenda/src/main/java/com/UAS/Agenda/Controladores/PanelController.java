package Controladores;

import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node; // Importar Node para la nueva vista
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane; // Importar AnchorPane
import javafx.stage.Stage;

public class PanelController {

    private final String COLOR_ACTIVO = "-fx-background-color: #4c5363; -fx-background-radius: 50px;";
    private final String COLOR_BASE = "-fx-background-color: #2B2F38;";
    private final double ZOOM_SCALE = 1.15;

    // --- NUEVA INYECCIÓN: El contenedor central (contenidoPrincipal) ---
    @FXML
    private AnchorPane contenidoPrincipal; 
    // Asegúrate de que este fx:id="contenidoPrincipal" esté definido en tu Panel.fxml

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

    // ------------------------------------------------------------------
    // Métodos existentes: addZoomEffect y onLogoutClick (se mantienen igual)
    // ------------------------------------------------------------------

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

        button.setOnAction(e -> {
            marcarBotonActivo(button);
            // La carga de la vista se hace dentro de marcarBotonActivo
        });
    }

    // ------------------------------------------------------------------
    // --- MÉTODO CLAVE 1: Mapear Botón a FXML ---
    private String getFxmlPath(Button button) {
        if (button == BTN_HOME) {
            return "/Home.fxml"; // La vista inicial, que contiene el dashboard
        } else if (button == BTN_Tareas) {
            return "/Tareas.fxml"; 
        } else if (button == BTN_Materias) {
            return "/Materias.fxml";
        } else if (button == BTN_Profesores) {
            return "/Profesores.fxml";
        } else if (button == BTN_Calendario) {
            return "/Calendario.fxml";
        } else if (button == BTN_Solicitudes) {
            return "/Solicitudes.fxml";
        } else if (button == BTN_Tramites) {
            return "/Tramites.fxml";
        } else if (button == BTN_Ajustes) {
            return "/Ajustes.fxml";
        }
        return null; // En caso de que se presione un botón sin FXML asociado
    }

    // --- MÉTODO CLAVE 2: Carga de FXML en el Centro ---
    public void loadCenterView(String fxmlPath) {
        if (fxmlPath == null) return;
        try {
            // Cargar el FXML de la nueva vista
            Node newView = FXMLLoader.load(getClass().getResource(fxmlPath));

            // Reemplaza el contenido del panel central
            contenidoPrincipal.getChildren().setAll(newView);

            // Asegurar que la nueva vista ocupe todo el AnchorPane
            AnchorPane.setTopAnchor(newView, 0.0);
            AnchorPane.setBottomAnchor(newView, 0.0);
            AnchorPane.setLeftAnchor(newView, 0.0);
            AnchorPane.setRightAnchor(newView, 0.0);

        } catch (IOException e) {
            System.err.println("Error al cargar la vista central: " + fxmlPath);
            e.printStackTrace();
        }
    }

    // --- MODIFICACIÓN: Integrar el cambio de vista aquí ---
    private void marcarBotonActivo(Button botonPresionado) {
        // 1. Lógica de estilos (se mantiene)
        Button[] todosLosBotones = {BTN_HOME, BTN_Tareas, BTN_Materias, BTN_Profesores, BTN_Calendario, BTN_Solicitudes, BTN_Tramites, BTN_Ajustes};
        
        // Desactivar todos
        for (Button b : todosLosBotones) {
            b.setStyle(COLOR_BASE);
        }
        
        // Activar el presionado
        botonPresionado.setStyle(COLOR_ACTIVO);
        botonPresionado.requestFocus();
        this.botonActivo = botonPresionado;
        
        // 2. Lógica de CAMBIO DE VISTA
        String fxmlPath = getFxmlPath(botonPresionado);
        loadCenterView(fxmlPath);
    }

    @FXML
    private void onLogoutClick() {
        // ... (Se mantiene igual) ...
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
        // ... (Se mantiene igual) ...
        addZoomEffect(BTN_HOME);
        addZoomEffect(BTN_Tareas);
        addZoomEffect(BTN_Materias);
        addZoomEffect(BTN_Profesores);
        addZoomEffect(BTN_Calendario);
        addZoomEffect(BTN_Solicitudes);
        addZoomEffect(BTN_Tramites);
        addZoomEffect(BTN_Ajustes);
        addZoomEffect(BTN_LOGOUT);
        
        // Al iniciar, activa el botón HOME y carga su vista
        marcarBotonActivo(BTN_HOME); 
    }
}