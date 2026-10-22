package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la red vial como un grafo dirigido y ponderado.
 *   - Vértices: Ubicacion (depósito y centros).
 *   - Aristas: Conexion (tramo dirigido con su tiempo en minutos).
 *
 * Los ids de las ubicaciones deben ser 0, 1, 2, ... (consecutivos),
 * porque se usan como índices en la matriz de adyacencia.
 */
public class RedVial {

 /** Representa que no hay conexión directa entre dos ubicaciones. */
    public static final double INF = Double.POSITIVE_INFINITY;

    private List<Ubicacion> ubicaciones = new ArrayList<>();
    private List<Conexion> conexiones = new ArrayList<>();

    /** Agrega una ubicación (vértice) a la red. */
    public void agregarUbicacion(Ubicacion ubicacion) {
        ubicaciones.add(ubicacion);
    }

    /** Agrega una conexión dirigida (arista) a la red. */
    public void agregarConexion(Conexion conexion) {
        conexiones.add(conexion);
    }

    /**
     * Deshabilita la conexión de idOrigen a idDestino (simula un corte de ruta).
     * Devuelve true si la encontró, false si no existe.
     */
    public boolean deshabilitarConexion(int idOrigen, int idDestino) {
        Conexion c = buscarConexion(idOrigen, idDestino);
        if (c == null) return false;
        c.deshabilitar();
        return true;
    }

    /**
     * Vuelve a habilitar la conexión de idOrigen a idDestino.
     * Devuelve true si la encontró, false si no existe.
     */
    public boolean habilitarConexion(int idOrigen, int idDestino) {
        Conexion c = buscarConexion(idOrigen, idDestino);
        if (c == null) return false;
        c.habilitar();
        return true;
    }

    /** Recorre las conexiones y devuelve la que va de idOrigen a idDestino, o null. */
    private Conexion buscarConexion(int idOrigen, int idDestino) {
        for (Conexion c : conexiones) {
            if (c.getOrigen().getId() == idOrigen && c.getDestino().getId() == idDestino) {
                return c;
            }
        }
        return null;
    }

    /**
     * Arma la matriz de adyacencia con los tiempos de las conexiones HABILITADAS.
     *   - matriz[i][j] = tiempo de i a j si hay conexión habilitada.
     *   - matriz[i][j] = INF si no hay conexión (o está cortada).
     *   - matriz[i][i] = 0.
     * Complejidad: O(n² + m), con n ubicaciones y m conexiones.
     */
    public double[][] getMatrizAdyacencia() {
        int n = ubicaciones.size();
        double[][] matriz = new double[n][n];

        // 1) Inicializar: 0 en la diagonal, INF en el resto
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = (i == j) ? 0 : INF;
            }
        }

        // 2) Cargar los tiempos de las conexiones habilitadas
        for (Conexion c : conexiones) {
            if (c.isHabilitada()) {
                int i = c.getOrigen().getId();
                int j = c.getDestino().getId();
                matriz[i][j] = c.getTiempoMinutos();
            }
        }
        return matriz;
    }

    /** Devuelve la ubicación con ese id, o null si no existe. */
    public Ubicacion getUbicacion(int id) {
        for (Ubicacion u : ubicaciones) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public int getCantidadUbicaciones() { return ubicaciones.size(); }
    public List<Ubicacion> getUbicaciones() { return ubicaciones; }
    public List<Conexion> getConexiones() { return conexiones; }
}