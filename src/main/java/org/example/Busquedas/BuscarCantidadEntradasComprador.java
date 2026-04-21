package org.example.Busquedas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarCantidadEntradasComprador {
    public static int verificarCompradorConcierto(Connection conexion, String comprador, int idConcierto){
        String consulta = "SELECT SUM(CANTIDAD) " +
                "FROM ENTRADA " +
                "WHERE UPPER(COMPRADOR) = UPPER(?) AND ID_C = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, comprador);
            ps.setInt(2, idConcierto);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            } else {
                return rs.getInt(1);
            }



        } catch (SQLException e) {
            System.out.println("Error al obtener el comprador concierto");
        }
        return 0;
    }
}
