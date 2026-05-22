package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.entity.Habitacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {
    @Mapping(source = "tipoHabitacion.idTipo", target = "idTipo")
    @Mapping(source = "sucursalHotel.idSucursal", target = "idSucursal")
    @Mapping(source = "estadoHabitacion.idEstado", target = "idEstado")
    HabitacionDTO toDTO(Habitacion entity);

    @Mapping(source = "idTipo", target = "tipoHabitacion.idTipo")
    @Mapping(source = "idSucursal", target = "sucursalHotel.idSucursal")
    @Mapping(source = "idEstado", target = "estadoHabitacion.idEstado")
    Habitacion toEntity(HabitacionDTO dto);
}
