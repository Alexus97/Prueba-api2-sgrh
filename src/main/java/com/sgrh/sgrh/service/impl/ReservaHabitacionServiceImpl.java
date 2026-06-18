package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ReservaHabitacionDTO;
import com.sgrh.sgrh.entity.Habitacion;
import com.sgrh.sgrh.entity.Estado;
import com.sgrh.sgrh.entity.ReservaHabitacion;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ReservaHabitacionMapper;
import com.sgrh.sgrh.repository.ReservaHabitacionRepository;
import com.sgrh.sgrh.repository.HabitacionRepository;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.repository.ReservaRepository;
import com.sgrh.sgrh.service.ReservaHabitacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaHabitacionServiceImpl implements ReservaHabitacionService {

    private final ReservaHabitacionRepository reservaHabitacionRepository;
    private final HabitacionRepository habitacionRepository;
    private final EstadoRepository estadoRepository;
    private final ReservaRepository reservaRepository;
    private final ReservaHabitacionMapper reservaHabitacionMapper;

  private static final Integer ESTADO_HABITACION_DISPONIBLE = 5; // ID 5 en tu captura
private static final Integer ESTADO_HABITACION_OCUPADA = 6;    //
    @Override
    public ReservaHabitacionDTO asignarHabitacionAReserva(ReservaHabitacionDTO detalleDTO) {
        // 1. Validar que la reserva exista
        if (!reservaRepository.existsById(detalleDTO.idReserva())) {
            throw new ResourceNotFoundException("La Reserva con ID " + detalleDTO.idReserva() + " no existe.");
        }

        // 2. HU: Validar disponibilidad de la habitación
        Habitacion habitacion = habitacionRepository.findById(detalleDTO.idHabitacion())
                .orElseThrow(() -> new ResourceNotFoundException("La Habitación con ID " + detalleDTO.idHabitacion() + " no existe."));

        if (!habitacion.getEstado().getIdEstado().equals(ESTADO_HABITACION_DISPONIBLE)) {
            throw new IllegalArgumentException("No se puede asignar: La habitación ya está OCUPADA, en limpieza o mantenimiento.");
        }

        // 3. HU: Cambiar estado a Ocupada en tiempo real
        Estado estadoOcupada = estadoRepository.findById(ESTADO_HABITACION_OCUPADA)
                .orElseThrow(() -> new ResourceNotFoundException("Estado 'Ocupado' no configurado."));
        
        habitacion.setEstado(estadoOcupada);
        habitacionRepository.save(habitacion);

        // 4. Guardar en la tabla intermedia
        ReservaHabitacion detalleEntity = reservaHabitacionMapper.toEntity(detalleDTO);
        ReservaHabitacion guardado = reservaHabitacionRepository.save(detalleEntity);
        
        return reservaHabitacionMapper.toDTO(guardado);
    }

    @Override
    public void removerHabitacionDeReserva(Integer idReservaHabitacion) {
        ReservaHabitacion detalle = reservaHabitacionRepository.findById(idReservaHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Asignación no encontrada con ID: " + idReservaHabitacion));

        // HU: Al liberar la habitación de la tabla intermedia, vuelve a estar disponible
        Habitacion habitacion = detalle.getHabitacion();
        if (habitacion != null) {
            Estado estadoDisponible = estadoRepository.findById(ESTADO_HABITACION_DISPONIBLE)
                    .orElseThrow(() -> new ResourceNotFoundException("Estado 'Disponible' no configurado."));
            habitacion.setEstado(estadoDisponible);
            habitacionRepository.save(habitacion);
        }

        reservaHabitacionRepository.deleteById(idReservaHabitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaHabitacionDTO> obtenerHabitacionesPorReserva(Integer idReserva) {
        return reservaHabitacionRepository.findByReservaIdReserva(idReserva)
                .stream()
                .map(reservaHabitacionMapper::toDTO)
                .toList();
    }
}