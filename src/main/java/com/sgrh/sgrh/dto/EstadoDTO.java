package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
        Integer idEstado,
        @NotBlank(message = "El tipo no puede estar vacío")
        @Size(max = 30, message = "El tipo no puede exceder 30 caracteres")
        String tipo,
        @Size(max = 50, message = "La descripción no puede exceder 50 caracteres")
        String descripcion
) {
}
