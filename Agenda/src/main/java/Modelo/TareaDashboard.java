package Modelo; // O Utilidades, donde guardes tus modelos

import javafx.beans.property.SimpleStringProperty;

// Clase modelo para la tabla de Tareas con Vencimiento Cercano
public class TareaDashboard {
    private final SimpleStringProperty materia;
    private final SimpleStringProperty tarea;
    private final SimpleStringProperty vence;

    public TareaDashboard(String materia, String tarea, String vence) {
        this.materia = new SimpleStringProperty(materia);
        this.tarea = new SimpleStringProperty(tarea);
        this.vence = new SimpleStringProperty(vence);
    }

    public String getMateria() { return materia.get(); }
    public String getTarea() { return tarea.get(); }
    public String getVence() { return vence.get(); }
}