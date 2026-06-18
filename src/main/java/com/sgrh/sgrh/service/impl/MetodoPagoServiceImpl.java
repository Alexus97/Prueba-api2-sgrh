package com.sgrh.sgrh.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.MetodoPagoDTO;
import com.sgrh.sgrh.entity.MetodoPago;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.MetodoPagoMapper;
import com.sgrh.sgrh.repository.MetodoPagoRepository;
import com.sgrh.sgrh.service.MetodoPagoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final MetodoPagoMapper metodoPagoMapper;

    @Override
    public MetodoPagoDTO crearMetodoPago(MetodoPagoDTO dto) {
        if (metodoPagoRepository.existsByDescripcionIgnoreCase(dto.descripcion())) {
            throw new IllegalArgumentException("El método de pago '" + dto.descripcion() + "' ya está registrado.");
        }
        MetodoPago metodoPago = metodoPagoMapper.toEntity(dto);
        return metodoPagoMapper.toDTO(metodoPagoRepository.save(metodoPago));
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPagoDTO obtenerPorId(Integer id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado con ID: " + id));
        return metodoPagoMapper.toDTO(metodoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoDTO> obtenerTodos() {
        return metodoPagoRepository.findAll()
                .stream()
                .map(metodoPagoMapper::toDTO)
                .toList();
    }

    @Override
    public MetodoPagoDTO actualizarMetodoPago(Integer id, MetodoPagoDTO dto) {
        MetodoPago existente = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado con ID: " + id));

        // Validar que el nuevo nombre no choque con otro existente
        if (!existente.getDescripcion().equalsIgnoreCase(dto.descripcion()) && 
            metodoPagoRepository.existsByDescripcionIgnoreCase(dto.descripcion())) {
            throw new IllegalArgumentException("Ya existe otro método de pago con la descripción: " + dto.descripcion());
        }

        existente.setDescripcion(dto.descripcion());
        return metodoPagoMapper.toDTO(metodoPagoRepository.save(existente));
    }

    @Override
    public void eliminarMetodoPago(Integer id) {
        if (!metodoPagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Método de pago no encontrado con ID: " + id);
        }
        metodoPagoRepository.deleteById(id);
    }
}