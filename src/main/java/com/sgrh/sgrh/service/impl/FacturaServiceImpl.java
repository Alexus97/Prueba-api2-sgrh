package com.sgrh.sgrh.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.FacturaDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.FacturaMapper;
import com.sgrh.sgrh.repository.FacturaRepository;
import com.sgrh.sgrh.service.FacturaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    @Override
    public FacturaDTO crearFactura(FacturaDTO facturaDTO) {
        var factura = facturaMapper.toEntity(facturaDTO);
        var facturaGuardada = facturaRepository.save(factura);
        return facturaMapper.toDTO(facturaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public FacturaDTO obtenerFacturaById(Integer idFactura) {
        var factura = facturaRepository.findById(idFactura)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));
        return facturaMapper.toDTO(factura);
    }

    @Override
    public FacturaDTO actualizarFactura(Integer idFactura, FacturaDTO facturaDTO) {
        var factura = facturaRepository.findById(idFactura)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));

        var facturaActualizada = facturaMapper.toEntity(facturaDTO);
        facturaActualizada.setIdFactura(idFactura);
        var facturaGuardada = facturaRepository.save(facturaActualizada);
        return facturaMapper.toDTO(facturaGuardada);
    }

    @Override
    public void eliminarFactura(Integer idFactura) {
        if (!facturaRepository.existsById(idFactura)) {
            throw new ResourceNotFoundException("Factura no encontrada");
        }
        facturaRepository.deleteById(idFactura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturasByReserva(Integer idReserva) {
        return facturaRepository.findByReservaIdReserva(idReserva)
                .stream()
                .map(facturaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturasByFecha(LocalDate fecha) {
        return facturaRepository.findByFechaEmision(fecha)
                .stream()
                .map(facturaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerTodas() {
        return facturaRepository.findAll()
                .stream()
                .map(facturaMapper::toDTO)
                .toList();
    }
}
