package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insertar {
    public static void nuevoArtista(Connection conexion, Scanner sc) {
        System.out.println("Introduce el nombre del artista");
        String nombre = sc.nextLine();
        System.out.println("Introduce el genero musical");
        String genero = sc.nextLine();
        System.out.println("Introduce el pais de origen");
        String pais = sc.nextLine();

        int idArtista = Extraer.idArtistaDisponible(conexion) + 1;

        Artista nuevoArtista = new Artista(idArtista, nombre, genero, pais);
        Insertar.insertarArtistaBBDD(conexion, nuevoArtista);
    }


    public static void insertarArtistaBBDD(Connection conexion, Artista artista) {
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
