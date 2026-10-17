package modelo;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private String descripcion;
    private LocalDate fechaLimite;
    private int prioridad;
    private double peso;

    public Pedido(int id, String descripcion, LocalDate fechaLimite, int prioridad, double peso) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.peso = peso;
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public int getPrioridad() { return prioridad; }
    public double getPeso() { return peso; }

    @Override
    public String toString() {
        return "Pedido #" + id + " | " + descripcion +
               " | Prioridad: " + prioridad +
               " | Fecha límite: " + fechaLimite +
               " | Peso: " + peso + " kg";
    }
}
