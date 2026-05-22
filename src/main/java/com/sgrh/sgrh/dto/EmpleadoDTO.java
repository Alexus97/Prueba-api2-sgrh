package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpleadoDTO(
        Integer idEmpleado,
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
        String nombre,
        @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
        String telefono,
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        @Size(max = 100, message = "El email no puede exceder 100 caracteres")
        String email
) {
}
