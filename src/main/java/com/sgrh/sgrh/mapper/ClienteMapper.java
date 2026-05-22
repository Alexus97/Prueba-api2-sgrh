package com.sgrh.sgrh.mapper;

import com.sgrh.sgrh.dto.ClienteDTO;
import com.sgrh.sgrh.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente entity);
    Cliente toEntity(ClienteDTO dto);
}
