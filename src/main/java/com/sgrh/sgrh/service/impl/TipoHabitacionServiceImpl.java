package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.TipoHabitacionDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.TipoHabitacionMapper;
import com.sgrh.sgrh.repository.TipoHabitacionRepository;
import com.sgrh.sgrh.service.TipoHabitacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoHabitacionServiceImpl implements TipoHabitacionService {

    private final TipoHabitacionRepository tipoHabitacionRepository;
    private final TipoHabitacionMapper tipoHabitacionMapper;

    @Override
    public TipoHabitacionDTO crearTipoHabitacion(TipoHabitacionDTO tipoHabitacionDTO) {
        var tipoHabitacion = tipoHabitacionMapper.toEntity(tipoHabitacionDTO);
        var guardado = tipoHabitacionRepository.save(tipoHabitacion);
        return tipoHabitacionMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoHabitacionDTO obtenerTipoHabitacionById(Integer idTipo) {
        var tipoHabitacion = tipoHabitacionRepository.findById(idTipo)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Habitación no encontrado con ID: " + idTipo));
        return tipoHabitacionMapper.toDTO(tipoHabitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoHabitacionDTO> obtenerTodos() {
        return tipoHabitacionRepository.findAll()
                .stream()
                .map(tipoHabitacionMapper::toDTO)
                .toList();
    }

    @Override
    public TipoHabitacionDTO actualizarTipoHabitacion(Integer idTipo, TipoHabitacionDTO tipoHabitacionDTO) {
        if (!tipoHabitacionRepository.existsById(idTipo)) {
            throw new ResourceNotFoundException("Tipo de Habitación no encontrado con ID: " + idTipo);
        }
        var actualizado = tipoHabitacionMapper.toEntity(tipoHabitacionDTO);
        actualizado.setIdTipo(idTipo); // Mantenemos el ID de la URL
        var guardado = tipoHabitacionRepository.save(actualizado);
        return tipoHabitacionMapper.toDTO(guardado);
    }

    @Override
    public void eliminarTipoHabitacion(Integer idTipo) {
        if (!tipoHabitacionRepository.existsById(idTipo)) {
            throw new ResourceNotFoundException("Tipo de Habitación no encontrado con ID: " + idTipo);
        }
        tipoHabitacionRepository.deleteById(idTipo);
    }
}