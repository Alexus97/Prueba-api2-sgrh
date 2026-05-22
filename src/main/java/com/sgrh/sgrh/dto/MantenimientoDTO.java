package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MantenimientoDTO(
        Integer idMantenimiento,
        @NotNull(message = "La habitación no puede ser nula")
        Integer idHabitacion,
        @NotNull(message = "La fecha no puede ser nula")
        LocalDate fecha,
        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(max = 100, message = "La descripción no puede exceder 100 caracteres")
        String descripcion,
        @NotNull(message = "El estado no puede ser nulo")
        Integer idEstado
) {
}
