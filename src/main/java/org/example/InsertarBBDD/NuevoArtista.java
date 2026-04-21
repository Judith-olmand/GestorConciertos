package org.example.InsertarBBDD;

import org.example.Objetos.Artista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NuevoArtista {
    public static void nuevoArtista(Connection conexion, Artista artista) {
        String insert = "INSERT INTO artista (id_a ,nombre_a, genero_musical, pais_origen) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(insert)) {
            ps.setInt(1, artista.getId());
            ps.setString(2, artista.getNombre());
            ps.setString(3, artista.getGeneroMusical());
            ps.setString(4, artista.getPaisOrigen());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar artista: " + e.getMessage());
        }
    }
}
