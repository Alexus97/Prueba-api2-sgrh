package com.sgrh.sgrh.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ReservaDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ReservaMapper;
import com.sgrh.sgrh.repository.ReservaRepository;
import com.sgrh.sgrh.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        var reserva = reservaMapper.toEntity(reservaDTO);
        var reservaGuardada = reservaRepository.save(reserva);
        return reservaMapper.toDTO(reservaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaDTO obtenerReservaById(Integer idReserva) {
        var reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));
        return reservaMapper.toDTO(reserva);
    }

    @Override
    public ReservaDTO actualizarReserva(Integer idReserva, ReservaDTO reservaDTO) {
        var reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        var reservaActualizada = reservaMapper.toEntity(reservaDTO);
        reservaActualizada.setIdReserva(idReserva);
        var reservaGuardada = reservaRepository.save(reservaActualizada);
        return reservaMapper.toDTO(reservaGuardada);
    }

    @Override
    public void eliminarReserva(Integer idReserva) {
        if (!reservaRepository.existsById(idReserva)) {
            throw new ResourceNotFoundException("Reserva no encontrada");
        }
        reservaRepository.deleteById(idReserva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByCliente(Integer idCliente) {
        return reservaRepository.findByClienteIdCliente(idCliente)
                .stream()
                .map(reservaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByEstado(Integer idEstado) {
        return reservaRepository.findByEstadoReservaIdEstado(idEstado)
                .stream()
                .map(reservaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerTodas() {
        return reservaRepository.findAll()
                .stream()
                .map(reservaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return reservaRepository.findByFechaEntradaAndFechaSalida(fechaEntrada, fechaSalida)
                .stream()
                .map(reservaMapper::toDTO)
                .toList();
    }
}
