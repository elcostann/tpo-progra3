package modelo;

public class Conexion {

    private Ubicacion origen;
    private Ubicacion destino;
    private double tiempoMinutos;
    private boolean habilitada;

    public Conexion(Ubicacion origen, Ubicacion destino, double tiempoMinutos) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoMinutos = tiempoMinutos;
        this.habilitada = true;
    }

    public Ubicacion getOrigen()      { return origen; }
    public Ubicacion getDestino()     { return destino; }
    public double getTiempoMinutos()  { return tiempoMinutos; }
    public boolean isHabilitada()     { return habilitada; }

    public void deshabilitar() { this.habilitada = false; }
    public void habilitar()    { this.habilitada = true; }
}