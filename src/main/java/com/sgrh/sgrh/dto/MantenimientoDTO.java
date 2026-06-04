package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record MantenimientoDTO(
        @JsonIgnore
        Integer idMantenimiento,

        @NotNull(message = "El ID de la habitación es obligatorio")
        Integer idHabitacion,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @Size(max = 100, message = "La descripción no puede exceder los 100 caracteres")
        String descripcion,

        @NotNull(message = "El ID del estado es obligatorio")
        Integer idEstadoMantenimiento
) {
}