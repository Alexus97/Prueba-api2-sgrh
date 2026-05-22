package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.entity.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    EmpleadoDTO toDTO(Empleado entity);
    Empleado toEntity(EmpleadoDTO dto);
}
