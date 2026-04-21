package org.example.Busquedas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarIDArtista {
    public static boolean idArtista(Connection conexion, int idArtista) {
        String consultaIdArtista = "SELECT ID_A FROM ARTISTA WHERE ID_A = ?";

        try (PreparedStatement ps = conexion.prepareStatement(consultaIdArtista)){
            ps.setInt(1, idArtista);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay fila, el artista existe; si no, false
        } catch (SQLException e){
            System.out.println("Error al obtener id del artista: " + e.getMessage());
        }
        return false;
    }
}
