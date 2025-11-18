package Modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Materia {
    
    private StringProperty nombre;
    private StringProperty codigo; 

    public Materia(String nombre, String codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleStringProperty(codigo);
    }
    
    // Getter para JavaFX Properties (opcional, pero útil)
    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getNombre() {
        return nombre.get();
    }
    
    // ✅ MÉTODO AÑADIDO: NECESARIO para que MateriasController compile
    public String getCodigo() {
        return codigo.get();
    }
    
    @Override
    public String toString() {
        return nombre.get() + " (" + codigo.get() + ")";
    }
}