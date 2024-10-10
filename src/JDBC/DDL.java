package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DDL {
    static final String URL = "jdbc:mariadb://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "root";

    static final String DB = "gestion_educativa";

    static Connection conexion;

    private DDL() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // conecta el driver
            conexion = DriverManager.getConnection(URL, USER, PASS); // conecta DB
            PreparedStatement st = conexion.prepareStatement("USE " + DB);
            st.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        if (conexion == null) 
            new DDL();
        return conexion;
    }

    public static void close() throws SQLException {
        if(conexion != null)
        conexion.close();
        conexion = null;
    }
 }
