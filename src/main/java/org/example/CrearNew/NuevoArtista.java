package org.example.CrearNew;

import org.example.Extraer.IDArtistaDisponible;
import org.example.InsertarBBDD.NuevoArtistaBBDD;
import org.example.Objetos.Artista;
import org.example.Busquedas.BuscarNombreArtista;

import java.sql.Connection;
import java.util.Scanner;

public class NuevoArtista {
    public static void nuevoArtista (Connection conexion, Scanner sc){
        boolean existe;
        String nombre;
        /**
         * Pido el nombre del artista y compruebo si existe en la base de datos
         * si existe se repite el bucle y vuelve a pedir el nombre
         */
        do {
            System.out.println("Introduce el nombre del artista");
            nombre = sc.nextLine();
            existe = BuscarNombreArtista.nombreArtista(conexion,nombre);
            if(existe){
                System.out.println("El artista ya existe");
            }
        }while(existe);

        /**
         * si no existe pido el resto de datos
         */
        System.out.println("Introduce el genero musical");
        String genero = sc.nextLine();
        System.out.println("Introduce el pais de origen");
        String pais = sc.nextLine();

        /**
         * Consulto el máximo id de la lista artista y sumo 1 para el nuevo
         */
        int idArtista = IDArtistaDisponible.idArtistaDisponible(conexion) + 1;

        Artista nuevoArtista = new Artista(idArtista, nombre, genero, pais);
        NuevoArtistaBBDD.nuevoArtista(conexion, nuevoArtista);
        System.out.println("Artista añadido con éxito");
    }
}
