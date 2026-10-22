package ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import modelo.Pedido;
import servicios.ServicioPedidos;

/**
 * Menú de consola: punto de entrada de la aplicación.
 *
 * Estructura:
 *   - Menú principal: elige el módulo (cada funcionalidad del TPO).
 *   - Submenús: las operaciones de cada módulo.
 *
 * Módulos implementados:
 *   1. Gestión de pedidos (Merge Sort + Búsqueda Binaria)
 */
public class Menu {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static ServicioPedidos servicioPedidos = new ServicioPedidos();

    public static void main(String[] args) {
        precargarPedidos();

        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== TPO Programación III - Sistema de Logística ===");
            System.out.println("1. Gestión de pedidos");
            System.out.println("0. Salir");

            Integer opcion = leerEntero("Elegí una opción: ");
            if (opcion == null) {
                if (!SCANNER.hasNextLine()) break; // se terminó la entrada
                continue;
            }
            switch (opcion) {
                case 1:
                    menuPedidos();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        System.out.println("Fin del programa.");
    }

    // =====================================================================
    //  1. GESTIÓN DE PEDIDOS
    // =====================================================================

   
    private static void precargarPedidos() {
        servicioPedidos.registrarPedido(new Pedido(4, "Centro Oeste", LocalDate.of(20261, 10, 30), 5, 60));
        servicioPedidos.registrarPedido(new Pedido(1, "Centro Norte", LocalDate.of(2026, 10, 25), 3, 120));
        servicioPedidos.registrarPedido(new Pedido(5, "Centro Ribera", LocalDate.of(2026, 10, 22), 5, 150));
        servicioPedidos.registrarPedido(new Pedido(3, "Centro Este", LocalDate.of(2026, 10, 22), 3, 200));
        servicioPedidos.registrarPedido(new Pedido(2, "Centro Sur", LocalDate.of(2026, 10, 22), 5, 80));
    }

    /** Submenú del módulo de pedidos. Vuelve al menú principal con 0. */
    private static void menuPedidos() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("----------- GESTIÓN DE PEDIDOS -----------");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Ordenar por fecha límite");
            System.out.println("3. Ordenar por prioridad");
            System.out.println("4. Buscar pedido por id");
            System.out.println("5. Registrar nuevo pedido");
            System.out.println("0. Volver al menú principal");

            Integer opcion = leerEntero("Elegí una opción: ");
            if (opcion == null) {
                if (!SCANNER.hasNextLine()) return;
                continue;
            }
            switch (opcion) {
                case 1:
                    mostrarPedidos("Pedidos en orden de registro", servicioPedidos.listarPedidos());
                    break;
                case 2:
                    mostrarPedidos("Pedidos ordenados por fecha límite (empate: id menor)",
                            servicioPedidos.ordenarPorFechaLimite());
                    break;
                case 3:
                    mostrarPedidos("Pedidos ordenados por prioridad (empate: fecha más cercana, luego id menor)",
                            servicioPedidos.ordenarPorPrioridad());
                    break;
                case 4:
                    buscarPedido();
                    break;
                case 5:
                    registrarPedido();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void mostrarPedidos(String titulo, List<Pedido> pedidos) {
        System.out.println(titulo + ":");
        if (pedidos.isEmpty()) {
            System.out.println("  (no hay pedidos registrados)");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("  " + p);
        }
    }

    private static void buscarPedido() {
        Integer id = leerEntero("Ingresá el id del pedido: ");
        if (id == null) return;

        Pedido encontrado = servicioPedidos.buscarPorId(id);
        if (encontrado == null) {
            System.out.println("No se encontró ningún pedido con el id " + id);
        } else {
            System.out.println("Pedido encontrado:");
            System.out.println("  " + encontrado);
        }
    }

    private static void registrarPedido() {
        Integer id = leerEntero("Id del pedido: ");
        if (id == null) return;
        if (id <= 0) {
            System.out.println("El id debe ser un número entero positivo.");
            return;
        }

        String centro = leerTexto("Centro de destino: ");
        if (centro == null || centro.isEmpty()) {
            System.out.println("El centro de destino no puede estar vacío.");
            return;
        }

        String textoFecha = leerTexto("Fecha límite (AAAA-MM-DD): ");
        if (textoFecha == null) return;
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(textoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida: usá el formato AAAA-MM-DD (por ejemplo 2026-10-25).");
            return;
        }

        Integer prioridad = leerEntero("Prioridad (mayor número = más urgente): ");
        if (prioridad == null) return;
        if (prioridad < 0) {
            System.out.println("La prioridad no puede ser negativa.");
            return;
        }

        String textoPeso = leerTexto("Peso en kg: ");
        if (textoPeso == null) return;
        double peso;
        try {
            peso = Double.parseDouble(textoPeso.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido: ingresá un número.");
            return;
        }
        if (peso <= 0) {
            System.out.println("El peso debe ser mayor que 0.");
            return;
        }

        // El servicio valida el id duplicado e informa el resultado
        servicioPedidos.registrarPedido(new Pedido(id, centro, fecha, prioridad, peso));
    }

    // =====================================================================
    //  Lectura de datos (compartida por todos los módulos)
    // =====================================================================

    /** Lee una línea de texto. Devuelve null si se terminó la entrada. */
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        if (!SCANNER.hasNextLine()) {
            System.out.println();
            return null;
        }
        return SCANNER.nextLine().trim();
    }

    /** Lee un número entero. Si el usuario escribe algo que no es un número, avisa y devuelve null. */
    private static Integer leerEntero(String mensaje) {
        String texto = leerTexto(mensaje);
        if (texto == null) return null;
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: se esperaba un número entero.");
            return null;
        }
    }
}
