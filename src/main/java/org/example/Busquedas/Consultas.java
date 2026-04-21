package org.example.Busquedas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas {
    public static boolean consultas(Connection conexion, String consulta, int id){
        try (PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al obtener conciertosIdArtista: " + e.getMessage());
        }
        return false;
    }
}
