package org.example.CrearNew;

import org.example.Busquedas.BuscarIDConcierto;
import org.example.Busquedas.BuscarCantidadEntradasComprador;
import org.example.Extraer.IDEntradaDisponible;
import org.example.Extraer.NombreArtista;
import org.example.InsertarBBDD.NuevaEntradaBBDD;
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
        int cantidadPosibles = 0;

        System.out.println("Escriba su nombre");
        String nombreComprador = sc.nextLine();

        Conciertos.listarConciertos(conexion);

        /**
         * Mientras no exista el concierto seguirá pidiendo el número
         */
        while (!idConciertoExiste) {
            do {
                System.out.println("Introduce el número del concierto");
                try {
                    idConcierto = sc.nextInt();
                    sc.nextLine();
                    // Si es un número válido ponemos true
                    numeroCorrecto = true;
                    // Buscamos el concierto
                    idConciertoExiste = BuscarIDConcierto.idConcierto(conexion, idConcierto);
                    // si no existe idConciertoExiste lo dejamos a false como está
                    if (!idConciertoExiste) {
                        System.out.println("El número de concierto no existe");
                    } else {
                        /**
                         * si existe compruebo si el comprador ya ha comprado para ese mismo concierto
                         * si ya ha comprado cantidadPosibles es 10 - la cantidad
                         * si no ha comprado cantidadPosibles es 10 - 0 (que es lo que devuelve)
                         */
                        cantidadPosibles = 10 - BuscarCantidadEntradasComprador.verificarCompradorConcierto(conexion,nombreComprador,idConcierto);
                        /**
                         * si la cantidad posible es 0 o menos numeroCorrecto lo pasamos a false
                         * y vuelve a pedir otro id de concierto
                         */
                        if (cantidadPosibles <= 0){
                            System.out.println("Has comprado el máximo de entradas para el concierto.");
                            numeroCorrecto = false;
                        }
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
            //Pido el número de entradas
            System.out.println("Introduce el número de entradas");
            try {
                cantidadEntrada = sc.nextInt();
                sc.nextLine();
                /**
                 * si es 0 o menor
                 * o si la cantidad + las posibles es mayor que 10
                 * numeroCorrecto se mantiene a false para repetir el bucle
                 */
                if (cantidadEntrada <= 0){
                    System.out.println("La cantidad de entradas debe ser mayor a 0");
                } else if (cantidadEntrada + cantidadPosibles > 10){
                    System.out.println("La cantidad de entradas máxima por comprador es de 10.");
                } else {
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
        NuevaEntradaBBDD.nuevaEntrada(conexion,nuevaEntrada);
    }
}
