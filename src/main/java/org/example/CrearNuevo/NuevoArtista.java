package org.example.CrearNuevo;

import org.example.ExtraerID.IDArtistaDisponible;
import org.example.InsertarBBDD.InsertarNuevoArtista;
import org.example.Objetos.Artista;

import java.sql.Connection;
import java.util.Scanner;

public class NuevoArtista {
    public static void nuevoArtista (Connection conexion, Scanner sc){
        System.out.println("Introduce el nombre del artista");
        String nombre = sc.nextLine();
        System.out.println("Introduce el genero musical");
        String genero = sc.nextLine();
        System.out.println("Introduce el pais de origen");
        String pais = sc.nextLine();

        int idArtista = IDArtistaDisponible.idArtistaDisponible(conexion) + 1;

        Artista nuevoArtista = new Artista(idArtista, nombre, genero, pais);
        InsertarNuevoArtista.nuevoArtista(conexion, nuevoArtista);
    }
}
