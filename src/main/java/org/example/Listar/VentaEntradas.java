package org.example.Listar;

import oracle.net.jdbc.TNSAddress.SOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentaEntradas {
    public static void listarEntradas(Connection conexion){
        try (Statement st = conexion.createStatement()){
            String consultarEntradas = "SELECT ID_E, NOMBRE_A || ' / ' || " +
                    "TO_CHAR(FECHA, 'DD-MM-YYYY') AS CONCIERTO, " +
                    "COMPRADOR, CANTIDAD, TO_CHAR(FECHA_COMPRA, 'DD-MM-YYYY') " +
                    "FROM ENTRADA JOIN CONCIERTO USING(ID_C) " +
                    "JOIN ARTISTA USING(ID_A) " +
                    "ORDER BY ID_C";
            ResultSet rs = st.executeQuery(consultarEntradas);

            if(!rs.next()){
                System.out.println("No se encontraron entradas");
            } else {
                System.out.printf("%-3s %-30s %-13s %-25s %-15s%n",
                        "ID", "CONCIERTO", "COMPRADOR", "CANTIDAD DE ENTRADAS" , "FECHA DE COMPRA");
                System.out.println("-".repeat(97));
                do {
                    System.out.printf("%-3s %-30s %-13s %-25s %-15s%n",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5));
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
