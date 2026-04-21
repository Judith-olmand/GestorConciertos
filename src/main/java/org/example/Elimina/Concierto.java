package org.example.Elimina;

import org.example.Busquedas.EntradasConcierto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Concierto {
    public static void eliminarConcierto(Connection conexion, Scanner sc) {
        System.out.println("Introduce el id del concierto que desea eliminar:");
        int idConcierto = sc.nextInt();
        sc.nextLine();

        boolean existeIdConcierto = org.example.Busquedas.Concierto.idConcierto(conexion, idConcierto);

        if (existeIdConcierto){
            boolean existenEntradasVendidas = EntradasConcierto.entradasIDConcierto(conexion, idConcierto); //comprobar si existen entradas vendidas
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
