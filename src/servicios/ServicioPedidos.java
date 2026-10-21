package servicios;

import algoritmos.BusquedaBinaria;
import algoritmos.MergeSort;
import modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lista de pedidos: registro, ordenamiento y búsqueda.
 * Usa MergeSort (Divide y Conquista) para ordenar y
 * BusquedaBinaria para buscar por id.
 */
public class ServicioPedidos {

    /** Lista interna donde se guardan todos los pedidos registrados. */
    private List<Pedido> pedidos = new ArrayList<>();

    /**
     * Registra un nuevo pedido.
     * No permite ids duplicados: verifica antes de agregar.
     */
    public void registrarPedido(Pedido pedido) {
        // Usamos la búsqueda binaria para verificar si el id ya existe
        List<Pedido> ordenadosPorId = MergeSort.ordenarPorId(pedidos);
        Pedido existente = BusquedaBinaria.buscarPorId(ordenadosPorId, pedido.getId());

        if (existente != null) {
            System.out.println("  Ya existe un pedido con el id " + pedido.getId() + ". No se registró.");
        } else {
            pedidos.add(pedido);
            System.out.println("  Pedido #" + pedido.getId() + " registrado correctamente.");
        }
    }

    /**
     * Devuelve todos los pedidos tal como están guardados.
     */
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    /**
     * Devuelve los pedidos ordenados por fecha límite más cercana.
     * Empate: id menor primero.
     */
    public List<Pedido> ordenarPorFechaLimite() {
        return MergeSort.ordenarPorFechaLimite(pedidos);
    }

    /**
     * Devuelve los pedidos ordenados por prioridad descendente.
     * Empate de prioridad: fecha más cercana. Empate de fecha: id menor.
     */
    public List<Pedido> ordenarPorPrioridad() {
        return MergeSort.ordenarPorPrioridad(pedidos);
    }

    /**
     * Busca un pedido por id usando búsqueda binaria.
     * Primero ordena por id (requisito de la búsqueda binaria), luego busca.
     * Devuelve el pedido encontrado, o null si no existe.
     */
    public Pedido buscarPorId(int id) {
        List<Pedido> ordenadosPorId = MergeSort.ordenarPorId(pedidos);
        return BusquedaBinaria.buscarPorId(ordenadosPorId, id);
    }
}
