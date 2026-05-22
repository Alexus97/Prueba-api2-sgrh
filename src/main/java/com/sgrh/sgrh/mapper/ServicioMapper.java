package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ServicioDTO;
import com.sgrh.sgrh.entity.Servicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    ServicioDTO toDTO(Servicio entity);
    Servicio toEntity(ServicioDTO dto);
}
