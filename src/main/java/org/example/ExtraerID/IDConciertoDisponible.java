package org.example.ExtraerID;

import java.sql.*;

public class IDConciertoDisponible {
    public static int idConciertoDisponible(Connection conexion){
        try (Statement statement = conexion.createStatement()){
            String query = "SELECT MAX(ID_C) FROM CONCIERTO";
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e){
            System.out.println("Error al obtener el id del concierto: " + e.getMessage());
        }
        return 0;
    }



}
