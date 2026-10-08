package com.java.eco_reportes_city.dto;

import com.java.eco_reportes_city.entity.EstadoReporte;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoRequest(@NotNull EstadoReporte estado) {}