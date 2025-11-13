package com.UAS; // O com.UAS.Agenda.Application, si ese es tu paquete raíz
import Controladores.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Panel extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar FXML desde resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panel.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Panel Principal");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
