package com.java.eco_reportes_city.dto;

import jakarta.validation.constraints.*;

public record PuntoReciclajeRequest(
        @NotBlank @Size(max = 150) String nombre,
        @NotBlank @Size(max = 50) String tipoMaterial,
        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Double latitud,
        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Double longitud,
        String direccion,
        String horario
) {}