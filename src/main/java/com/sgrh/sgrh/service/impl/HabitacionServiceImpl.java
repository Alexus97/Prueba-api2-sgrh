package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.entity.Estado;
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

    // ID del estado 'Disponible' según tu tabla maestra en MySQL
    private static final Integer ESTADO_HABITACION_DISPONIBLE = 5;

    @Override
    public HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO) {
        if (!sucursalHotelRepository.existsById(habitacionDTO.idSucursal())) {
            throw new ResourceNotFoundException("La Sucursal con ID " + habitacionDTO.idSucursal() + " no existe.");
        }
        if (!estadoRepository.existsById(habitacionDTO.idEstadoHabitacion())) {
            throw new ResourceNotFoundException(
                    "El Estado con ID " + habitacionDTO.idEstadoHabitacion() + " no existe.");
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
            throw new ResourceNotFoundException(
                    "El Estado con ID " + habitacionDTO.idEstadoHabitacion() + " no existe.");
        }

        Habitacion actualizada = habitacionMapper.toEntity(habitacionDTO);
        actualizada.setIdHabitacion(idHabitacion);
        Habitacion guardada = habitacionRepository.save(actualizada);
        return habitacionMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> obtenerPorEstado(String tipoEstado) {
        // Criterio HU: Si se consulta "disponible", usamos la constante explícita indexada en tu BD
        if ("disponible".equalsIgnoreCase(tipoEstado)) {
            return habitacionRepository.findByEstadoIdEstado(ESTADO_HABITACION_DISPONIBLE)
                    .stream()
                    .map(habitacionMapper::toDTO)
                    .toList();
        }

        // Búsqueda genérica por la columna 'descripcion' o 'tipo' en caso de otros estados
        Estado estado = estadoRepository.findByTipo(tipoEstado)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado para el tipo: " + tipoEstado));
                
        return habitacionRepository.findByEstadoIdEstado(estado.getIdEstado())
                .stream()
                .map(habitacionMapper::toDTO)
                .toList();
    }

    @Override
    public HabitacionDTO cambiarEstado(Integer idHabitacion, Integer idEstado) {
        Habitacion habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con ID: " + idHabitacion));
                
        Estado estado = estadoRepository.findById(idEstado)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + idEstado));

        // Validación de negocio: Asegurar que el estado pertenezca al dominio de la habitación
        if (!"habitacion".equalsIgnoreCase(estado.getTipo())) {
            throw new IllegalArgumentException("El estado seleccionado ('" + estado.getDescripcion() + "') no es válido para una Habitación.");
        }

        habitacion.setEstado(estado);
        return habitacionMapper.toDTO(habitacionRepository.save(habitacion));
    }

    @Override
    public void eliminarHabitacion(Integer idHabitacion) {
        if (!habitacionRepository.existsById(idHabitacion)) {
            throw new ResourceNotFoundException("Habitación no encontrada con ID: " + idHabitacion);
        }
        habitacionRepository.deleteById(idHabitacion);
    }
}