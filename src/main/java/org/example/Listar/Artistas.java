package org.example.Listar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Artistas {
    public static void listarArtistas(Connection conexion){
        try (Statement st = conexion.createStatement()){
            String consultarArtistas = "SELECT ID_A, NOMBRE_A, GENERO_MUSICAL, PAIS_ORIGEN" +
                    " FROM ARTISTA";
            ResultSet rs = st.executeQuery(consultarArtistas);
            if(!rs.next()){
                System.out.println("No se encontraron conciertos");
            } else {
                System.out.printf("%-5s %-15s %-20s %-15s%n", "ID", "NOMBRE" , "GÉNERO MUSICAL", "PAIS DE ORIGEN");
                System.out.println("-".repeat(57));
                do {
                    System.out.printf("%-5s %-15s %-20s %-15s%n",
                            rs.getInt(1) ,
                            rs.getString(2) ,
                            rs.getString(3) ,
                            rs.getString(4));
                    //System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " +
                    //rs.getString(3) +  ", " + rs.getString(4));
                } while (rs.next());
            }
        }catch (SQLException e) {
            System.out.println("Error al listar los artistas");
        }
    }
}
