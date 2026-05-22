package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LimpiezaDTO(
        Integer idLimpieza,
        @NotNull(message = "La habitación no puede ser nula")
        Integer idHabitacion,
        @NotNull(message = "El empleado no puede ser nulo")
        Integer idEmpleado,
        @NotNull(message = "La fecha no puede ser nula")
        LocalDate fecha,
        @NotBlank(message = "El estado no puede estar vacío")
        @Size(max = 50, message = "El estado no puede exceder 50 caracteres")
        String estado
) {
}
