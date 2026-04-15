package org.example.CrearNuevo;

import org.example.Buscar.BuscarNombreArtista;
import org.example.ExtraerID.IDConciertoDisponible;
import org.example.InsertarBBDD.InsertarNuevoConcierto;
import org.example.Objetos.Concierto;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class NuevoConcierto {
    public static void nuevoConcierto (Connection conexion, Scanner sc) {
        System.out.println("Indica el nombre del Artista del concierto");
        String nombre = sc.nextLine();
        // Llama a buscar el nombre del artista en la base de datos
        boolean existeArtista = BuscarNombreArtista.nombreArtista(conexion, nombre);
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
            System.out.println("Indica el precio de las entradas");
            double precio = sc.nextDouble();
            sc.nextLine();

            int idConcierto = IDConciertoDisponible.idConciertoDisponible(conexion) + 1;

            Concierto nuevoConcierto = new Concierto(idConcierto, nombre, fechasql, lugar, precio);

            InsertarNuevoConcierto.nuevoConcierto(conexion, nuevoConcierto);
        } else {
            System.out.println("No existe el artista en la base de datos.");
        }
    }
}
