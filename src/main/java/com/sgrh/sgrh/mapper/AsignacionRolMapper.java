package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.AsignacionRolDTO;
import com.sgrh.sgrh.entity.AsignacionRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AsignacionRolMapper {
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    @Mapping(source = "rol.idRol", target = "idRol")
    AsignacionRolDTO toDTO(AsignacionRol entity);

    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    @Mapping(source = "idRol", target = "rol.idRol")
    AsignacionRol toEntity(AsignacionRolDTO dto);
}
