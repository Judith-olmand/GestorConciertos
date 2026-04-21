package org.example.InsertarBBDD;

import org.example.Objetos.Entrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NuevaEntrada {
    public static void nuevaEntrada(Connection conexion, Entrada entrada) {

        String insert = "INSERT INTO ENTRADA (ID_E, ID_C, COMPRADOR, CANTIDAD, FECHA_COMPRA) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(insert)) {
            ps.setInt(1, entrada.getId());
            ps.setInt(2, entrada.getIdConcierto());
            ps.setString(3, entrada.getComprador());
            ps.setInt(4, entrada.getCantidad());
            ps.setObject(5, entrada.getFechaCompra());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar artista: " + e.getMessage());
        }
    }
}
