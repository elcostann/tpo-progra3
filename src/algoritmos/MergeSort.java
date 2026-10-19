package algoritmos;

import java.util.ArrayList;
import java.util.List;
import modelo.Pedido;

public class MergeSort {

    
    
    public static List<Pedido> ordenarPorFechaLimite(List<Pedido> pedidos) {
        List<Pedido> copia = copiar(pedidos);
        mergeSort(copia, 0, copia.size() - 1, "FECHA");
        return copia;
    }

    
    
    public static List<Pedido> ordenarPorPrioridad(List<Pedido> pedidos) {
        List<Pedido> copia = copiar(pedidos);
        mergeSort(copia, 0, copia.size() - 1, "PRIORIDAD");
        return copia;
    }
    
    
    
    public static List<Pedido> ordenarPorId(List<Pedido> pedidos) {
        List<Pedido> copia = copiar(pedidos);
        mergeSort(copia, 0, copia.size() - 1, "ID");
        return copia;
    }


    /** Paso recursivo: divide y luego fusiona. */
    private static void mergeSort(List<Pedido> lista, int inicio, int fin, String criterio) {
        if (inicio >= fin) return; // caso base: 0 ó 1 elemento, ya está ordenado

        int medio = inicio + (fin - inicio) / 2;
        mergeSort(lista, inicio, medio, criterio);
        mergeSort(lista, medio + 1, fin, criterio);
        merge(lista, inicio, medio, fin, criterio);
    }
    /** Fusiona las dos mitades lista[inicio..medio] y lista[medio+1..fin]. */
    private static void merge(List<Pedido> lista, int inicio, int medio, int fin, String criterio) {
        // Copiamos las dos mitades en arreglos auxiliares
        int tamIzq = medio - inicio + 1;
        int tamDer = fin - medio;

        Pedido[] izq = new Pedido[tamIzq];
        Pedido[] der = new Pedido[tamDer];

        for (int i = 0; i < tamIzq; i++) izq[i] = lista.get(inicio + i);
        for (int j = 0; j < tamDer; j++) der[j] = lista.get(medio + 1 + j);
        int i = 0, j = 0, k = inicio;
        while (i < tamIzq && j < tamDer) {
            if (comparar(izq[i], der[j], criterio) <= 0) {
                lista.set(k++, izq[i++]); // izq va primero (o son iguales: estabilidad)
            } else {
                lista.set(k++, der[j++]);
            }}
        // Copiamos lo que sobró
        while (i < tamIzq) lista.set(k++, izq[i++]);
        while (j < tamDer) lista.set(k++, der[j++]);
    }


    /**
     * Compara p1 y p2 según el criterio.
     * Devuelve negativo si p1 va antes, positivo si va después, 0 si son equivalentes.
     */
    static int comparar(Pedido p1, Pedido p2, String criterio) {
        switch (criterio) {
            case "FECHA": {
                int cmp = p1.getFechaLimite().compareTo(p2.getFechaLimite());
                if (cmp != 0) return cmp;
                return Integer.compare(p1.getId(), p2.getId()); // desempate: id menor
            }
            case "PRIORIDAD": {
                int cmp = Integer.compare(p2.getPrioridad(), p1.getPrioridad()); // desc
                if (cmp != 0) return cmp;
                cmp = p1.getFechaLimite().compareTo(p2.getFechaLimite());
                if (cmp != 0) return cmp;
                return Integer.compare(p1.getId(), p2.getId());
            }
            default: // "ID"
                return Integer.compare(p1.getId(), p2.getId());
        }
    }

    /** Crea una copia de la lista para no modificar la original. */
    private static List<Pedido> copiar(List<Pedido> lista) {
        return new ArrayList<>(lista);
    }

}
