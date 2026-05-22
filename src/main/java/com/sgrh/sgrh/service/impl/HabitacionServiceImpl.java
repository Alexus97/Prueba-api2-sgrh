package com.sgrh.sgrh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.HabitacionMapper;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.service.HabitacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitacionServiceImpl implements HabitacionService {
    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;

    @Override
    public HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO) {
        var habitacion = habitacionMapper.toEntity(habitacionDTO);
        var habitacionGuardada = habitacionRepository.save(habitacion);
        return habitacionMapper.toDTO(habitacionGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public HabitacionDTO obtenerHabitacionById(Integer idHabitacion) {
        var habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada"));
        return habitacionMapper.toDTO(habitacion);
    }

    @Override
    public HabitacionDTO actualizarHabitacion(Integer idHabitacion, HabitacionDTO habitacionDTO) {
        var habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada"));

        var habitacionActualizada = habitacionMapper.toEntity(habitacionDTO);
        habitacionActualizada.setIdHabitacion(idHabitacion);
        var habitacionGuardada = habitacionRepository.save(habitacionActualizada);
        return habitacionMapper.toDTO(habitacionGuardada);
    }

    @Override
    public void eliminarHabitacion(Integer idHabitacion) {
        if (!habitacionRepository.existsById(idHabitacion)) {
            throw new ResourceNotFoundException("Habitación no encontrada");
        }
        habitacionRepository.deleteById(idHabitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> obtenerHabitacionesBySucursal(Integer idSucursal) {
        return habitacionRepository.findBySucursalHotelIdSucursal(idSucursal)
                .stream()
                .map(habitacionMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> obtenerHabitacionesByEstado(Integer idEstado) {
        return habitacionRepository.findByEstadoHabitacionIdEstado(idEstado)
                .stream()
                .map(habitacionMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> obtenerTodas() {
        return habitacionRepository.findAll()
                .stream()
                .map(habitacionMapper::toDTO)
                .toList();
    }
}
