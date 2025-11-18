package Modelo;

public class Usuario {
    
    // Campos basados en la tabla USUARIO
    private int ID_USUARIO;
    private String ID_MATRICULA; 
    private String CORREO;
    private String TELEFONO; 
    private String NOMBRE; 
    private String APELLIDO_1;
    private String APELLIDO_2;
    private double promedioGeneral; 
    
    public Usuario() {}
    public Usuario(int id, String nombre, String apellido1, String apellido2, String matricula, String correo, double promedio) {
        this.ID_USUARIO = id;
        this.NOMBRE = nombre;
        this.APELLIDO_1 = apellido1;
        this.APELLIDO_2 = apellido2;
        this.ID_MATRICULA = matricula;
        this.CORREO = correo;
        this.promedioGeneral = promedio;
    }

    // --- GETTERS (Necesarios para PanelController, HomeController y AjustesController) ---

    public int getId() {
        return ID_USUARIO;
    }
    
    public String getNombre() {
        return NOMBRE;
    }
    
    public String getNombreCompleto() {
        String ap1 = (APELLIDO_1 != null) ? APELLIDO_1 : "";
        String ap2 = (APELLIDO_2 != null) ? APELLIDO_2 : "";
        return NOMBRE + " " + ap1 + " " + ap2;
    }

    public String getMatricula() {
        return ID_MATRICULA;
    }

    public String getCorreo() {
        return CORREO;
    }
    
    public double getPromedioGeneral() {
        return promedioGeneral;
    }
    
    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }
}