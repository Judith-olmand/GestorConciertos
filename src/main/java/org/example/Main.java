package org.example;

import org.example.Eliminar.*;
import org.example.CrearNuevo.*;
import org.example.Buscar.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())){

            System.out.println("Elija una opción:");
            System.out.println("1. Añadir un artista");
            System.out.println("2. EliminarArtista un artista");
            System.out.println("3. Listar todos los artistas");
            System.out.println("4. Añadir un concierto.");
            System.out.println("5. EliminarArtista un concierto.");
            System.out.println("6. Listar todos los conciertos.");
            System.out.println("7. Registrar venta de entradas.");
            System.out.println("8. Listar venta de entradas.");
            System.out.println("0. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    NuevoArtista.nuevoArtista(conn,sc);
                    break;
                case 2:
                    EliminarArtista.eliminarArtista(conn,sc);
                    break;
                case 3:
                    break;
                case 4:
                    NuevoConcierto.nuevoConcierto(conn,sc);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    System.out.println("Hasta pronto!!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}