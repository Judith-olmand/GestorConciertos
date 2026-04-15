package org.example.ExtraerID;

import java.sql.*;

public class IDArtistaDisponible {
    public static int idArtistaDisponible(Connection conexion){
        try (Statement statement = conexion.createStatement()){
            String query = "SELECT MAX(ID_A) FROM ARTISTA";
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e){
            System.out.println("Error al obtener el id del artista: " + e.getMessage());
        }
        return 0;
    }
}
