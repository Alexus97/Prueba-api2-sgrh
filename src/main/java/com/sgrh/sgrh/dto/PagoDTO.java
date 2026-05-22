package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagoDTO(
        Integer idPago,
        @NotNull(message = "La reserva no puede ser nula")
        Integer idReserva,
        @NotNull(message = "El empleado no puede ser nulo")
        Integer idEmpleado,
        @NotNull(message = "El método de pago no puede ser nulo")
        Integer idMetodoPago,
        @NotNull(message = "La fecha de pago no puede ser nula")
        LocalDate fechaPago,
        @NotNull(message = "El monto no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
        BigDecimal monto
) {
}
