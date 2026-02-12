import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static List<Consulta> consultas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ServicioMoneda service = new ServicioMoneda();
        boolean continuar = true;

        System.out.println(" ".repeat(14) + "CONVERSOR DE MONEDAS" + " ".repeat(14));

        while (continuar) {
            mostrarEncabezadoMenu();
            System.out.println("   (1) Convertir moneda");
            System.out.println("   (2) Ver historial de consultas");
            System.out.println("   (0) Salir");
            System.out.print("\nSeleccione una opción: ");
            String opcionPrincipal = sc.nextLine(); // Leemos como String para evitar errores de buffer

            switch (opcionPrincipal) {
                case "1" -> ejecutarConversion(service);
                case "2" -> {
                    mostrarHistorial();
                    pausarYContinuar();
                }
                case "0" -> continuar = false;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("Gracias por usar el conversor. ¡Adiós!");
    }

    private static void ejecutarConversion(ServicioMoneda service) {

        try {
                mostrarMenuOpciones();
                // Selección Moneda Origen
                String monedaBase = solicitarMoneda("ORIGEN");
                if (monedaBase.equals("Salir")) return;
                // Selección Moneda Destino
                String monedaDestino = solicitarMoneda("DESTINO");
                if (monedaDestino.equals("Salir")) return;
                // Cantidad
                double cantidad = solicitarCantidad();

                // 2. Cálculo
                double cantidadResultado = service.convertir(cantidad, monedaBase, monedaDestino);
                String resultadoString = service.obtenerResultado(cantidad, monedaBase, monedaDestino);

                System.out.print(resultadoString);
                consultas.add(new Consulta(monedaBase, monedaDestino, cantidad, cantidadResultado, resultadoString));

                pausarYContinuar();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }

    }

    public static void mostrarEncabezadoMenu(){
        System.out.println("*".repeat(46));
        System.out.println("-".repeat(20)+" Menú "+"-".repeat(20)+"\n");
    }

    public static void mostrarMenuOpciones() {
        Moneda[] monedas = Moneda.values();
        for (int i = 0; i < monedas.length; i++) {
            System.out.printf("   (%d) %s - %s.%n",
                    (i + 1),
                    monedas[i].getCodigo(),
                    monedas[i].getNombre());
        }
        System.out.println("   (0) Salir.");
    }

    public static String obtenerMoneda(int opcion) {
        if (opcion == 0) return "Salir";

        Moneda[] monedas = Moneda.values();
        if (opcion > 0 && opcion <= monedas.length) {
            // Restamos 1 porque el array empieza en 0 y tu menú en 1
            return monedas[opcion - 1].getCodigo();
        }
        throw new IllegalStateException("Valor incorrecto: " + opcion);
    }

    public static String solicitarMoneda(String tipoMoneda) {
        while (true){
        System.out.println("-".repeat(46));
        System.out.println("Seleccione la moneda de "+tipoMoneda+" (o 0 para cancelar):");
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion == 0) return "Salir";

                return obtenerMoneda(opcion);

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número de la lista.");
            }catch (IllegalStateException e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
    }

    public static double solicitarCantidad() {
        while (true){
            System.out.println("-".repeat(46));
            System.out.println("Ingrese la cantidad a calcular:");
            try {
                double cantidad = Double.parseDouble(sc.nextLine().replace(",", "."));
                return cantidad;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número.");
            }catch (IllegalStateException e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
    }

    public static void mostrarHistorial() {
        if (consultas.isEmpty()) {
            System.out.println("\n[!] Historial vacío.");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("| %-20s | %-8s | %-8s | %-15s | %-15s |%n",
                "FECHA", "ORIGEN", "DESTINO", "CANTIDAD", "CONVERSIÓN");
        System.out.println("-".repeat(80));

        for (Consulta c : consultas) {
            System.out.printf("| %-20s | %-8s | %-8s | %-15.2f | %-15.2f |%n",
                    c.getFechaConsultaFormateada(),
                    c.getMonedaBase(),
                    c.getMonedaDestino(),
                    c.getCantidad(),
                    c.getCantidadResultado());
        }
        System.out.println("=".repeat(80));
    }

    public static void pausarYContinuar() {
        System.out.println("\nPresione Enter para continuar...");
        sc.nextLine();
    }
}