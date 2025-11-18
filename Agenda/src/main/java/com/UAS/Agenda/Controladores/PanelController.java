package Controladores;

import Modelo.Usuario; // ¡Importante!
import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane; 
import javafx.stage.Stage;

public class PanelController implements Initializable { 

    private final String COLOR_ACTIVO = "-fx-background-color: #4c5363; -fx-background-radius: 50px;";
    private final String COLOR_BASE = "-fx-background-color: #2B2F38;";
    private final double ZOOM_SCALE = 1.15;
    
    // ✅ Campo esencial para almacenar el usuario
    private Usuario usuarioActual; 

    @FXML private AnchorPane contenidoPrincipal; 
    @FXML private Button BTN_LOGOUT;
    @FXML private Button BTN_HOME;
    @FXML private Button BTN_Tareas;
    @FXML private Button BTN_Materias;
    @FXML private Button BTN_Profesores;
    @FXML private Button BTN_Calendario;
    @FXML private Button BTN_Solicitudes; // Cambié BTN_Tramites a BTN_Solicitudes basado en tu código
    @FXML private Button BTN_Tramites;
    @FXML private Button BTN_Ajustes;
    
    private Button botonActivo;

    // =======================================================================
    // I. MÉTODOS DE INICIO Y CONFIGURACIÓN
    // =======================================================================
    
    /**
     * ✅ CRÍTICO: Recibe el objeto Usuario del MainController
     */
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        marcarBotonActivo(BTN_HOME);
        
        // Opcional: Mostrar nombre de usuario
        // if (LBL_Usuario != null) LBL_Usuario.setText("Hola, " + usuario.getNombre());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addZoomEffect(BTN_HOME);
        addZoomEffect(BTN_Tareas);
        addZoomEffect(BTN_Materias);
        addZoomEffect(BTN_Profesores);
        addZoomEffect(BTN_Calendario);
        // Asumiendo que BTN_Solicitudes es lo que querías para un botón
        // y BTN_Tramites es otro. Se usa BTN_Tramites si quieres.
        addZoomEffect(BTN_Solicitudes); 
        addZoomEffect(BTN_Tramites);
        addZoomEffect(BTN_Ajustes);
        addZoomEffect(BTN_LOGOUT);
    }
    
    public void loadCenterView(String fxmlPath) {
        if (fxmlPath == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node newView = loader.load(); 
            
            Object controller = loader.getController();

            // CRÍTICO: Sección de Inyección del objeto Usuario.
            if (controller instanceof AjustesController) {
                ((AjustesController) controller).setUsuario(usuarioActual);
            } else if (controller instanceof HomeController) {
                ((HomeController) controller).setUsuario(usuarioActual);
            } 
            // 👇 ¡ESTA ES LA LÍNEA QUE FALTABA! 👇
            else if (controller instanceof MateriasController) {
                 System.out.println("DEBUG PanelController: Inyectando Usuario a MateriasController.");
                ((MateriasController) controller).setUsuario(usuarioActual);
            }
            // 👆 FIN DE LA ADICIÓN 👆
            
            // Agrega aquí cualquier otro controlador (e.g., TareasController) que necesite el objeto Usuario
            
            contenidoPrincipal.getChildren().setAll(newView);

            // Ajustar el anclaje
            AnchorPane.setTopAnchor(newView, 0.0);
            AnchorPane.setBottomAnchor(newView, 0.0);
            AnchorPane.setLeftAnchor(newView, 0.0);
            AnchorPane.setRightAnchor(newView, 0.0);

        } catch (IOException e) {
            System.err.println("Error al cargar la vista central: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private String getFxmlPath(Button button) {
        if (button == BTN_HOME) return "/Home.fxml"; 
        if (button == BTN_Tareas) return "/Tareas.fxml"; 
        if (button == BTN_Materias) return "/Materias.fxml";
        if (button == BTN_Profesores) return "/Profesores.fxml";
        if (button == BTN_Calendario) return "/Calendario.fxml";
        if (button == BTN_Solicitudes) return "/Solicitudes.fxml";
        if (button == BTN_Tramites) return "/Tramites.fxml";
        if (button == BTN_Ajustes) return "/Ajustes.fxml";
        return null;
    }

    // ------------------------------------------------------------------
    // III. LÓGICA DE NAVEGACIÓN Y EFECTOS
    // ------------------------------------------------------------------
    
    // ... (Tu código de addZoomEffect y onLogoutClick se mantiene igual) ...

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
        });
    }

    private void marcarBotonActivo(Button botonPresionado) {
        if (this.botonActivo != null) {
            this.botonActivo.setStyle(COLOR_BASE);
        }
        
        botonPresionado.setStyle(COLOR_ACTIVO);
        botonPresionado.requestFocus();
        this.botonActivo = botonPresionado;
        
        String fxmlPath = getFxmlPath(botonPresionado);
        loadCenterView(fxmlPath);
    }
    
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