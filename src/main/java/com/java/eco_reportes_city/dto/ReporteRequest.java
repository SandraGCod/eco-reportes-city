package com.java.eco_reportes_city.dto;

import jakarta.validation.constraints.*;

public record ReporteRequest(
        @NotBlank @Size(max = 500) String descripcion,
        String fotoUrl,
        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Double latitud,
        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Double longitud,
        String direccion
) {}