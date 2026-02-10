import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record RespuestaApi(
        String result,
        @SerializedName("base_code") String codigoBase,
        @SerializedName("conversion_rates") Map<String, Double> tasasConversion) {}
