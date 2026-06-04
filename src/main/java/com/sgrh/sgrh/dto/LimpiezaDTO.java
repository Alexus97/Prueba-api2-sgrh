package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record LimpiezaDTO(
        @JsonIgnore
        Integer idLimpieza,

        @NotNull(message = "El ID de la habitación es obligatorio")
        Integer idHabitacion,

        @NotNull(message = "El ID del empleado es obligatorio")
        Integer idEmpleado,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotBlank(message = "El estado de la limpieza no puede estar vacío")
        @Size(max = 50, message = "El estado no puede exceder los 50 caracteres")
        String estado
) {
}