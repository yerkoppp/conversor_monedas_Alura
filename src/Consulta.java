import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {

    private String monedaBase;
    private String monedaDestino;
    private double cantidad;
    private double cantidadResultado;
    private String resultadoString;
    private LocalDateTime fechaConsulta;

    public Consulta(String monedaBase, String monedaDestino,
                    double cantidad, double cantidadResultado,
                    String resultadoString) {
        this.monedaBase = monedaBase;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
        this.cantidadResultado = cantidadResultado;
        this.resultadoString = resultadoString;
        this.fechaConsulta = LocalDateTime.now();
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getCantidadResultado() {
        return cantidadResultado;
    }

    public String getResultadoString() {
        return resultadoString;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public String getFechaConsultaFormateada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaConsulta.format(formato);
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s => %.2f %s\n",
                getFechaConsultaFormateada(),
                cantidad, monedaBase,
                cantidadResultado, monedaDestino);
    }
}
