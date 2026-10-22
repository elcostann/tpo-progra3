package modelo;

public class Ubicacion {

    private int id;
    private String nombre;

    public Ubicacion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId()       { return id; }
    public String getNombre(){ return nombre; }
}