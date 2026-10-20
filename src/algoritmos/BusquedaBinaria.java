package algoritmos;

import java.util.List;
import modelo.Pedido;

/**
 * Búsqueda de un pedido por id mediante Búsqueda Binaria (recursiva).
 *
 * Requisito previo: la lista DEBE estar ordenada por id ascendente.
 * (Se ordena antes con MergeSort.ordenarPorId)
 */
public class BusquedaBinaria {

    /**
     * Busca un pedido por id en una lista ya ordenada por id.
     * Devuelve el pedido encontrado, o null si no existe.
     */
    public static Pedido buscarPorId(List<Pedido> pedidosOrdenadosPorId, int id) {
        return buscar(pedidosOrdenadosPorId, id, 0, pedidosOrdenadosPorId.size() - 1);
    }

    /** Paso recursivo: descarta la mitad donde no puede estar el id. */
    private static Pedido buscar(List<Pedido> lista, int id, int inicio, int fin) {
        
        if (inicio > fin) {
            return null;
        }

        int medio = inicio + (fin - inicio) / 2;
        int idMedio = lista.get(medio).getId();

        if (idMedio == id) {
            return lista.get(medio);       // encontrado
        } else if (id < idMedio) {
            return buscar(lista, id, inicio, medio - 1);  // buscar a la izquierda
        } else {
            return buscar(lista, id, medio + 1, fin);     // buscar a la derecha
        }
    }
}