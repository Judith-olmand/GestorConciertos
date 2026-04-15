package org.example.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarConciertosArtista {
    public static boolean conciertosIdArtista(Connection conexion, int idArtista) {
        String consultaConciertos = "SELECT * FROM CONCIERTO S WHERE ID_A = ?";

        try (PreparedStatement ps = conexion.prepareStatement(consultaConciertos)) {
            ps.setInt(1, idArtista);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            System.out.println("Error al obtener conciertosIdArtista: " + e.getMessage());
        }
        return false;
    }
}
