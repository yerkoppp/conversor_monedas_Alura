import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ServicioMoneda {
    private final Gson gson = new Gson();
    private Map<String, Double> tasasFiltradas;

    private final String API_KEY = System.getenv("API_KEY_EXCHANGE");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Extraemos los códigos directamente del Enum
    private static final Set<String> PERMITIDAS = java.util.Arrays.stream(Moneda.values())
            .map(Moneda::getCodigo)
            .collect(Collectors.toSet());
    public ServicioMoneda(){
        try {
            inicializarTasas();
        } catch (Exception e) {
            System.err.println("[!] Alerta: No se pudieron cargar las tasas en tiempo real.");
            System.err.println("Motivo: " + e.getMessage());
        }
    }
    public void inicializarTasas(){
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("Error: La variable de entorno API_KEY_EXCHANGE no está configurada.");
        }
        String url = BASE_URL + API_KEY + "/latest/USD";

        try {
            HttpClient cliente = HttpClient.newHttpClient();
            // Creando la solicitud
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            RespuestaApi data = gson.fromJson(respuesta.body(), RespuestaApi.class);
            this.tasasFiltradas = filtrarTasas(data.tasasConversion());

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la API: " + e.getMessage());
        }
    }

    public double convertir(double cantidad, String desde, String hacia){
        if (tasasFiltradas == null) throw new IllegalStateException("Tasas no cargadas");

        // Verificamos que existan las monedas antes de operar
        if (!tasasFiltradas.containsKey(desde) || !tasasFiltradas.containsKey(hacia)) {
            throw new IllegalArgumentException("Moneda no soportada");
        }

        return  (tasasFiltradas.get(hacia)/
                tasasFiltradas.get(desde)) * cantidad;
    }

    public Map<String, Double> filtrarTasas(Map<String, Double> todasLasTasas) {

        return todasLasTasas.entrySet().stream()
                .filter(entry -> PERMITIDAS.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String obtenerResultado(double cantidad, String desde, String hacia) {
        double resultado = convertir(cantidad, desde, hacia);

        // Para buscar el símbolo, usamos el Enum
        Moneda mOrigen = Moneda.valueOf(desde);
        Moneda mDestino = Moneda.valueOf(hacia);

        return String.format("""
            
            +----------------------------------------------+
            |             RESULTADO DE CONVERSIÓN          |
            +----------------------------------------------+
              Origen:  %s %.2f (%s)
              Destino: %s %.2f (%s)
            +----------------------------------------------+
            """,
                mOrigen.getSimbolo(), cantidad, mOrigen.getNombre(),
                mDestino.getSimbolo(), resultado, mDestino.getNombre());
    }

}
