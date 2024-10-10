package asignatura;

import java.sql.*;
import java.util.ArrayList;

import JDBC.DDL;

public class DAOAsignatura {
    static Connection conexion;
    // sentencias CRUD
    private static String CREATE = "INSERT INTO asignatura (Nombre, HorasSemanales) VALUES (?,?)";
    private static String READ = "SELECT * FROM asignatura WHERE ID =?";
    private static String READ_ALL = "SELECT * FROM asignatura";
    private static String UPDATE = "UPDATE asignatura SET Nombre=?, HorasSemanales=? WHERE ID=?";
    private static String DELETE = "DELETE FROM asignatura WHERE ID=?";

    public DAOAsignatura() {
        conexion = DDL.getConexion();
    }

    // Métodos CRUD
    public boolean create(DTOAsignatura asignatura) {
        try (PreparedStatement pst = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, asignatura.getNombre());
            pst.setInt(2, asignatura.getHorasSemanales());

            int rowsAffected = pst.executeUpdate();

            // Gestionar el ID generado por la base de datos
            if (rowsAffected != 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        asignatura.setId(rs.getInt(1));
                    }
                }
                return true; // Devuelve true si se creó la asignatura
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    public DTOAsignatura read(int id) {
        DTOAsignatura asignatura = null;
        try {
            try (PreparedStatement pst = conexion.prepareStatement(READ)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    // Si se encuentra algo, llamar al método privado para convertirlo a
                    // DTOAsignatura
                    asignatura = getAsignatura(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asignatura;
    }

    public ArrayList<DTOAsignatura> readAll() {
        ArrayList<DTOAsignatura> listaAsignaturas = new ArrayList<>();
        try {
            try (PreparedStatement pst = conexion.prepareStatement(READ_ALL);
                    ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    listaAsignaturas.add(getAsignatura(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAsignaturas;
    }

    public boolean update(DTOAsignatura asignatura) {
        try (PreparedStatement pst = conexion.prepareStatement(UPDATE)) {
            pst.setString(1, asignatura.getNombre());
            pst.setInt(2, asignatura.getHorasSemanales());
            pst.setInt(3, asignatura.getId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected != 0; // Devuelve true si se actualizó la asignatura
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    public boolean delete(int id) {
        try (PreparedStatement pst = conexion.prepareStatement(DELETE)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected != 0; // Devuelve true si se eliminó la asignatura
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    // Convertir ResultSet a DTOAsignatura
    private DTOAsignatura getAsignatura(ResultSet rs) {
        DTOAsignatura asignatura = null;

        try {
            // Coger datos de la consulta
            int id = rs.getInt("ID");
            String nombre = rs.getString("Nombre");
            int HorasSemanales = rs.getInt("HorasSemanales");

            // Cargar asignatura con datos leídos
            asignatura = new DTOAsignatura(nombre, HorasSemanales);
            asignatura.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asignatura;
    }
}
