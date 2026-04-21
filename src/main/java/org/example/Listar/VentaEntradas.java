package org.example.Listar;

import oracle.net.jdbc.TNSAddress.SOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentaEntradas {
    public static void listarEntradas(Connection conexion){
        try (Statement st = conexion.createStatement()){
            String consultarEntradas = "SELECT ID_E, ID_C, " +
                    "COMPRADOR, CANTIDAD, FECHA_COMPRA " +
                    "FROM ENTRADA " +
                    "ORDER BY ID_C";
            ResultSet rs = st.executeQuery(consultarEntradas);

            if(!rs.next()){
                System.out.println("No se encontraron entradas");
            } else {
                System.out.printf("%-13s %-15s %-20s %-10s %-15s%n",
                        "ID_ENTRADA", "ID_CONCIERTO", "COMPRADOR", "CANTIDAD" , "FECHA");
                System.out.println("-".repeat(57));
                do {
                    System.out.printf("%-13s %-15s %-20s %-10s %-15s%n",
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDate(5));
//                  System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " +
//                        rs.getString(3) +  ", " + rs.getString(4) +
//                        ", " + rs.getString(5));
                }while(rs.next());
            }
        }catch (SQLException e) {
            System.out.println("Error al listar las entradas");
        }
    }
}
