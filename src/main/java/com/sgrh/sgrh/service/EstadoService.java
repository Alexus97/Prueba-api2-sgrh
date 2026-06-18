package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.EstadoDTO;

public interface EstadoService {
    EstadoDTO crearEstado(EstadoDTO estadoDTO);

    EstadoDTO obtenerEstadoById(Integer idEstado);

    EstadoDTO obtenerEstadoByTipo(String tipo);

    List<EstadoDTO> obtenerTodos();

    EstadoDTO actualizarEstado(Integer idEstado, EstadoDTO estadoDTO);

    void eliminarEstado(Integer idEstado);
}