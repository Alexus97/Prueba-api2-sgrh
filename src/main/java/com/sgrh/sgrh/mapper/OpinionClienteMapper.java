package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.OpinionClienteDTO;
import com.sgrh.sgrh.entity.OpinionCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OpinionClienteMapper {
    @Mapping(source = "cliente.idCliente", target = "idCliente")
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    OpinionClienteDTO toDTO(OpinionCliente entity);

    @Mapping(source = "idCliente", target = "cliente.idCliente")
    @Mapping(source = "idReserva", target = "reserva.idReserva")
    OpinionCliente toEntity(OpinionClienteDTO dto);
}
