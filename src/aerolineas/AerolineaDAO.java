
package aerolineas;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AerolineaDAO {
    
    // objeto tipo ConexionDB
    private ConexionDB conexion;
    
    public AerolineaDAO(){
        conexion = new ConexionDB();
    }
    
    //metodo para crear o insertar aerolineas en la base de datos
    public boolean crearAerolinea(Aerolinea aerolinea){
        String query = "INSERT INTO aerolinea (nombre, pais, cod_iata,"
                        + "cod_icao) VALUES (?, ?, ?, ?)"; 
        
        try (
                Connection conn = conexion.conectarDB();
                PreparedStatement stmt = conn.prepareStatement(query,
                                Statement.RETURN_GENERATED_KEYS)
            )
        {
            stmt.setString(1, aerolinea.getNombre());
            stmt.setString(2, aerolinea.getPais());
            stmt.setString(3, aerolinea.getCod_iata());
            stmt.setString(4, aerolinea.getCod_icao());
            stmt.execute();
            JOptionPane.showMessageDialog(null,
                            "Datos Agregados",
                            "Agregacion de datos",
                            JOptionPane.INFORMATION_MESSAGE);
            //obtener el id del registro insertado
            try(ResultSet rs = stmt.getGeneratedKeys();) 
            {
                if (rs.next()) {
                    aerolinea.setId(rs.getInt(1));
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                            "Error al agregar datos a la base de datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                            "Error al agregar datos a la base de datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public List<Aerolinea> leerDatos(){
        List<Aerolinea> aerolineas = new ArrayList<>();
        String sql = "SELECT id, nombre, pais, cod_iata, cod_icao FROM aerolinea";
        
        try (
               Connection conn = conexion.conectarDB();
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(sql)
            )
        {
            while (rs.next()) {                
                Aerolinea aerolinea = new Aerolinea(
                                   rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("pais"),
                                rs.getString("cod_iata"),
                                rs.getString("cod_icao")
                                
                );
                
                aerolineas.add(aerolinea);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                            "Error al agregar datos a la base de datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        return aerolineas;
    }
    
    //metodo para actualizar datos
    public void actualizarDatos(Aerolinea aerolinea){
        String sql = "UPDATE aerolinea SET nombre = ?, pais = ?, cod_iata = ?,"
                        + "cod_icao = ? WHERE id = ?";
        
        try (
                Connection conn = conexion.conectarDB();
                PreparedStatement stmt = conn.prepareStatement(sql)
            )
        {
            stmt.setString(1, aerolinea.getNombre());
            stmt.setString(2, aerolinea.getPais());
            stmt.setString(3, aerolinea.getCod_iata());
            stmt.setString(4, aerolinea.getCod_icao());
            stmt.setInt(5, aerolinea.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,
                            "Datos actualizados correctamente",
                            "Actualizacion de datos",
                            JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                            "Error al agregar datos a la base de datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarDatos(int id){
        String sql = "Delete FROM aerolinea WHERE id = ?";
        try (
                Connection conn = conexion.conectarDB();
                PreparedStatement stmt = conn.prepareStatement(sql)
            ) 
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                            "Error al eliminar datos:\n" + e,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
