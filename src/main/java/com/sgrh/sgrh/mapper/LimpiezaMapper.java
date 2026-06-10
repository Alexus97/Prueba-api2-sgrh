package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.entity.Limpieza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LimpiezaMapper {

    @Mapping(source = "habitacion.idHabitacion", target = "idHabitacion")
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    LimpiezaDTO toDTO(Limpieza entity);

    @Mapping(target = "habitacion", ignore = true)
    @Mapping(target = "empleado", ignore = true)
    Limpieza toEntity(LimpiezaDTO dto);
}