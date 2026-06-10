package com.sgrh.sgrh.service.impl;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.entity.Empleado;
import com.sgrh.sgrh.entity.Estado;
import com.sgrh.sgrh.entity.Habitacion;
import com.sgrh.sgrh.entity.Limpieza;
import com.sgrh.sgrh.mapper.LimpiezaMapper;
import com.sgrh.sgrh.repository.LimpiezaRepository;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.repository.EmpleadoRepository;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.service.LimpiezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LimpiezaServiceImpl implements LimpiezaService {

    private final LimpiezaRepository limpiezaRepository;
    private final HabitacionRepository habitacionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EstadoRepository estadoRepository;
    private final LimpiezaMapper limpiezaMapper;

    @Override
    @Transactional
    public LimpiezaDTO asignarLimpieza(LimpiezaDTO dto) {
        // 1. Validar Habitación
        Habitacion habitacion = habitacionRepository.findById(dto.idHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + dto.idHabitacion()));

        // 2. Validar Empleado
        Empleado empleado = empleadoRepository.findById(dto.idEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + dto.idEmpleado()));

        // 3. Mapear y enlazar relaciones
        Limpieza limpieza = limpiezaMapper.toEntity(dto);
        limpieza.setHabitacion(habitacion);
        limpieza.setEmpleado(empleado);
        
        limpieza.setEstado(dto.estado() != null ? dto.estado() : "PENDIENTE");

        // 4. Bloquear habitación poniéndola en Mantenimiento/Limpieza (ID 7)
        Estado estadoBloqueado = estadoRepository.findById(7)
                .orElseThrow(() -> new RuntimeException("El estado maestro con ID 7 no existe en la BD"));
        habitacion.setEstado(estadoBloqueado);
        habitacionRepository.save(habitacion); 

        // 5. Persistir
        Limpieza guardada = limpiezaRepository.save(limpieza);
        return limpiezaMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimpiezaDTO> obtenerHistorialLimpieza() {
        return limpiezaRepository.findAll().stream()
                .map(limpiezaMapper::toDTO)
                .collect(Collectors.toList());
    }
}