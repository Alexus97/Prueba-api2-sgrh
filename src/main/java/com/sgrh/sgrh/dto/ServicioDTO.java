package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ServicioDTO(
        Integer idServicio,
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
        String nombre,
        @Size(max = 100, message = "La descripción no puede exceder 100 caracteres")
        String descripcion,
        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
        BigDecimal precio
) {
}
