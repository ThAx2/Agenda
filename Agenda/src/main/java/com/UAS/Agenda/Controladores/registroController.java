package Controladores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import ConexionBD.ConexionBD;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextFormatter; // ¡Importante!
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.UnaryOperator; // ¡Importante!
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class registroController{
	@FXML
	private TextField TextApell1;
	@FXML
	private TextField Textapell2;
	@FXML
	private TextField Textnomb;
	@FXML
	private TextField TextCorreo;
	@FXML
	private TextField TextNumCuenta;
	@FXML
	private TextField TextPIN;
	@FXML
	private TextField TextTelefono;
	@FXML
	private ComboBox<String> cmbxSemestre;
	@FXML
	private ComboBox<String> cmbxCarrera;
	@FXML
	private Button btnRegistrar;
	@FXML
	private Button btnRegresar;
	@FXML
	public void initialize() {
		cargarSemestres();
        cargarCarreras();
		UnaryOperator<TextFormatter.Change> filtroNomyApell= change ->{
			String newText = change.getControlNewText();
		if (newText.matches("[A-Za-z\\s]*")) { 
            return change;
        }
        return null;
		
	};
	UnaryOperator<TextFormatter.Change> filtrocuenta= change ->{
		String newText = change.getControlNewText();
	if (newText.matches("\\d{0,8}")) { 
        return change;
    }
    return null;
	
};
	UnaryOperator<TextFormatter.Change> filtronumeroT= change ->{
	String newText = change.getControlNewText();
	if (newText.matches("\\d{0,10}")) { 
		return change;
	}
	return null;
};	
	UnaryOperator<TextFormatter.Change> filterPin = change -> {
		String newText = change.getControlNewText();
		if (newText.matches("\\d{0,6}")) {
			return change;
		}
		return null;
};
UnaryOperator<TextFormatter.Change> filtroCorreo = change -> {
    String newText = change.getControlNewText();
    if (newText.matches("[a-zA-Z0-9@\\._\\-]*")) { 
        return change;
    }
    return null;
};
	TextFormatter<String> fnomb= new TextFormatter<>(filtroNomyApell);
	Textnomb.setTextFormatter(fnomb);
	TextFormatter<String> fappl1= new TextFormatter<>(filtroNomyApell);
	TextApell1.setTextFormatter(fappl1);
	TextFormatter<String> fappl2= new TextFormatter<>(filtroNomyApell);
	Textapell2.setTextFormatter(fappl2);
	TextFormatter<String> fnum= new TextFormatter<>(filtrocuenta);
	TextNumCuenta.setTextFormatter(fnum);
	TextFormatter<String> ftelefono= new TextFormatter<>(filtronumeroT);
	TextTelefono.setTextFormatter(ftelefono);
	TextFormatter<String> pinFormatter = new TextFormatter<>(filterPin);
    TextPIN.setTextFormatter(pinFormatter);
    TextFormatter<String> fcorreo = new TextFormatter<>(filtroCorreo);
    TextCorreo.setTextFormatter(fcorreo);
	}
    private void cargarSemestres() {
        // 1. Crear la lista de datos (ObservableList)
        ObservableList<String> opcionesSemestre = FXCollections.observableArrayList(
            "1er Semestre",
            "2do Semestre",
            "3er Semestre",
            "4to Semestre",
            "5to Semestre",
            "6to Semestre",
            "7mo Semestre",
            "8vo Semestre"
        );

        // 2. Enlazar la lista al ComboBox
        cmbxSemestre.setItems(opcionesSemestre);
        
        // Opcional: Seleccionar el primer elemento por defecto
        // cmbxSemestre.getSelectionModel().selectFirst(); 
    }
    
    private void cargarCarreras() {
        // 1. Crear la lista de datos (ObservableList)
        ObservableList<String> opcionesCarrera = FXCollections.observableArrayList(
            "Ingeniería en Software",
            "Licenciatura en Informática",
            "Arquitectura",
            "Medicina"
        );

        // 2. Enlazar la lista al ComboBox
        cmbxCarrera.setItems(opcionesCarrera);
    }
	@FXML
	private void BotonActionRegresar() {
		try {
            Parent root = FXMLLoader.load(getClass().getResource("/main-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Formulario de Registro");
            stage.show();
            ((Stage) btnRegresar.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@FXML
	private void BotonActionRegistrar() {
		String nmb=Textnomb.getText();
		String appl1=TextApell1.getText();
		String appl2=Textapell2.getText();
		String correo=TextCorreo.getText();
		String numUAS=TextNumCuenta.getText();
		String pin=TextPIN.getText();
		String numT=TextTelefono.getText();
		String semestre=cmbxSemestre.getSelectionModel().getSelectedItem().toString();
		String carrera=cmbxCarrera.getSelectionModel().getSelectedItem().toString();
		boolean registro=ConexionBD.insertarRegistro(nmb, appl1, appl2, correo, numUAS, pin, numT, semestre, carrera);
		if(registro) {
			System.out.println("Registro ingresado con exito");
		}else {
			System.out.println("Registr no ingresado");
		}
		
	}
}
