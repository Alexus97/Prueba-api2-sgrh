package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.entity.Mantenimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MantenimientoMapper {

    // Al salir a DTO: Extrae los IDs de los objetos anidados perfectamente
    @Mapping(source = "habitacion.idHabitacion", target = "idHabitacion")
    @Mapping(source = "estado.idEstado", target = "idEstadoMantenimiento")
    MantenimientoDTO toDTO(Mantenimiento entity);

    // Al ir a Entidad: Ignoramos los objetos completos para que no interfieran
    // con las consultas limpias (findById) que hace tu MantenimientoServiceImpl
    @Mapping(target = "habitacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Mantenimiento toEntity(MantenimientoDTO dto);
}