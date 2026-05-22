package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.PagoDTO;
import com.sgrh.sgrh.entity.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    @Mapping(source = "metodoPago.idMetodoPago", target = "idMetodoPago")
    PagoDTO toDTO(Pago entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    @Mapping(source = "idMetodoPago", target = "metodoPago.idMetodoPago")
    Pago toEntity(PagoDTO dto);
}
