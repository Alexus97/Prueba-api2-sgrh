package com.sgrh.sgrh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SucursalHotelDTO(
        @JsonIgnore
        Integer idSucursal,
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
        String nombre,
        @NotBlank(message = "La dirección no puede estar vacía")
        @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
        String direccion,
        @NotBlank(message = "La ciudad no puede estar vacía")
        @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
        String ciudad
) {
}
