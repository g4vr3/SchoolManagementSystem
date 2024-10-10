package matricula;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;

import JDBC.DDL;

public class DAOMatricula {
    static Connection conexion;
    
    // Sentencias CRUD
    private static final String CREATE = "INSERT INTO matricula (idAlumno, idAsignatura, FechaMatricula) VALUES (?, ?, ?)";
    private static String READ_ALL = "SELECT * FROM matricula";
    // private static final String DELETE_BY_ALUMNO_ID = "DELETE FROM matricula WHERE idAlumno = ?";
    // private static final String DELETE_BY_ASIGNATURA_ID = "DELETE FROM matricula WHERE idAsignatura = ?";

    public DAOMatricula() {
        conexion = DDL.getConexion();
    }

    // Métodos CRUD
    public boolean create(DTOMatricula matricula) {
        try (PreparedStatement pst = conexion.prepareStatement(CREATE)) {
            pst.setInt(1, matricula.getIdAlumno());
            pst.setInt(2, matricula.getIdAsignatura());
            // Convertir LocalDate a java.sql.Date
            pst.setDate(3, java.sql.Date.valueOf(matricula.getFechaMatricula())); 
    
            int rowsAffected = pst.executeUpdate();
    
            if (rowsAffected != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public ArrayList<DTOMatricula> readAll() {
        ArrayList<DTOMatricula> listaAsignaturas = new ArrayList<>();
        try {
            try (PreparedStatement pst = conexion.prepareStatement(READ_ALL);
                    ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    listaAsignaturas.add(getMatricula(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAsignaturas;
    }

    // Convertir ResultSet a DTOAsignatura
    private DTOMatricula getMatricula(ResultSet rs) {
        DTOMatricula matricula = null;

        try {
            // Coger datos de la consulta
            int idAlumno = rs.getInt("idAlumno");
            int idAsignatura = rs.getInt("idAsignatura");
            LocalDate fechaMatricula = LocalDate.parse(rs.getString("fechaMatricula"));

            // Cargar asignatura con datos leídos
            matricula = new DTOMatricula(idAlumno, idAsignatura, fechaMatricula);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricula;
    }

    // // Método para eliminar todas las matrículas de un alumno
    // public void deleteByAlumnoID(int idAlumno) {
    //     try (PreparedStatement pst = conexion.prepareStatement(DELETE_BY_ALUMNO_ID)) {
    //         pst.setInt(1, idAlumno);

    //         int rowsAffected = pst.executeUpdate();

    //         if (rowsAffected > 0) {
    //             System.out.println("Matrículas del alumno con ID " + idAlumno + " eliminadas.");
    //         } else {
    //             System.out.println("No se encontraron matrículas para eliminar.");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // Método para eliminar todas las matrículas de una asignatura
    // public void deleteByAsignaturaID(int idAsignatura) {
    //     try (PreparedStatement pst = conexion.prepareStatement(DELETE_BY_ASIGNATURA_ID)) {
    //         pst.setInt(1, idAsignatura);

    //         int rowsAffected = pst.executeUpdate();

    //         if (rowsAffected > 0) {
    //             System.out.println("Matrículas de la asignatura con ID " + idAsignatura + " eliminadas.");
    //         } else {
    //             System.out.println("No se encontraron matrículas para eliminar.");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
