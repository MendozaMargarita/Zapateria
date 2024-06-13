package accesoadatos;



import entidadesdenegocio.ZapatoEN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZapatosDAL {

    // método que permite guardar un nuevo registro
    public static int guardar(ZapatoEN zapatos) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO Zapatos( Nombre, Marca, Color, Talla ) VALUES(?, ?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);

            ps.setString(1, zapatos.getNombre());
            ps.setString(2, zapatos.getMarca());
            ps.setString(3, zapatos.getColor());
            ps.setString(4, zapatos.getTalla());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(ZapatoEN zapatos) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Zapatos SET Codigo = ?,    Nombre = ?, Apellido = ?, Carrera = ? WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);

            ps.setString(1, zapatos.getNombre());
            ps.setString(2, zapatos.getMarca());
            ps.setString(3, zapatos.getColor());
            ps.setString(4, zapatos.getTalla());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(ZapatoEN zapatos) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Zapatos WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, zapatos.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que muestra todos los registros de la tabla
    public static ArrayList<ZapatoEN> obtenerTodos() throws SQLException {
        ArrayList<ZapatoEN> lista = new ArrayList<>();
        ZapatoEN zapatos;
        try{
            String sql = "SELECT Id, nombre, marca,  color, talla FROM Zapatos";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                zapatos= new ZapatoEN(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                lista.add(zapatos);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<ZapatoEN> obtenerDatosFiltrados(ZapatoEN zapatos) throws SQLException{
        ArrayList<ZapatoEN> lista = new ArrayList<>();
        ZapatoEN est;
        try{
            String sql = "SELECT id, nombre, marca, color, talla FROM Zapatos WHERE id = ? or nombre like'%" + zapatos.getNombre() + "%' or marca like'%" + zapatos.getMarca() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, zapatos.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                est = new ZapatoEN(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}