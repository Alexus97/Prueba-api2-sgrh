package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ReservaHabitacionDTO;
import com.sgrh.sgrh.entity.ReservaHabitacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaHabitacionMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    @Mapping(source = "habitacion.idHabitacion", target = "idHabitacion")
    ReservaHabitacionDTO toDTO(ReservaHabitacion entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    @Mapping(source = "idHabitacion", target = "habitacion.idHabitacion")
    ReservaHabitacion toEntity(ReservaHabitacionDTO dto);
}
