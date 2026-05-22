package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.entity.Limpieza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LimpiezaMapper {
    @Mapping(source = "habitacion.idHabitacion", target = "idHabitacion")
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    LimpiezaDTO toDTO(Limpieza entity);

    @Mapping(source = "idHabitacion", target = "habitacion.idHabitacion")
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    Limpieza toEntity(LimpiezaDTO dto);
}
