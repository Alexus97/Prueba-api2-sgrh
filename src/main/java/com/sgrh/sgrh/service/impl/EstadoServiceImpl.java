package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.EstadoDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.EstadoMapper;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.service.EstadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    public EstadoDTO crearEstado(EstadoDTO estadoDTO) {
        var estado = estadoMapper.toEntity(estadoDTO);
        var estadoGuardado = estadoRepository.save(estado);
        return estadoMapper.toDTO(estadoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoDTO obtenerEstadoById(Integer idEstado) {
        var estado = estadoRepository.findById(idEstado)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));
        return estadoMapper.toDTO(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoDTO obtenerEstadoByTipo(String tipo) {
        var estado = estadoRepository.findByTipo(tipo)
                .orElseThrow(() -> new ResourceNotFoundException("Estado con tipo '" + tipo + "' no encontrado"));
        return estadoMapper.toDTO(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoDTO> obtenerTodos() {
        return estadoRepository.findAll()
                .stream()
                .map(estadoMapper::toDTO)
                .toList();
    }

    @Override
    public EstadoDTO actualizarEstado(Integer idEstado, EstadoDTO estadoDTO) {
        if (!estadoRepository.existsById(idEstado)) {
            throw new ResourceNotFoundException("Estado no encontrado");
        }
        var estadoActualizado = estadoMapper.toEntity(estadoDTO);
        // Aseguramos que conserve el ID que viene por la URL
        estadoActualizado.setIdEstado(idEstado);
        var estadoGuardado = estadoRepository.save(estadoActualizado);
        return estadoMapper.toDTO(estadoGuardado);
    }

    @Override
    public void eliminarEstado(Integer idEstado) {
        if (!estadoRepository.existsById(idEstado)) {
            throw new ResourceNotFoundException("Estado no encontrado");
        }
        estadoRepository.deleteById(idEstado);
    }
}