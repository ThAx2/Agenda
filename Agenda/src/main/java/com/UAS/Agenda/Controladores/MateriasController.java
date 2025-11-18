package Controladores;

import Modelo.Usuario;
import Modelo.Materia; 
import Modelo.MateriaDAO; 
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label; 
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane; // Añadido TabPane
import javafx.scene.control.TableColumn; // Añadido TableColumn

public class MateriasController implements Initializable {
    
    // Inyecciones de la lista (Panel Izquierdo)
    @FXML private ListView<Materia> materiaListView; 
    
    // Inyecciones del Panel Derecho (Sincronizado con Materias.fxml)
    @FXML private Label lblMateriaSeleccionada; // <-- Nombre corregido (Línea 21 FXML)
    @FXML private TabPane detailsTabPane; 
    @FXML private TableView<?> gradesTable; // fx:id="gradesTable" (Línea 30 FXML)
    @FXML private TableView<?> filesTable; // fx:id="filesTable" (Línea 65 FXML)

    // Columnas de la tabla (Opcional, pero bueno tenerlas para el futuro)
    @FXML private TableColumn<?, ?> colEvaluacion;
    @FXML private TableColumn<?, ?> colValor;
    @FXML private TableColumn<?, ?> colNota;
    @FXML private TableColumn<?, ?> colComentario;
    // ... otras columnas ...

    // Campo que contendrá el objeto del usuario logueado
    private Usuario usuarioActual;

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario; 
        cargarMateriasDelUsuario();
        // ✅ CRÍTICO: Configura el Listener DESPUÉS de que la lista se cargó
        setupListViewListener(); 
    }
    
    private void cargarMateriasDelUsuario() {
         if (usuarioActual != null) {
             
             MateriaDAO materiaDAO = new MateriaDAO();
             String matriculaUsuario = usuarioActual.getMatricula(); 
             
             // ... (Verificación de matrícula) ...
             
             List<Materia> listaMaterias = materiaDAO.obtenerMateriasPorUsuario(matriculaUsuario); 
             
             ObservableList<Materia> items = FXCollections.observableArrayList(listaMaterias);
             
             if (materiaListView == null) {
                 System.err.println("ERROR FXML: La ListView no fue inyectada.");
                 return;
             }
             
             materiaListView.setItems(items); 
             
             System.out.println("DEBUG Controller: Se cargaron " + listaMaterias.size() + " materias.");
             // El Label de Bienvenida no está en el FXML actual, pero se comenta para claridad:
             // if (lblBienvenidaMaterias != null) { lblBienvenidaMaterias.setText("Mis Materias del Semestre"); }
             
             // Seleccionar la primera materia automáticamente para que el panel derecho no esté vacío
             if (!materiaListView.getItems().isEmpty()) {
                 materiaListView.getSelectionModel().selectFirst();
             }
         }
     }
    
    /**
     * Configura el Listener y se asegura de que la ListView no es null.
     */
    private void setupListViewListener() {
        if (materiaListView != null) {
            materiaListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        mostrarDetallesDeMateria(newValue);
                    }
                });
            System.out.println("DEBUG Controller: Listener de ListView configurado.");
        }
    }
    
    /**
     * Muestra el nombre y código de la materia seleccionada en el panel de detalles.
     */
    private void mostrarDetallesDeMateria(Materia materiaSeleccionada) {
        if (materiaSeleccionada == null) return;
        
        // **ACTUALIZACIÓN CRÍTICA**: Usando lblMateriaSeleccionada del FXML
        if (lblMateriaSeleccionada != null) {
            String titulo = materiaSeleccionada.getNombre() + " (" + materiaSeleccionada.getCodigo() + ")"; 
            lblMateriaSeleccionada.setText(titulo);
            System.out.println("DEBUG Controller: Materia Seleccionada -> " + titulo);
        }
        
        // El FXML no tiene fx:id para el Label del Promedio, por lo que este código
        // está comentado por ahora para evitar NullPointerException si no existe.
        /*
        if (lblPromedioActual != null) {
             lblPromedioActual.setText("Promedio Actual: 9.2"); 
        }
        */
        
        // Aquí se llamaría a la función para cargar las calificaciones, recursos, etc.
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se deja vacío. El setup del Listener y la carga de datos se hace en setUsuario().
    }
}