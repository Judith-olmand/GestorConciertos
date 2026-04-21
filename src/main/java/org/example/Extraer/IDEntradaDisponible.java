package org.example.Extraer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IDEntradaDisponible {
    public static int idEntradaDisponible(Connection conexion){
        try (Statement statement = conexion.createStatement()){
            String query = "SELECT MAX(ID_E) FROM ENTRADA";
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()){
                return 0;
            } else {
                return rs.getInt(1);
            }
        } catch (SQLException e){
            System.out.println("Error al obtener el id de la entrada: " + e.getMessage());
        }
        return 0;
    }
}
