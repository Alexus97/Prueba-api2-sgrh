package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ConsumoServicioDTO;
import com.sgrh.sgrh.entity.ConsumoServicio;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ConsumoServicioMapper;
import com.sgrh.sgrh.repository.ConsumoServicioRepository;
import com.sgrh.sgrh.repository.ReservaRepository;
import com.sgrh.sgrh.repository.ServicioRepository;
import com.sgrh.sgrh.service.ConsumoServicioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsumoServicioServiceImpl implements ConsumoServicioService {

    private final ConsumoServicioRepository consumoServicioRepository;
    private final ReservaRepository reservaRepository;
    private final ServicioRepository servicioRepository;
    private final ConsumoServicioMapper consumoServicioMapper;

    @Override
    public ConsumoServicioDTO registrarConsumo(ConsumoServicioDTO dto) {
        if (!reservaRepository.existsById(dto.idReserva())) {
            throw new ResourceNotFoundException("La Reserva con ID " + dto.idReserva() + " no existe.");
        }
        if (!servicioRepository.existsById(dto.idServicio())) {
            throw new ResourceNotFoundException("El Servicio con ID " + dto.idServicio() + " no existe.");
        }

        ConsumoServicio consumo = consumoServicioMapper.toEntity(dto);
        ConsumoServicio guardado = consumoServicioRepository.save(consumo);
        return consumoServicioMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ConsumoServicioDTO obtenerPorId(Integer idConsumo) {
        ConsumoServicio consumo = consumoServicioRepository.findById(idConsumo)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de consumo no encontrado con ID: " + idConsumo));
        return consumoServicioMapper.toDTO(consumo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsumoServicioDTO> obtenerTodos() {
        return consumoServicioRepository.findAll()
                .stream()
                .map(consumoServicioMapper::toDTO)
                .toList();
    }

    @Override
    public ConsumoServicioDTO actualizarConsumo(Integer idConsumo, ConsumoServicioDTO dto) {
        if (!consumoServicioRepository.existsById(idConsumo)) {
            throw new ResourceNotFoundException("Registro de consumo no encontrado con ID: " + idConsumo);
        }
        if (!reservaRepository.existsById(dto.idReserva())) {
            throw new ResourceNotFoundException("La Reserva con ID " + dto.idReserva() + " no existe.");
        }
        if (!servicioRepository.existsById(dto.idServicio())) {
            throw new ResourceNotFoundException("El Servicio con ID " + dto.idServicio() + " no existe.");
        }

        ConsumoServicio actualizado = consumoServicioMapper.toEntity(dto);
        actualizado.setIdConsumo(idConsumo);
        ConsumoServicio guardado = consumoServicioRepository.save(actualizado);
        return consumoServicioMapper.toDTO(guardado);
    }

    @Override
    public void eliminarConsumo(Integer idConsumo) {
        if (!consumoServicioRepository.existsById(idConsumo)) {
            throw new ResourceNotFoundException("Registro de consumo no encontrado con ID: " + idConsumo);
        }
        consumoServicioRepository.deleteById(idConsumo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsumoServicioDTO> obtenerConsumosPorReserva(Integer idReserva) {
        return consumoServicioRepository.findByReservaIdReserva(idReserva)
                .stream()
                .map(consumoServicioMapper::toDTO)
                .toList();
    }
}