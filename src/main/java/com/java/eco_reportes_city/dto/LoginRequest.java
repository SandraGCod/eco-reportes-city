package com.java.eco_reportes_city.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String correo, @NotBlank String password) {}