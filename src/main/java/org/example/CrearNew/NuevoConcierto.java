package org.example.CrearNew;

import org.example.Busquedas.NombreArtista;
import org.example.Extraer.IDConciertoDisponible;
import org.example.Objetos.Concierto;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NuevoConcierto {
    public static void nuevoConcierto (Connection conexion, Scanner sc) {
        double precio;

        System.out.println("Indica el nombre del Artista del concierto");
        String nombre = sc.nextLine();
        // Llama a buscar el nombre del artista en la base de datos
        boolean existeArtista = NombreArtista.nombreArtista(conexion, nombre);
        if (existeArtista) {
            System.out.println("Indica la fecha del concierto (YYYY-MM-DD)");
            String fecha = sc.nextLine();
            LocalDate fechasql = null;
            // Mientras la fecha sea nula sigue pideindola
            while (fechasql == null) {
                try {
                    fechasql = LocalDate.parse(fecha);
                } catch (DateTimeParseException e) {
                    System.out.println(" Formato incorrecto --> (YYYY-MM-DD)");
                }
            }

            System.out.println("Indica el lugar del concierto");
            String lugar = sc.nextLine();
            boolean numeroCorrecto = false;
            do {
                precio = 0;
                System.out.println("Indica el precio de las entradas");
                try {
                    precio = sc.nextDouble();
                    sc.nextLine();
                    numeroCorrecto = true;
                        if (precio < 0) {
                            System.out.println("El precio de las entradas no puede ser negativo");
                            numeroCorrecto = false;
                        }
                }catch (InputMismatchException e){
                    System.out.println("Ha de ser numérico");
                    sc.nextLine();
                }
            }while (!numeroCorrecto);

            int idConcierto = IDConciertoDisponible.idConciertoDisponible(conexion) + 1;

            Concierto nuevoConcierto = new Concierto(idConcierto, nombre, fechasql, lugar, precio);

            org.example.InsertarBBDD.NuevoConcierto.nuevoConcierto(conexion, nuevoConcierto);
        } else {
            System.out.println("No existe el artista en la base de datos.");
        }
    }
}
