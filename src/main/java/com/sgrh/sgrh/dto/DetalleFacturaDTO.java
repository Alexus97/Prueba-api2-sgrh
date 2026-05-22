package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetalleFacturaDTO(
        Integer idDetalle,
        @NotNull(message = "La factura no puede ser nula")
        Integer idFactura,
        Integer idServicio,
        Integer idPromocion,
        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser positiva")
        Integer cantidad,
        @NotNull(message = "El precio unitario no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
        BigDecimal precioUnitario
) {
}
