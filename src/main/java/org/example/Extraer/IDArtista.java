package org.example.Extraer;

import java.sql.*;

public class IDArtista {
    public static int idArtista(Connection conexion, String nombre){
        String consultaIdArtista = "SELECT ID_A FROM ARTISTA WHERE NOMBRE_A LIKE ?";

        try (PreparedStatement ps = conexion.prepareStatement(consultaIdArtista)){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e){
            System.out.println("Error al obtener el id del artista: " + e.getMessage());
        }
        return 0;
    }
}
