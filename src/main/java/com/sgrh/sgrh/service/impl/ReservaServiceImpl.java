package com.sgrh.sgrh.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ReservaDTO;
import com.sgrh.sgrh.entity.Reserva;
import com.sgrh.sgrh.entity.Estado;
import com.sgrh.sgrh.entity.Cliente;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ReservaMapper;
import com.sgrh.sgrh.repository.ReservaRepository;
import com.sgrh.sgrh.repository.EstadoRepository;
import com.sgrh.sgrh.repository.ClienteRepository;
import com.sgrh.sgrh.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServiceImpl implements ReservaService {
    
    private final ReservaRepository reservaRepository;
    private final EstadoRepository estadoRepository;
    private final ClienteRepository clienteRepository;
    private final ReservaMapper reservaMapper;

    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        // 1. Validar y recuperar el Cliente de la BD para asegurar la relación
        Cliente cliente = clienteRepository.findById(reservaDTO.idCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + reservaDTO.idCliente()));

        // 2. Validar y recuperar el Estado de la BD usando el ID enviado (ej: 1 para Pendiente)
        Estado estado = estadoRepository.findById(reservaDTO.idEstadoReserva())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de reserva no encontrado con ID: " + reservaDTO.idEstadoReserva()));

        // Validación de seguridad con tu tabla maestra:
        if (!"reserva".equalsIgnoreCase(estado.getTipo())) {
            throw new IllegalArgumentException("El ID de estado provisto no corresponde al tipo 'reserva'.");
        }

        // 3. Construir la entidad con sus relaciones completamente cargadas
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        reserva.setCliente(cliente);
        reserva.setEstadoReserva(estado);

        // 4. Guardar limpiamente sin errores de llave foránea
        Reserva reservaGuardada = reservaRepository.save(reserva);
        return reservaMapper.toDTO(reservaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaDTO obtenerReservaById(Integer idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva));
        return reservaMapper.toDTO(reserva);
    }

    @Override
    public ReservaDTO actualizarReserva(Integer idReserva, ReservaDTO reservaDTO) {
        if (!reservaRepository.existsById(idReserva)) {
            throw new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva);
        }
        
        Estado estado = estadoRepository.findById(reservaDTO.idEstadoReserva())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + reservaDTO.idEstadoReserva()));
                
        Reserva reservaActualizada = reservaMapper.toEntity(reservaDTO);
        reservaActualizada.setIdReserva(idReserva);
        reservaActualizada.setEstadoReserva(estado);
        
        Reserva reservaGuardada = reservaRepository.save(reservaActualizada);
        return reservaMapper.toDTO(reservaGuardada);
    }

    @Override
    public void eliminarReserva(Integer idReserva) {
        if (!reservaRepository.existsById(idReserva)) {
            throw new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva);
        }
        reservaRepository.deleteById(idReserva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByCliente(Integer idCliente) {
        return reservaRepository.findByClienteIdCliente(idCliente).stream().map(reservaMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByEstado(Integer idEstado) {
        return reservaRepository.findByEstadoReservaIdEstado(idEstado).stream().map(reservaMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerTodas() {
        return reservaRepository.findAll().stream().map(reservaMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservasByFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return reservaRepository.findByFechaEntradaAndFechaSalida(fechaEntrada, fechaSalida).stream().map(reservaMapper::toDTO).toList();
    }
}