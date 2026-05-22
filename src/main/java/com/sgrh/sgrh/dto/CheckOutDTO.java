package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CheckOutDTO(
        Integer idCheckOut,
        @NotNull(message = "La reserva no puede ser nula")
        Integer idReserva,
        @NotNull(message = "El empleado no puede ser nulo")
        Integer idEmpleado,
        @NotNull(message = "La fecha y hora no puede ser nula")
        LocalDateTime fechaHora
) {
}
