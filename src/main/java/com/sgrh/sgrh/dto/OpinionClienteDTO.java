package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OpinionClienteDTO(
        Integer idOpinion,
        @NotNull(message = "El cliente no puede ser nulo")
        Integer idCliente,
        @NotNull(message = "La reserva no puede ser nula")
        Integer idReserva,
        String comentario,
        @NotNull(message = "La calificación no puede ser nula")
        @Min(value = 1, message = "La calificación mínima es 1")
        @Max(value = 5, message = "La calificación máxima es 5")
        Integer calificacion,
        @NotNull(message = "La fecha no puede ser nula")
        LocalDate fecha
) {
}
