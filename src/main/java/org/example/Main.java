package org.example;

import org.example.Elimina.*;
import org.example.CrearNew.*;
import org.example.Listar.Artistas;
import org.example.Listar.Conciertos;
import org.example.Listar.VentaEntradas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean opcionValida = false;

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())){

            do {
                opcion = - 1;
                do {
                    System.out.println("Elija una opción:");
                    System.out.println("1. Añadir un artista");
                    System.out.println("2. Eliminar un artista");
                    System.out.println("3. Listar todos los artistas");
                    System.out.println("4. Añadir un concierto.");
                    System.out.println("5. Eliminar un concierto.");
                    System.out.println("6. Listar todos los conciertos.");
                    System.out.println("7. Registrar venta de entradas.");
                    System.out.println("8. Listar venta de entradas.");
                    System.out.println("0. Salir");
                    try {
                        opcion = sc.nextInt();
                        sc.nextLine();
                        opcionValida = true;
                    } catch (InputMismatchException e) {
                        System.out.println("La opción ha de ser numérica.");
                        sc.nextLine();
                    }
                } while (!opcionValida);

                switch (opcion) {
                    case 1:
                        NuevoArtista.nuevoArtista(conn,sc);
                        break;
                    case 2:
                        Artista.eliminarArtista(conn,sc);
                        break;
                    case 3:
                        Artistas.listarArtistas(conn);
                        break;
                    case 4:
                        NuevoConcierto.nuevoConcierto(conn,sc);
                        break;
                    case 5:
                        Concierto.eliminarConcierto(conn,sc);
                        break;
                    case 6:
                        Conciertos.listarConciertos(conn);
                        break;
                    case 7:
                        NuevaEntrada.nuevaEntrada(conn,sc);
                        break;
                    case 8:
                        VentaEntradas.listarEntradas(conn);
                        break;
                    case 0:
                        System.out.println("Hasta pronto!!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 0);

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}