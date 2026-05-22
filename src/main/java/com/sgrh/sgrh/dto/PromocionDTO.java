package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromocionDTO(
        Integer idPromocion,
        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(max = 100, message = "La descripción no puede exceder 100 caracteres")
        String descripcion,
        @NotNull(message = "El descuento no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El descuento debe ser mayor a 0")
        BigDecimal descuento,
        @NotNull(message = "La fecha de inicio no puede ser nula")
        LocalDate fechaInicio,
        @NotNull(message = "La fecha de fin no puede ser nula")
        LocalDate fechaFin
) {
}
