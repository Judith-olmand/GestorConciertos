package org.example.Busquedas;

import java.sql.Connection;


public class EntradasConcierto {
    public static boolean entradasIDConcierto(Connection conexion, int idConcierto) {
        String consultaConciertos = "SELECT * FROM ENTRADA WHERE ID_C = ?";
        return Consultas.consultas(conexion,consultaConciertos,idConcierto);
    }
}
