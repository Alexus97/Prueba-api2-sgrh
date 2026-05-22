package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.RolDTO;
import com.sgrh.sgrh.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO toDTO(Rol entity);
    Rol toEntity(RolDTO dto);
}
