public enum Moneda {
    // Definimos las constantes con sus parámetros
    ARS("Peso Argentino", "ARS", "$"),
    BOB("Boliviano", "BOB", "Bs"),
    BRL("Real Brasileño", "BRL", "R$"),
    CLP("Peso Chileno", "CLP", "CL$"),
    COP("Peso Colombiano", "COP", "COL$"),
    USD("Dólar Estadounidense", "USD", "US$"),
    EUR("Euro", "EUR", "€");

    private final String nombre;
    private final String codigo;
    private final String simbolo;

    Moneda(String nombre, String codigo, String simbolo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.simbolo = simbolo;
    }

    // Getters para acceder a los datos
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public String getSimbolo() { return simbolo; }
}