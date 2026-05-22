package com.sgrh.sgrh.service;

import java.util.List;

import com.sgrh.sgrh.dto.HabitacionDTO;

public interface HabitacionService {
    HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO);

    HabitacionDTO obtenerHabitacionById(Integer idHabitacion);

    HabitacionDTO actualizarHabitacion(Integer idHabitacion, HabitacionDTO habitacionDTO);

    void eliminarHabitacion(Integer idHabitacion);

    List<HabitacionDTO> obtenerHabitacionesBySucursal(Integer idSucursal);

    List<HabitacionDTO> obtenerHabitacionesByEstado(Integer idEstado);

    List<HabitacionDTO> obtenerTodas();
}
