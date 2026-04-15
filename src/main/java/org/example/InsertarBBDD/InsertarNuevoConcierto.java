package org.example.InsertarBBDD;

import org.example.ExtraerID.IDArtista;
import org.example.Objetos.Concierto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarNuevoConcierto {
    public static void nuevoConcierto(Connection conexion, Concierto concierto) {
        int idArtista = IDArtista.idArtista(conexion, concierto.getArtista());
        String insert = "INSERT INTO concierto (id_c ,id_a, fecha, lugar, precio) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(insert)) {
            ps.setInt(1, concierto.getId());
            ps.setInt(2, idArtista);
            ps.setObject(3, concierto.getFecha());
            ps.setString(4, concierto.getLugar());
            ps.setDouble(5, concierto.getPrecioEntrada());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar artista: " + e.getMessage());
        }
    }
}
