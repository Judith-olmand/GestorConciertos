package org.example.Eliminar;

import org.example.Buscar.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarArtista {
    public static void eliminarArtista(Connection conexion, Scanner sc) {
        System.out.println("Introduce el id del artista que desea eliminar:");
        int idArtista = sc.nextInt();
        sc.nextLine();

        boolean existeIdArtista = BuscarIdArtista.idArtista(conexion, idArtista);

        if (existeIdArtista){
            boolean existenConciertos = BuscarConciertosArtista.conciertosIdArtista(conexion, idArtista);
            if (existenConciertos){
                System.out.println("Existen conciertos del artista. No se puede eliminar");
            } else {
                String eliminarArtista = "DELETE FROM ARTISTA WHERE ID_A = ? ";

                try (PreparedStatement ps = conexion.prepareStatement(eliminarArtista)){
                    ps.setInt(1, idArtista);
                    ps.executeUpdate();
                    System.out.println("Artista eliminado con éxito");
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el artista." + e.getMessage());
                }
            }

        } else {
            System.out.println("No existe el artista en la base de datos.");
        }
    }
}
