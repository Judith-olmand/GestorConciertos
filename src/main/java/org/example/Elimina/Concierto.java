package org.example.Elimina;

import org.example.Busquedas.BuscarIDConcierto;
import org.example.Busquedas.BuscarEntradasConcierto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Concierto {
    public static void eliminarConcierto(Connection conexion, Scanner sc) {
        int idConcierto = -1;
        boolean numeroValido;

        do {
            numeroValido = false;
            System.out.println("Introduce el id del concierto que desea eliminar:");
            try {
                idConcierto = sc.nextInt();
                sc.nextLine();
                numeroValido = true;
            } catch (InputMismatchException e) {
                System.out.println("La opción ha de ser numérica.");
                sc.nextLine();
            }
        }while(!numeroValido);


        boolean existeIdConcierto = BuscarIDConcierto.idConcierto(conexion, idConcierto);

        if (existeIdConcierto){
            boolean existenEntradasVendidas = BuscarEntradasConcierto.entradasIDConcierto(conexion, idConcierto); //comprobar si existen entradas vendidas
            if (existenEntradasVendidas){
                System.out.println("Existen entradas de ese concierto. No se puede eliminar");
            } else {
                String eliminarConcierto = "DELETE FROM CONCIERTO WHERE ID_C = ? ";

                try (PreparedStatement ps = conexion.prepareStatement(eliminarConcierto)){
                    ps.setInt(1, idConcierto);
                    ps.executeUpdate();
                    System.out.println("Concierto eliminado con éxito");
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el concierto." + e.getMessage());
                }
            }
        } else {
            System.out.println("No existe el concierto en la base de datos.");
        }
    }
}
