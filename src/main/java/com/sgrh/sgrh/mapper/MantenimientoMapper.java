package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.entity.Mantenimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MantenimientoMapper {

    @Mapping(source = "habitacion.idHabitacion", target = "idHabitacion")
    @Mapping(source = "estado.idEstado", target = "idEstadoMantenimiento")
    MantenimientoDTO toDTO(Mantenimiento entity);

    @Mapping(source = "idHabitacion", target = "habitacion.idHabitacion")
    @Mapping(source = "idEstadoMantenimiento", target = "estado.idEstado")
    Mantenimiento toEntity(MantenimientoDTO dto);
}