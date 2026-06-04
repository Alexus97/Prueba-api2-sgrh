package com.sgrh.sgrh.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.entity.Limpieza;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.LimpiezaMapper;
import com.sgrh.sgrh.repository.LimpiezaRepository;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.repository.EmpleadoRepository;
import com.sgrh.sgrh.service.LimpiezaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LimpiezaServiceImpl implements LimpiezaService {

    private final LimpiezaRepository limpiezaRepository;
    private final HabitacionRepository habitacionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final LimpiezaMapper limpiezaMapper;

    @Override
    public LimpiezaDTO crearLimpieza(LimpiezaDTO limpiezaDTO) {
        if (!habitacionRepository.existsById(limpiezaDTO.idHabitacion())) {
            throw new ResourceNotFoundException("La Habitación con ID " + limpiezaDTO.idHabitacion() + " no existe.");
        }
        if (!empleadoRepository.existsById(limpiezaDTO.idEmpleado())) {
            throw new ResourceNotFoundException("El Empleado con ID " + limpiezaDTO.idEmpleado() + " no existe.");
        }

        Limpieza limpieza = limpiezaMapper.toEntity(limpiezaDTO);
        Limpieza guardada = limpiezaRepository.save(limpieza);
        return limpiezaMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public LimpiezaDTO obtenerLimpiezaById(Integer idLimpieza) {
        Limpieza limpieza = limpiezaRepository.findById(idLimpieza)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Limpieza no encontrado con ID: " + idLimpieza));
        return limpiezaMapper.toDTO(limpieza);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimpiezaDTO> obtenerTodas() {
        return limpiezaRepository.findAll()
                .stream()
                .map(limpiezaMapper::toDTO)
                .toList();
    }

    @Override
    public LimpiezaDTO actualizarLimpieza(Integer idLimpieza, LimpiezaDTO limpiezaDTO) {
        if (!limpiezaRepository.existsById(idLimpieza)) {
            throw new ResourceNotFoundException("Registro de Limpieza no encontrado con ID: " + idLimpieza);
        }
        if (!habitacionRepository.existsById(limpiezaDTO.idHabitacion())) {
            throw new ResourceNotFoundException("La Habitación con ID " + limpiezaDTO.idHabitacion() + " no existe.");
        }
        if (!empleadoRepository.existsById(limpiezaDTO.idEmpleado())) {
            throw new ResourceNotFoundException("El Empleado con ID " + limpiezaDTO.idEmpleado() + " no existe.");
        }

        Limpieza actualizada = limpiezaMapper.toEntity(limpiezaDTO);
        actualizada.setIdLimpieza(idLimpieza);
        Limpieza guardada = limpiezaRepository.save(actualizada);
        return limpiezaMapper.toDTO(guardada);
    }

    @Override
    public void eliminarLimpieza(Integer idLimpieza) {
        if (!limpiezaRepository.existsById(idLimpieza)) {
            throw new ResourceNotFoundException("Registro de Limpieza no encontrado con ID: " + idLimpieza);
        }
        limpiezaRepository.deleteById(idLimpieza);
    }

    // --- Mapeos de consultas personalizadas del repositorio ---

    @Override
    @Transactional(readOnly = true)
    public List<LimpiezaDTO> obtenerPorHabitacion(Integer idHabitacion) {
        return limpiezaRepository.findByHabitacionIdHabitacion(idHabitacion)
                .stream()
                .map(limpiezaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimpiezaDTO> obtenerPorFecha(LocalDate fecha) {
        return limpiezaRepository.findByFecha(fecha)
                .stream()
                .map(limpiezaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimpiezaDTO> obtenerPorEstado(String estado) {
        return limpiezaRepository.findByEstado(estado)
                .stream()
                .map(limpiezaMapper::toDTO)
                .toList();
    }
}