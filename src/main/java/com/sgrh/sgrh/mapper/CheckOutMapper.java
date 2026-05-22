package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.CheckOutDTO;
import com.sgrh.sgrh.entity.CheckOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckOutMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    CheckOutDTO toDTO(CheckOut entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    CheckOut toEntity(CheckOutDTO dto);
}
