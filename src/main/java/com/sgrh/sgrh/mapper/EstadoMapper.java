package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.EstadoDTO;
import com.sgrh.sgrh.entity.Estado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    EstadoDTO toDTO(Estado entity);
    Estado toEntity(EstadoDTO dto);
}
