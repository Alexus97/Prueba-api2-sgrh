package com.sgrh.sgrh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MetodoPagoDTO(

        @JsonIgnore
        Integer idMetodoPago,

        @NotBlank(message = "La descripción del método de pago no puede estar vacía")
        @Size(max = 50, message = "La descripción no puede exceder los 50 caracteres")
        String descripcion
) {
}