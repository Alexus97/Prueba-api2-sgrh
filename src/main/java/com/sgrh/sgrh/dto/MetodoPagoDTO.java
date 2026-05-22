package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MetodoPagoDTO(
        Integer idMetodoPago,
        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(max = 50, message = "La descripción no puede exceder 50 caracteres")
        String descripcion
) {
}
