package com.sgrh.sgrh.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.PromocionDTO;
import com.sgrh.sgrh.entity.Promocion;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.PromocionMapper;
import com.sgrh.sgrh.repository.PromocionRepository;
import com.sgrh.sgrh.service.PromocionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository promocionRepository;
    private final PromocionMapper promocionMapper;

    @Override
    public PromocionDTO crearPromocion(PromocionDTO dto) {
        validarFechas(dto.fechaInicio(), dto.fechaFin());
        Promocion promocion = promocionMapper.toEntity(dto);
        Promocion guardada = promocionRepository.save(promocion);
        return promocionMapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public PromocionDTO obtenerPorId(Integer idPromocion) {
        Promocion promocion = promocionRepository.findById(idPromocion)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada con ID: " + idPromocion));
        return promocionMapper.toDTO(promocion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromocionDTO> obtenerTodas() {
        return promocionRepository.findAll()
                .stream()
                .map(promocionMapper::toDTO)
                .toList();
    }

    @Override
    public PromocionDTO actualizarPromocion(Integer idPromocion, PromocionDTO dto) {
        if (!promocionRepository.existsById(idPromocion)) {
            throw new ResourceNotFoundException("Promoción no encontrada con ID: " + idPromocion);
        }
        validarFechas(dto.fechaInicio(), dto.fechaFin());

        Promocion actualizada = promocionMapper.toEntity(dto);
        actualizada.setIdPromocion(idPromocion);
        Promocion guardada = promocionRepository.save(actualizada);
        return promocionMapper.toDTO(guardada);
    }

    @Override
    public void eliminarPromocion(Integer idPromocion) {
        if (!promocionRepository.existsById(idPromocion)) {
            throw new ResourceNotFoundException("Promoción no encontrada con ID: " + idPromocion);
        }
        promocionRepository.deleteById(idPromocion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromocionDTO> obtenerVigentes(LocalDate fecha) {
        // Si no mandan fecha en el RequestParam, usamos la fecha actual del servidor
        LocalDate fechaBusqueda = (fecha != null) ? fecha : LocalDate.now();
        
        // Evaluamos el rango pasando la misma fecha a ambos extremos de la condición
        return promocionRepository.findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(fechaBusqueda, fechaBusqueda)
                .stream()
                .map(promocionMapper::toDTO)
                .toList();
    }

    private void validarFechas(LocalDate inicio, LocalDate fin) {
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException("La fecha de fin de la promoción no puede ser anterior a la fecha de inicio.");
        }
    }
}