package org.example.Busquedas;

import java.sql.Connection;

public class BuscarConciertosArtista {
    public static boolean conciertosIdArtista(Connection conexion, int idArtista) {
        String consultaConciertos = "SELECT * FROM CONCIERTO S WHERE ID_A = ?";
        return Consultas.consultas(conexion,consultaConciertos,idArtista);

    }


}
