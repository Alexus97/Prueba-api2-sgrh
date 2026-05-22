package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ConsumoServicioDTO(
        Integer idConsumo,
        @NotNull(message = "La reserva no puede ser nula")
        Integer idReserva,
        @NotNull(message = "El servicio no puede ser nulo")
        Integer idServicio,
        @NotNull(message = "La fecha no puede ser nula")
        LocalDate fecha,
        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser positiva")
        Integer cantidad
) {
}
