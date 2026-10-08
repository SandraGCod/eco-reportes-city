package com.java.eco_reportes_city.dto;

public record PuntoReciclajeResponse(
        Long id,
        String nombre,
        String tipoMaterial,
        Double latitud,
        Double longitud,
        String direccion,
        String horario
) {}