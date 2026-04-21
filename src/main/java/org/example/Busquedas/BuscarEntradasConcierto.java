package org.example.Busquedas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BuscarEntradasConcierto {
    public static boolean entradasIDConcierto(Connection conexion, int idConcierto) {
        String consultaConciertos = "SELECT * FROM ENTRADA WHERE ID_C = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consultaConciertos)){
            ps.setInt(1, idConcierto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e){
            System.out.println("Error al consultar los datos");
        }
        return false;
    }
}
