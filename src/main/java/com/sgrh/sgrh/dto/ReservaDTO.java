package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaDTO(
        Integer idReserva,
        @NotNull(message = "El cliente no puede ser nulo")
        Integer idCliente,
        @NotNull(message = "La fecha de reserva no puede ser nula")
        LocalDate fechaReserva,
        @NotNull(message = "La fecha de entrada no puede ser nula")
        LocalDate fechaEntrada,
        @NotNull(message = "La fecha de salida no puede ser nula")
        LocalDate fechaSalida,
        @NotNull(message = "El estado de la reserva no puede ser nulo")
        Integer idEstado
) {
}
