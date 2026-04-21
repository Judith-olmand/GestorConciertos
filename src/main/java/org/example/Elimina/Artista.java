package org.example.Elimina;

import org.example.Busquedas.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Artista {
    public static void eliminarArtista(Connection conexion, Scanner sc) {
        boolean numeroCorrecto = false;
        int idArtista = 0;

        do {
            System.out.println("Introduce el id del artista que desea eliminar:");
            try {
                idArtista = sc.nextInt();
                sc.nextLine();
                numeroCorrecto = true;
            } catch (InputMismatchException e) {
                System.out.println("El id del artista ha de ser numérico.");
            }
        }while (!numeroCorrecto);



        boolean existeIdArtista = BuscarIDArtista.idArtista(conexion, idArtista);

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
