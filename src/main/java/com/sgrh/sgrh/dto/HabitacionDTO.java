package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record HabitacionDTO(
        Integer idHabitacion,
        @NotNull(message = "El tipo de habitación no puede ser nulo")
        Integer idTipo,
        @NotNull(message = "La sucursal no puede ser nula")
        Integer idSucursal,
        @NotNull(message = "El número no puede ser nulo")
        @Positive(message = "El número debe ser positivo")
        Integer numero,
        @NotNull(message = "La capacidad no puede ser nula")
        @Positive(message = "La capacidad debe ser positiva")
        Integer capacidad,
        @NotNull(message = "El precio noche no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio noche debe ser mayor a 0")
        BigDecimal precioNoche,
        @NotNull(message = "El estado no puede ser nulo")
        Integer idEstado
) {
}
