package org.example.CrearNew;

import org.example.Busquedas.Concierto;
import org.example.Extraer.IDEntradaDisponible;
import org.example.Extraer.NombreArtista;
import org.example.Listar.Conciertos;
import org.example.Objetos.Entrada;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NuevaEntrada {
    public static void nuevaEntrada (Connection conexion, Scanner sc){
        boolean idConciertoExiste = false;
        int idConcierto = 0;
        boolean numeroCorrecto = false;
        int cantidadEntrada = 0;

        System.out.println("Escriba su nombre");
        String nombreComprador = sc.nextLine();

        Conciertos.listarConciertos(conexion);

        while (!idConciertoExiste) {
            do {
                System.out.println("Introduce el número del concierto");
                try {
                    idConcierto = sc.nextInt();
                    sc.nextLine();
                    numeroCorrecto = true;
                    idConciertoExiste = Concierto.idConcierto(conexion, idConcierto);
                    if (!idConciertoExiste) {
                        System.out.println("El número de concierto no existe");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ha de ser numérico");
                    sc.nextLine();
                }
            }while(!numeroCorrecto);
        }
        String nombreArtista = NombreArtista.nombreArtista(conexion,idConcierto);

        numeroCorrecto = false;
        do {
            System.out.println("Introduce el número de entradas");
            try {
                cantidadEntrada = sc.nextInt();
                sc.nextLine();
                if (cantidadEntrada <= 0){
                    System.out.println("La cantidad de entradas debe ser mayor a 0");
                    numeroCorrecto = false;
                } else if (cantidadEntrada > 10){
                    System.out.println("La cantidad de entradas máxima por comprador es de 10.");
                    numeroCorrecto = false;
                }else {
                    numeroCorrecto = true;
                }
            } catch (InputMismatchException r) {
                System.out.println("Ha de ser numérico.");
                sc.nextLine();
            }
        } while (!numeroCorrecto);

        int idEntrada = IDEntradaDisponible.idEntradaDisponible(conexion) + 1;
        Entrada nuevaEntrada = new Entrada(idEntrada,idConcierto,nombreArtista,nombreComprador,cantidadEntrada,LocalDate.now());
        System.out.println("Compra de entradas añadida con éxito");
        org.example.InsertarBBDD.NuevaEntrada.nuevaEntrada(conexion,nuevaEntrada);
    }
}
