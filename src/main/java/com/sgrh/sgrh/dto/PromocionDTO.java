package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record PromocionDTO(
        @JsonIgnore                                               
        Integer idPromocion,

        @NotBlank(message = "La descripción de la promoción es obligatoria")
        @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
        String descripcion,

        @NotNull(message = "El porcentaje de descuento es obligatorio")
        @DecimalMin(value = "0.00", message = "El descuento mínimo permitido es 0.00%")
        @DecimalMax(value = "100.00", message = "El descuento máximo permitido es 100.00%")
        BigDecimal descuento,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio,

        @NotNull(message = "La fecha de fin es obligatoria")
        LocalDate fechaFin) {
}