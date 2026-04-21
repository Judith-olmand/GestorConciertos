package org.example.Extraer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NombreArtista {
    public static String nombreArtista(Connection conexion, int idConcierto) {
        String consultaNombreArtista = "SELECT NOMBRE_A  " +
                "FROM ARTISTA JOIN CONCIERTO USING(ID_A) " +
                "WHERE ID_C = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consultaNombreArtista)){
            ps.setInt(1,idConcierto);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("NOMBRE_A");
        } catch (SQLException e) {
            System.out.println("Error al obtener el nombre del artista del concierto");
        }
        return null;
    }
}
