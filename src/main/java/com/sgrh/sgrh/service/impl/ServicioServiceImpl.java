package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ServicioDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ServicioMapper;
import com.sgrh.sgrh.repository.ServicioRepository;
import com.sgrh.sgrh.service.ServicioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    @Override
    public ServicioDTO crearServicio(ServicioDTO servicioDTO) {
        var servicio = servicioMapper.toEntity(servicioDTO);
        var servicioGuardado = servicioRepository.save(servicio);
        return servicioMapper.toDTO(servicioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioDTO obtenerServicioById(Integer idServicio) {
        var servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + idServicio));
        return servicioMapper.toDTO(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioDTO> obtenerTodos() {
        return servicioRepository.findAll()
                .stream()
                .map(servicioMapper::toDTO)
                .toList();
    }

    @Override
    public ServicioDTO actualizarServicio(Integer idServicio, ServicioDTO servicioDTO) {
        if (!servicioRepository.existsById(idServicio)) {
            throw new ResourceNotFoundException("Servicio no encontrado con ID: " + idServicio);
        }
        var servicioActualizado = servicioMapper.toEntity(servicioDTO);
        servicioActualizado.setIdServicio(idServicio); // Aseguramos que conserve el ID de la URL
        var servicioGuardado = servicioRepository.save(servicioActualizado);
        return servicioMapper.toDTO(servicioGuardado);
    }

    @Override
    public void eliminarServicio(Integer idServicio) {
        if (!servicioRepository.existsById(idServicio)) {
            throw new ResourceNotFoundException("Servicio no encontrado con ID: " + idServicio);
        }
        servicioRepository.deleteById(idServicio);
    }
}