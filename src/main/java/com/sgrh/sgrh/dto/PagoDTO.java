package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record PagoDTO(

        @JsonIgnore
        Integer idPago,

        @NotNull(message = "El ID de la reserva es obligatorio")
        Integer idReserva,

        @NotNull(message = "El ID del empleado es obligatorio")
        Integer idEmpleado,

        @NotNull(message = "El ID del método de pago es obligatorio")
        Integer idMetodoPago,

        @NotNull(message = "La fecha de pago no puede ser nula")
        LocalDate fechaPago,

        @NotNull(message = "El monto no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
        BigDecimal monto
) {
}