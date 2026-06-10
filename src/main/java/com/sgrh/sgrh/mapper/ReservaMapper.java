package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ReservaDTO;
import com.sgrh.sgrh.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservaMapper {

    @Mapping(source = "cliente.idCliente", target = "idCliente")
    @Mapping(source = "estadoReserva.idEstado", target = "idEstadoReserva")
    ReservaDTO toDTO(Reserva entity);

    @Mapping(source = "idCliente", target = "cliente.idCliente")
    @Mapping(source = "idEstadoReserva", target = "estadoReserva.idEstado")
    Reserva toEntity(ReservaDTO dto);
}