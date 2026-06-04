package com.sgrh.sgrh.service;

import java.util.List;
import java.time.LocalDate;
import com.sgrh.sgrh.dto.LimpiezaDTO;

public interface LimpiezaService {
    LimpiezaDTO crearLimpieza(LimpiezaDTO limpiezaDTO);
    LimpiezaDTO obtenerLimpiezaById(Integer idLimpieza);
    List<LimpiezaDTO> obtenerTodas();
    LimpiezaDTO actualizarLimpieza(Integer idLimpieza, LimpiezaDTO limpiezaDTO);
    void eliminarLimpieza(Integer idLimpieza);
    
    // Métodos para soportar las búsquedas de tu Repository
    List<LimpiezaDTO> obtenerPorHabitacion(Integer idHabitacion);
    List<LimpiezaDTO> obtenerPorFecha(LocalDate fecha);
    List<LimpiezaDTO> obtenerPorEstado(String estado);
}