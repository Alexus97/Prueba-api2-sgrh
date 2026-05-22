package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.PromocionDTO;
import com.sgrh.sgrh.entity.Promocion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromocionMapper {
    PromocionDTO toDTO(Promocion entity);
    Promocion toEntity(PromocionDTO dto);
}
