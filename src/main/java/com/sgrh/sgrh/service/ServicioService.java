package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.ServicioDTO;

public interface ServicioService {
    ServicioDTO crearServicio(ServicioDTO servicioDTO);

    ServicioDTO obtenerServicioById(Integer idServicio);

    List<ServicioDTO> obtenerTodos();

    ServicioDTO actualizarServicio(Integer idServicio, ServicioDTO servicioDTO);

    void eliminarServicio(Integer idServicio);
}