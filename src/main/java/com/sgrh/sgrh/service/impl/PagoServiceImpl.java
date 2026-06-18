package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.PagoDTO;
import com.sgrh.sgrh.entity.Pago;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.PagoMapper;
import com.sgrh.sgrh.repository.PagoRepository;
import com.sgrh.sgrh.repository.ReservaRepository;
import com.sgrh.sgrh.repository.EmpleadoRepository;
import com.sgrh.sgrh.repository.MetodoPagoRepository;
import com.sgrh.sgrh.service.PagoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final PagoMapper pagoMapper;

    @Override
    public PagoDTO registrarPago(PagoDTO pagoDTO) {
        // Validar la existencia de cada relación foránea obligatoria
        if (!reservaRepository.existsById(pagoDTO.idReserva())) {
            throw new ResourceNotFoundException("La Reserva con ID " + pagoDTO.idReserva() + " no existe.");
        }
        if (!empleadoRepository.existsById(pagoDTO.idEmpleado())) {
            throw new ResourceNotFoundException("El Empleado con ID " + pagoDTO.idEmpleado() + " no existe.");
        }
        if (!metodoPagoRepository.existsById(pagoDTO.idMetodoPago())) {
            throw new ResourceNotFoundException("El Método de Pago con ID " + pagoDTO.idMetodoPago() + " no existe.");
        }

        Pago pago = pagoMapper.toEntity(pagoDTO);
        Pago guardado = pagoRepository.save(pago);
        return pagoMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PagoDTO obtenerPagoById(Integer idPago) {
        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + idPago));
        return pagoMapper.toDTO(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoDTO> obtenerTodos() {
        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toDTO)
                .toList();
    }

    @Override
    public PagoDTO actualizarPago(Integer idPago, PagoDTO pagoDTO) {
        if (!pagoRepository.existsById(idPago)) {
            throw new ResourceNotFoundException("Pago no encontrado con ID: " + idPago);
        }
        if (!reservaRepository.existsById(pagoDTO.idReserva())) {
            throw new ResourceNotFoundException("La Reserva con ID " + pagoDTO.idReserva() + " no existe.");
        }
        if (!empleadoRepository.existsById(pagoDTO.idEmpleado())) {
            throw new ResourceNotFoundException("El Empleado con ID " + pagoDTO.idEmpleado() + " no existe.");
        }
        if (!metodoPagoRepository.existsById(pagoDTO.idMetodoPago())) {
            throw new ResourceNotFoundException("El Método de Pago con ID " + pagoDTO.idMetodoPago() + " no existe.");
        }

        Pago actualizado = pagoMapper.toEntity(pagoDTO);
        actualizado.setIdPago(idPago);
        Pago guardado = pagoRepository.save(actualizado);
        return pagoMapper.toDTO(guardado);
    }

    @Override
    public void eliminarPago(Integer idPago) {
        if (!pagoRepository.existsById(idPago)) {
            throw new ResourceNotFoundException("Pago no encontrado con ID: " + idPago);
        }
        pagoRepository.deleteById(idPago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoDTO> obtenerPagosPorReserva(Integer idReserva) {
        return pagoRepository.findByReservaIdReserva(idReserva)
                .stream()
                .map(pagoMapper::toDTO)
                .toList();
    }
}