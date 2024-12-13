
package aerolineas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
                

public class ConexionDB {
    
    // objecto tipo java.sql.Connection
    private static Connection conn;
    
    //atributos para la conexion a db
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "erik";
    private static final String PASSWORD = "zambito123";
    private static final String URL = "jdbc:mysql://localhost:3306/aeromanager";
    
    //constructor
    public ConexionDB() {
        conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,
                            "Error en la conexion a la base de datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //metodo para retornar la conexion a la base de datos
    public Connection conectarDB() {
        return conn;
    }
    
    // metodo para desconectar db
    public void desconectar() {
        conn = null;
        if (conn == null) {
            System.out.println("CONEXION FINALIZADA");
        }
    }
    
}
