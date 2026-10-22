package algoritmos;

import java.util.ArrayList;
import java.util.List;
import modelo.RedVial;

/**
 * Ruta de menor tiempo entre dos ubicaciones con el algoritmo de Dijkstra (técnica Greedy).
 * Complejidad: O(n²) usando la matriz de adyacencia (n = cantidad de ubicaciones).
 */
public class Dijkstra {

    /** Tiempo total de la última ruta calculada (INF si no hubo ruta). */
    private static double ultimoTiempoTotal = RedVial.INF;

    /**
     * Calcula la ruta más rápida de origen a destino y muestra las decisiones del algoritmo.
     * Devuelve la lista de ids del camino (origen ... destino), o una lista vacía si no hay ruta.
     */
    public static List<Integer> calcularRuta(RedVial red, int origen, int destino) {
        ultimoTiempoTotal = RedVial.INF;
        List<Integer> camino = new ArrayList<>();
        int n = red.getCantidadUbicaciones();

        // Validar que los ids existan
        if (origen < 0 || origen >= n || destino < 0 || destino >= n) {
            System.out.println("  Ids inválidos (válidos: 0 a " + (n - 1) + ").");
            return camino;
        }

        double[][] matriz = red.getMatrizAdyacencia();
        double[] dist = new double[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];

        // 1) Inicialización
        for (int i = 0; i < n; i++) {
            dist[i] = RedVial.INF;
            predecesor[i] = -1;
        }
        dist[origen] = 0;

        // 2) En cada vuelta se fija definitivamente un nodo
        for (int paso = 1; paso <= n; paso++) {

            // 2a) Elección golosa: el no visitado con menor distancia
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visitado[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }
            if (u == -1 || dist[u] == RedVial.INF) {
                System.out.println("  Paso " + paso + ": no quedan ubicaciones alcanzables.");
                break;
            }
            visitado[u] = true;
            System.out.println("  Paso " + paso + ": se elige " + nombre(red, u)
                    + " (distancia definitiva " + dist[u] + " min)");

            if (u == destino) {
                break; // ya tenemos la distancia definitiva del destino
            }

            // 2b) Relajación: ¿pasar por u mejora la distancia a algún vecino?
            for (int v = 0; v < n; v++) {
                if (!visitado[v] && matriz[u][v] != RedVial.INF) {
                    double nueva = dist[u] + matriz[u][v];
                    if (nueva < dist[v]) {
                        System.out.println("      " + nombre(red, u) + " -> " + nombre(red, v)
                                + ": mejora de " + texto(dist[v]) + " a " + nueva + " min");
                        dist[v] = nueva;
                        predecesor[v] = u;
                    }
                }
            }
        }

        // 3) Si el destino quedó en INF, no hay ruta
        if (dist[destino] == RedVial.INF) {
            return camino; // lista vacía
        }

        // 4) Reconstrucción: del destino hacia atrás, insertando al principio
        for (int actual = destino; actual != -1; actual = predecesor[actual]) {
            camino.add(0, actual);
        }
        ultimoTiempoTotal = dist[destino];
        return camino;
    }

    /** Tiempo total en minutos de la última ruta calculada. */
    public static double getUltimoTiempoTotal() {
        return ultimoTiempoTotal;
    }

    /** Nombre de la ubicación para mostrar en pantalla. */
    private static String nombre(RedVial red, int id) {
        return red.getUbicacion(id).getNombre();
    }

    /** Muestra "INF" en vez de "Infinity". */
    private static String texto(double valor) {
        return (valor == RedVial.INF) ? "INF" : String.valueOf(valor);
    }
}