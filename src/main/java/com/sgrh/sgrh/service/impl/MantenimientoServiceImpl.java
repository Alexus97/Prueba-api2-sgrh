package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.entity.Mantenimiento;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.MantenimientoMapper;
import com.sgrh.sgrh.repository.MantenimientoRepository;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.service.MantenimientoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final HabitacionRepository habitacionRepository;
    private final EstadoRepository estadoRepository;
    private final MantenimientoMapper mantenimientoMapper;

    @Override
    public MantenimientoDTO crearMantenimiento(MantenimientoDTO mantenimientoDTO) {
        // Validación de llaves foráneas antes de guardar
        if (!habitacionRepository.existsById(mantenimientoDTO.idHabitacion())) {
            throw new ResourceNotFoundException("La Habitación con ID " + mantenimientoDTO.idHabitacion() + " no existe.");
        }
        if (!estadoRepository.existsById(mantenimientoDTO.idEstadoMantenimiento())) {
            throw new ResourceNotFoundException("El Estado con ID " + mantenimientoDTO.idEstadoMantenimiento() + " no existe.");
        }

        Mantenimiento mantenimiento = mantenimientoMapper.toEntity(mantenimientoDTO);
        Mantenimiento guardado = mantenimientoRepository.save(mantenimiento);
        return mantenimientoMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public MantenimientoDTO obtenerMantenimientoById(Integer idMantenimiento) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(idMantenimiento)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + idMantenimiento));
        return mantenimientoMapper.toDTO(mantenimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MantenimientoDTO> obtenerTodos() {
        return mantenimientoRepository.findAll()
                .stream()
                .map(mantenimientoMapper::toDTO)
                .toList();
    }

    @Override
    public MantenimientoDTO actualizarMantenimiento(Integer idMantenimiento, MantenimientoDTO mantenimientoDTO) {
        if (!mantenimientoRepository.existsById(idMantenimiento)) {
            throw new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + idMantenimiento);
        }
        if (!habitacionRepository.existsById(mantenimientoDTO.idHabitacion())) {
            throw new ResourceNotFoundException("La Habitación con ID " + mantenimientoDTO.idHabitacion() + " no existe.");
        }
        if (!estadoRepository.existsById(mantenimientoDTO.idEstadoMantenimiento())) {
            throw new ResourceNotFoundException("El Estado con ID " + mantenimientoDTO.idEstadoMantenimiento() + " no existe.");
        }

        Mantenimiento actualizado = mantenimientoMapper.toEntity(mantenimientoDTO);
        actualizado.setIdMantenimiento(idMantenimiento);
        Mantenimiento guardado = mantenimientoRepository.save(actualizado);
        return mantenimientoMapper.toDTO(guardado);
    }

    @Override
    public void eliminarMantenimiento(Integer idMantenimiento) {
        if (!mantenimientoRepository.existsById(idMantenimiento)) {
            throw new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + idMantenimiento);
        }
        mantenimientoRepository.deleteById(idMantenimiento);
    }
}