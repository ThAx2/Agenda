package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TramitesController {

    @FXML
    private Label lblDocumentTitle;
    
    @FXML
    private VBox documentContentVBox;
    
    // Método implementado para corregir el error de onAction: #showKardex
    @FXML
    public void showKardex(ActionEvent event) {
        lblDocumentTitle.setText("Vista Previa de Kárdex");
        // Aquí se implementaría la lógica para cargar la tabla de Kárdex
        System.out.println("Cargando Kárdex...");
    }
    
    // Implementación de los demás métodos onAction requeridos por Tramites.fxml
    @FXML
    public void showHorario(ActionEvent event) {
        lblDocumentTitle.setText("Vista Previa de Horario");
        System.out.println("Cargando Horario...");
    }
    
    @FXML
    public void showCargaAcademica(ActionEvent event) {
        lblDocumentTitle.setText("Vista Previa de Carga Académica");
        System.out.println("Cargando Carga Académica...");
    }

    @FXML
    public void showEstadoCuenta(ActionEvent event) {
        lblDocumentTitle.setText("Vista Previa de Estado de Cuenta");
        System.out.println("Cargando Estado de Cuenta...");
    }
    
    @FXML
    public void downloadReglamento(ActionEvent event) {
        System.out.println("Descargando Reglamento Escolar...");
    }

    @FXML
    public void downloadDocument(ActionEvent event) {
        System.out.println("Descargando " + lblDocumentTitle.getText() + "...");
    }

    @FXML
    public void initialize() {
        // Lógica de inicialización si es necesaria
        
        // Cargar el Kárdex por defecto al inicio de la vista
        // Nota: Solo se llama al método, no se pasa ActionEvent
        // showKardex(null); 
    }
}