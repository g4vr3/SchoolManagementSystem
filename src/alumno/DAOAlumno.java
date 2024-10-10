package alumno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import JDBC.DDL;

public class DAOAlumno {
    static Connection conexion;
    // sentencias CRUD
    private static String CREATE = "INSERT INTO alumno (Nombre, FechaNacimiento, Telefono, Direccion) VALUES (?,?,?,?)";
    private static String READ = "SELECT * FROM alumno WHERE ID =?";
    private static String READ_ALL = "SELECT * FROM alumno";
    private static String UPDATE = "UPDATE alumno SET Nombre=?, FechaNacimiento=?, Telefono=?, Direccion=? WHERE ID=?";
    private static String DELETE = "DELETE FROM alumno WHERE ID=?";

    public DAOAlumno() {
        conexion = DDL.getConexion();
    }

    // Métodos CRUD
    public boolean create(DTOAlumno alumno) {
        try (PreparedStatement pst = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, alumno.getNombre());
            pst.setDate(2, Date.valueOf(alumno.getFechaNacimiento())); // Convertir LocalDate a Date
            pst.setString(3, alumno.getTelefono());
            pst.setString(4, alumno.getDireccion());

            int rowsAffected = pst.executeUpdate();

            // Gestionar el ID generado por la base de datos
            if (rowsAffected != 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        alumno.setId(rs.getInt(1));
                    }
                }
                return true; // Devuelve true si se actualizó el alumno
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    public DTOAlumno read(int id) {
        DTOAlumno alumno = null;
        try {
            try (PreparedStatement pst = conexion.prepareStatement(READ)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    // Si se encuentra algo, llamar al método privado para convertirlo a DTOAlumno
                    alumno = getAlumno(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumno;
    }

    public ArrayList<DTOAlumno> readAll() { 
        ArrayList<DTOAlumno> listaAlumnos = new ArrayList<>();
        try {
            try (PreparedStatement pst = conexion.prepareStatement(READ_ALL);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    listaAlumnos.add(getAlumno(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }

    public boolean update(DTOAlumno alumno) {
        try (PreparedStatement pst = conexion.prepareStatement(UPDATE)) {
            pst.setString(1, alumno.getNombre());
            pst.setDate(2, Date.valueOf(alumno.getFechaNacimiento()));
            pst.setString(3, alumno.getTelefono());
            pst.setString(4, alumno.getDireccion());
            pst.setInt(5, alumno.getId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected != 0; // Devuelve true si se actualizó el alumno
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    public boolean delete(int id) {
        try (PreparedStatement pst = conexion.prepareStatement(DELETE)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected != 0; // Devuelve true si se eliminó el alumno
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Operación fallida
    }

    // Convertir ResultSet a DTOAlumno
    private DTOAlumno getAlumno(ResultSet rs) {
        DTOAlumno alumno = null;

        try {
            // Coger datos de la consulta
            int id = rs.getInt("ID");
            String nombre = rs.getString("Nombre");
            LocalDate fechaNacimiento = rs.getDate("FechaNacimiento").toLocalDate();
            String telefono = rs.getString("Telefono");
            String direccion = rs.getString("Direccion");

            // Cargar alumno con datos leídos
            alumno = new DTOAlumno(nombre, fechaNacimiento, telefono, direccion);
            alumno.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumno;
    }
}
