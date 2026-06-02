package com.sgrh.sgrh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpleadoDTO(
        @JsonIgnore
        Integer idEmpleado, 

        @NotBlank(message = "El nombre completo no puede estar vacío")
        @Size(max = 100, message = "El nombre completo no puede exceder 100 caracteres")
        String nombreCompleto, 

        @NotBlank(message = "El tipo de documento es obligatorio")
        @Size(max = 10, message = "El tipo de documento es muy largo")
        String tipoDocumento,

        @NotBlank(message = "El número de documento es obligatorio")
        @Size(max = 20, message = "El número de documento no puede exceder 20 caracteres")
        String numeroDocumento,

        @NotBlank(message = "El cargo es obligatorio")
        @Size(max = 50, message = "El cargo no puede exceder 50 caracteres")
        String cargo,

        @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
        String telefono, 

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        @Size(max = 100, message = "El email no puede exceder 100 caracteres")
        String email
) {
}