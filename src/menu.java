import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== Sistema de Gestión Logística ===");
            System.out.println("1. Gestión de pedidos");
            System.out.println("2. Ruta más rápida entre dos ubicaciones");
            System.out.println("3. Selección de cargas prioritarias");
            System.out.println("4. Rutas mínimas entre todas las ubicaciones");
            System.out.println("5. Recorrido con múltiples entregas");
            System.out.println("6. Planificación de entregas con menor costo");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    System.out.println("En desarrollo.");
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
