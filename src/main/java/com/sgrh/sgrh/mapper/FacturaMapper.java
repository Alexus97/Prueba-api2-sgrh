package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.FacturaDTO;
import com.sgrh.sgrh.entity.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    FacturaDTO toDTO(Factura entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    Factura toEntity(FacturaDTO dto);
}
