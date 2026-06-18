package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.MetodoPagoDTO;

public interface MetodoPagoService {
    MetodoPagoDTO crearMetodoPago(MetodoPagoDTO dto);
    MetodoPagoDTO obtenerPorId(Integer id);
    List<MetodoPagoDTO> obtenerTodos();
    MetodoPagoDTO actualizarMetodoPago(Integer id, MetodoPagoDTO dto);
    void eliminarMetodoPago(Integer id);
}