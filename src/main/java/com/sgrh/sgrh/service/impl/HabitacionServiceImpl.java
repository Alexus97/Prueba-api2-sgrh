package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.entity.Habitacion;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.HabitacionMapper;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.repository.SucursalHotelRepository;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.service.HabitacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final SucursalHotelRepository sucursalHotelRepository;
    private final EstadoRepository estadoRepository;
    private final HabitacionMapper habitacionMapper;

    @Override
    public HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO) {
        if (!sucursalHotelRepository.existsById(habitacionDTO.idSucursal())) {
            throw new ResourceNotFoundException("La Sucursal con ID " + habitacionDTO.idSucursal() + " no existe.");
        }
        if (!estadoRepository.existsById(habitacionDTO.idEstadoHabitacion())) {
            throw new ResourceNotFoundException("El Estado con ID " + habitacionDTO.idEstadoHabitacion() + " no existe.");
        }

        Habitacion habitacion = habitacionMapper.toEntity(habitacionDTO);
        Habitacion guardada = habitacionRepository.save(habitacion);
        return habitacionMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public HabitacionDTO obtenerHabitacionById(Integer idHabitacion) {
        Habitacion habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con ID: " + idHabitacion));
        return habitacionMapper.toDTO(habitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> obtenerTodas() {
        return habitacionRepository.findAll()
                .stream()
                .map(habitacionMapper::toDTO)
                .toList();
    }

    @Override
    public HabitacionDTO actualizarHabitacion(Integer idHabitacion, HabitacionDTO habitacionDTO) {
        if (!habitacionRepository.existsById(idHabitacion)) {
            throw new ResourceNotFoundException("Habitación no encontrada con ID: " + idHabitacion);
        }
        if (!sucursalHotelRepository.existsById(habitacionDTO.idSucursal())) {
            throw new ResourceNotFoundException("La Sucursal con ID " + habitacionDTO.idSucursal() + " no existe.");
        }
        if (!estadoRepository.existsById(habitacionDTO.idEstadoHabitacion())) {
            throw new ResourceNotFoundException("El Estado con ID " + habitacionDTO.idEstadoHabitacion() + " no existe.");
        }

        Habitacion actualizada = habitacionMapper.toEntity(habitacionDTO);
        actualizada.setIdHabitacion(idHabitacion);
        Habitacion guardada = habitacionRepository.save(actualizada);
        return habitacionMapper.toDTO(guardada);
    }

    @Override
    public void eliminarHabitacion(Integer idHabitacion) {
        if (!habitacionRepository.existsById(idHabitacion)) {
            throw new ResourceNotFoundException("Habitación no encontrada con ID: " + idHabitacion);
        }
        habitacionRepository.deleteById(idHabitacion);
    }
}