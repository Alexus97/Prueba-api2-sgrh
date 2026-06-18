package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record ReservaDTO(
        @JsonIgnore // Ignorado en el JSON de entrada ya que es Autoincremental en MySQL
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
        Integer idEstadoReserva // <-- Alineado perfectamente con tu tabla 'reserva'
) {
}