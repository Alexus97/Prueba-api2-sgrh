package com.sgrh.sgrh.service.impl;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.entity.Estado;
import com.sgrh.sgrh.entity.Habitacion;
import com.sgrh.sgrh.entity.Mantenimiento;
import com.sgrh.sgrh.mapper.MantenimientoMapper;
import com.sgrh.sgrh.repository.MantenimientoRepository;
import com.sgrh.sgrh.repository.HabitacionRepository; // Asegúrate de tenerlo importado
import com.sgrh.sgrh.repository.EstadoRepository;     // Asegúrate de tenerlo importado
import com.sgrh.sgrh.service.MantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final HabitacionRepository habitacionRepository;
    private final EstadoRepository estadoRepository;
    private final MantenimientoMapper mantenimientoMapper;

    @Override
    @Transactional
    public MantenimientoDTO registrarMantenimiento(MantenimientoDTO dto) {
        // 1. Buscar la Habitación
        Habitacion habitacion = habitacionRepository.findById(dto.idHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // 2. Buscar el Estado del Mantenimiento (Ej: 8 = Pendiente, 9 = En Proceso)
        Estado estadoMantenimiento = estadoRepository.findById(dto.idEstadoMantenimiento())
                .orElseThrow(() -> new RuntimeException("Estado de mantenimiento no encontrado"));

        // 3. Convertir DTO a Entidad e inyectar relaciones del modelo
        Mantenimiento mantenimiento = mantenimientoMapper.toEntity(dto);
        mantenimiento.setHabitacion(habitacion);
        mantenimiento.setEstado(estadoMantenimiento);

        // 4. CRITERIO DE ACEPTACIÓN: Forzar que la habitación pase a estado 7 (En mantenimiento)
        Estado estadoBloqueado = estadoRepository.findById(7)
                .orElseThrow(() -> new RuntimeException("Estado 'En Mantenimiento' (7) no existe en la BD"));
        habitacion.setEstado(estadoBloqueado);
        habitacionRepository.save(habitacion); // Persiste el cambio de estado de la habitación en tiempo real

        // 5. Guardar la actividad de mantenimiento
        Mantenimiento guardado = mantenimientoRepository.save(mantenimiento);
        return mantenimientoMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MantenimientoDTO> obtenerHistorial() {
        // Criterio de aceptación: Llevar e historial de mantenimiento
        return mantenimientoRepository.findAll().stream()
                .map(mantenimientoMapper::toDTO)
                .collect(Collectors.toList());
    }
}