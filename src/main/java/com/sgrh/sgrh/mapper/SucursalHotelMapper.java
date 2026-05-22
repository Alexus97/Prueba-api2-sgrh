package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.SucursalHotelDTO;
import com.sgrh.sgrh.entity.SucursalHotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SucursalHotelMapper {
    SucursalHotelDTO toDTO(SucursalHotel entity);
    SucursalHotel toEntity(SucursalHotelDTO dto);
}
