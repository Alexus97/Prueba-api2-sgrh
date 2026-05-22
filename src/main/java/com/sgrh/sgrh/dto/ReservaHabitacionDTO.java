package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;

public record ReservaHabitacionDTO(
        Integer idReservaHabitacion,
        @NotNull(message = "La reserva no puede ser nula")
        Integer idReserva,
        @NotNull(message = "La habitación no puede ser nula")
        Integer idHabitacion
) {
}
