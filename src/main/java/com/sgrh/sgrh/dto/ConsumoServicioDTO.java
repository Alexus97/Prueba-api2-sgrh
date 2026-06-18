package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ConsumoServicioDTO(

        @JsonIgnore
        Integer idConsumo,

        @NotNull(message = "El ID de la reserva es obligatorio")
        Integer idReserva,

        @NotNull(message = "El ID del servicio es obligatorio")
        Integer idServicio,

        @NotNull(message = "La fecha del consumo no puede ser nula")
        LocalDate fecha,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad consumida debe ser al menos 1")
        Integer cantidad
) {
}