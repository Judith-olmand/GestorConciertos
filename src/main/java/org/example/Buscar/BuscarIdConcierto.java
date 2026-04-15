package org.example.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarIdConcierto {
    public static boolean idConcierto(Connection conexion, int idConcierto) {
        String consultaIdConcierto = "SELECT ID_C FROM  WHERE ID_C = ?";

        try (PreparedStatement ps = conexion.prepareStatement(consultaIdConcierto)){
            ps.setInt(1, idConcierto);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay fila, el concierto existe; si no, false
        } catch (SQLException e){
            System.out.println("Error al obtener id del concierto: " + e.getMessage());
        }
        return false;
    }
}
