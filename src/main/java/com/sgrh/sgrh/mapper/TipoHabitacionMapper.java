package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.TipoHabitacionDTO;
import com.sgrh.sgrh.entity.TipoHabitacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoHabitacionMapper {
    TipoHabitacionDTO toDTO(TipoHabitacion entity);
    TipoHabitacion toEntity(TipoHabitacionDTO dto);
}
