package com.sgrh.sgrh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;

public record ReservaHabitacionDTO(
        @JsonIgnore // Ignorado en el JSON de entrada ya que es Autoincremental en MySQL
        Integer idReservaHabitacion,
        @NotNull(message = "El ID de la reserva no puede ser nulo")
        Integer idReserva,
        @NotNull(message = "El ID de la habitación no puede ser nulo")
        Integer idHabitacion
) {}