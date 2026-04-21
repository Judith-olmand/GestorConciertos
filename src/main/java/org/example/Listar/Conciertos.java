package org.example.Listar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conciertos {
    public static void listarConciertos(Connection conexion){
        try (Statement st = conexion.createStatement()){
            String consultarConciertos = "SELECT ID_C, NOMBRE_A, TO_CHAR(FECHA, 'DD-MM-YYYY' ), LUGAR" +
                    " FROM CONCIERTO JOIN ARTISTA USING(ID_A) " +
                    "ORDER BY FECHA";
            ResultSet rs = st.executeQuery(consultarConciertos);
            if(!rs.next()){
                System.out.println("No se encontraron conciertos");
            } else {
                System.out.printf("%-5s %-18s %-23s %-15s%n",
                        "ID", "NOMBRE ARTISTA" , "FECHA DEL CONCIERTO", "LUGAR");
                System.out.println("-".repeat(57));
                do {
                    System.out.printf("%-5s %-18s %-23s %-15s%n",
                            rs.getInt(1) ,
                            rs.getString(2) ,
                            rs.getString(3) ,
                            rs.getString(4));
//                System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " +
//                        rs.getString(3) +  ", " + rs.getString(4));
                } while (rs.next());
            }
        }catch (SQLException e) {
            System.out.println("Error al listar los conciertos");
        }
    }
}
