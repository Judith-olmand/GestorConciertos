package org.example.Busquedas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NombreArtista {
    public static boolean nombreArtista(Connection conexion, String nombre) {
        String consultaNombreArtista = "SELECT NOMBRE_A FROM ARTISTA WHERE NOMBRE_A LIKE ?";

        try (PreparedStatement ps = conexion.prepareStatement(consultaNombreArtista)){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay fila, el artista existe; si no, false
        } catch (SQLException e){
            System.out.println("Error al obtener nombre del artista: " + e.getMessage());
        }
        return false;
    }
}
