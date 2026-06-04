package com.sgrh.sgrh.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record HabitacionDTO(
        @JsonIgnore
        Integer idHabitacion,

        @NotNull(message = "El ID de tipo es obligatorio")
        Integer idTipo,

        @NotNull(message = "El ID de la sucursal es obligatorio")
        Integer idSucursal,

        @NotNull(message = "El número de habitación es obligatorio")
        Integer numero,

        @NotNull(message = "La capacidad es obligatoria")
        Integer capacidad,

        @NotNull(message = "El precio por noche no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
        BigDecimal precioNoche,

        @NotNull(message = "El ID del estado es obligatorio")
        Integer idEstadoHabitacion
) {
}