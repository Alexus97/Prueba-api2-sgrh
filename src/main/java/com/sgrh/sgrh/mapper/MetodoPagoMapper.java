package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.MetodoPagoDTO;
import com.sgrh.sgrh.entity.MetodoPago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {
    MetodoPagoDTO toDTO(MetodoPago entity);
    MetodoPago toEntity(MetodoPagoDTO dto);
}
