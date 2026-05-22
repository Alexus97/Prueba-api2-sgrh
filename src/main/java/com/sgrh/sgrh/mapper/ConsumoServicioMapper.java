package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ConsumoServicioDTO;
import com.sgrh.sgrh.entity.ConsumoServicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumoServicioMapper {
    @Mapping(source = "reserva.idReserva", target = "idReserva")
    @Mapping(source = "servicio.idServicio", target = "idServicio")
    ConsumoServicioDTO toDTO(ConsumoServicio entity);

    @Mapping(source = "idReserva", target = "reserva.idReserva")
    @Mapping(source = "idServicio", target = "servicio.idServicio")
    ConsumoServicio toEntity(ConsumoServicioDTO dto);
}
