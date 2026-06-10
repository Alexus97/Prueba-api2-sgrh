package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.HabitacionDTO;

public interface HabitacionService {
    HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO);
    HabitacionDTO obtenerHabitacionById(Integer idHabitacion);
    List<HabitacionDTO> obtenerTodas();
    HabitacionDTO actualizarHabitacion(Integer idHabitacion, HabitacionDTO habitacionDTO);
    void eliminarHabitacion(Integer idHabitacion);
    List<HabitacionDTO> obtenerPorEstado(String tipoEstado);
    HabitacionDTO cambiarEstado(Integer idHabitacion, Integer idEstado);
}