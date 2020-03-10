package Conexion;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Conexion conexion;
    private String usuario = "postgres", password = "1234", iP = "localhost", puerto = "5433", nombreBD = "filtros";
    private Connection con;

    private Conexion() {

    }

    public static Conexion getConexion() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    public Conexion(String iP, String puerto, String nombreBD, String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.iP = iP;
        this.puerto = puerto;
        this.nombreBD = nombreBD;

    }

    // sw es true si va a conectar con MySQL (3306) y postgresql (5432)
    // sw es false si va a conectar con access porque es por medio del odbc
    private void conectar(String driver, String puente, boolean sw)
            throws ClassNotFoundException,
            SQLException,
            InstantiationException,
            IllegalAccessException {

        Class.forName(driver).newInstance();

        if (sw) {
            con = DriverManager.getConnection("jdbc:" + puente
                    + "://" + iP
                    + ":" + puerto
                    + "/" + nombreBD, usuario, password);
        } else {
            con = DriverManager.getConnection("jdbc:" + puente
                    + ":" + nombreBD, usuario, password);
        }
        
    }

    // Con JDBC
    public void ConexionJDBC() throws ClassNotFoundException,
            SQLException,
            InstantiationException,
            IllegalAccessException {
        conectar("sun.jdbc.odbc.JdbcOdbcDriver", "odbc", false);
    }

   

    // Con PostgreSql
    public void ConexionPostgres() throws ClassNotFoundException,
            SQLException,
            InstantiationException,
            IllegalAccessException {
        conectar("org.postgresql.Driver", "postgresql", true);
    }

   

    public void cerrar() {
        try {if(con!=null)
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }

}
