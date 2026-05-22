package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.CheckInDTO;
import com.sgrh.sgrh.entity.CheckIn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckInMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    CheckInDTO toDTO(CheckIn entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    CheckIn toEntity(CheckInDTO dto);
}
