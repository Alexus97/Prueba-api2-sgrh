package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.entity.Habitacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {

    @Mapping(source = "sucursalHotel.idSucursal", target = "idSucursal")
    @Mapping(source = "estado.idEstado", target = "idEstadoHabitacion")
    HabitacionDTO toDTO(Habitacion entity);

    @Mapping(source = "idSucursal", target = "sucursalHotel.idSucursal")
    @Mapping(source = "idEstadoHabitacion", target = "estado.idEstado")
    Habitacion toEntity(HabitacionDTO dto);
}