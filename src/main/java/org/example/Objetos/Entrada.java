package org.example.Objetos;

import java.time.LocalDate;

public class Entrada {
    private int id;
    private int idConcierto;
    private String concierto;
    private String comprador;
    private int cantidad;
    private LocalDate fechaCompra;

    public Entrada(int id, int idConcierto, String concierto, String comprador, int cantidad, LocalDate fechaCompra) {
        this.id = id;
        this.idConcierto = idConcierto;
        this.concierto = concierto;
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(int idConcierto) {
        this.idConcierto = idConcierto;
    }

    public String getConcierto() {
        return concierto;
    }

    public void setConcierto(String concierto) {
        this.concierto = concierto;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
