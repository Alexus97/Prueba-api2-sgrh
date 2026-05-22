package com.sgrh.sgrh.dto;

import jakarta.validation.constraints.NotNull;

public record AsignacionRolDTO(
        Integer idAsignacion,
        @NotNull(message = "El empleado no puede ser nulo")
        Integer idEmpleado,
        @NotNull(message = "El rol no puede ser nulo")
        Integer idRol
) {
}
