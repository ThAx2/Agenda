package Modelo; 

import Utilidades.DatabaseConnection; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public List<Materia> obtenerMateriasPorUsuario(String matricula) {
        List<Materia> materias = new ArrayList<>();
        
        // SQL: Usa CAST para el JOIN y pide M.ID_MATERIA (no 'codigo')
        String sql = "SELECT "
                   + "    M.NOMBRE_MATERIA, "
                   + "    M.ID_MATERIA " // <-- Solución al error 'Unknown label codigo'
                   + "FROM "
                   + "    MATERIA M "
                   + "INNER JOIN "
                   + "    USUARIO U ON CAST(M.ID_CARRERA AS SIGNED) = CAST(U.ID_CARRERA AS SIGNED) AND CAST(M.ID_SEMESTRE AS SIGNED) = CAST(U.ID_SEMESTRE AS SIGNED) " 
                   + "WHERE "
                   + "    U.ID_MATRICULA = ?"; 
        
        System.out.println("DEBUG DAO: Ejecutando consulta con CAST para matrícula: " + matricula);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    String nombre = rs.getString("NOMBRE_MATERIA");
                    
                    // LECTURA: Usamos el nombre de columna original y correcto.
                    String codigo = rs.getString("ID_MATERIA"); 
                    
                    Materia materia = new Materia(nombre, codigo); 
                    materias.add(materia);
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR FATAL AL CONSULTAR MATERIAS (SQLException): " + e.getMessage());
            e.printStackTrace();
        } 
        
        System.out.println("DEBUG DAO: Materias encontradas: " + materias.size());
        return materias;
    }
}