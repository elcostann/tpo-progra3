package servicios;

import algoritmos.Dijkstra;
import java.util.List;
import modelo.RedVial;

/**
 * Cálculo de rutas sobre la red vial.
 * Usa Dijkstra (Greedy) para la ruta más rápida entre dos ubicaciones.
 */
public class ServicioRutas {

    /**
     * Calcula la ruta más rápida de origen a destino y la muestra con nombres y tiempo total.
     * Si no hay ruta, lo informa.
     * Devuelve la lista de ids del camino (vacía si no hay ruta).
     */
    public List<Integer> calcularRutaMinima(RedVial red, int origen, int destino) {
        List<Integer> camino = Dijkstra.calcularRuta(red, origen, destino);

        if (camino.isEmpty()) {
            System.out.println("No existe una ruta disponible entre esas ubicaciones.");
        } else {
            System.out.println("Ruta más rápida: " + caminoConNombres(red, camino));
            System.out.println("Tiempo total: " + Dijkstra.getUltimoTiempoTotal() + " min");
        }
        return camino;
    }

    /** Convierte [0, 1, 3] en los nombres de las ubicaciones. */
    private String caminoConNombres(RedVial red, List<Integer> camino) {
        String texto = "";
        for (int i = 0; i < camino.size(); i++) {
            if (i > 0) texto += " -> ";
            texto += red.getUbicacion(camino.get(i)).getNombre();
        }
        return texto;
    }
}