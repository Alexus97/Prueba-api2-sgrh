package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ReservaDTO;
import com.sgrh.sgrh.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mapping(source = "cliente.idCliente", target = "idCliente")
    @Mapping(source = "estadoReserva.idEstado", target = "idEstado")
    ReservaDTO toDTO(Reserva entity);

    @Mapping(source = "idCliente", target = "cliente.idCliente")
    @Mapping(source = "idEstado", target = "estadoReserva.idEstado")
    Reserva toEntity(ReservaDTO dto);
}
