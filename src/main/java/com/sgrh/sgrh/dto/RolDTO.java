package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RolDTO(
        Integer idRol,
        @NotBlank(message = "El nombre del rol no puede estar vacío")
        @Size(max = 50, message = "El nombre del rol no puede exceder 50 caracteres")
        String nombreRol
) {
}
