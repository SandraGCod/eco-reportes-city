package com.java.eco_reportes_city.dto;

import com.java.eco_reportes_city.entity.EstadoReporte;

import java.time.LocalDateTime;

public record ReporteResponse(
        Long id,
        String descripcion,
        String fotoUrl,
        Double latitud,
        Double longitud,
        String direccion,
        EstadoReporte estado,
        LocalDateTime fechaCreacion,
        Long usuarioId,
        String usuarioNombre
) {}