package com.sgrh.sgrh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.SucursalHotelDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.SucursalHotelMapper;
import com.sgrh.sgrh.repository.SucursalHotelRepository;
import com.sgrh.sgrh.service.SucursalHotelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SucursalHotelServiceImpl implements SucursalHotelService {
    private final SucursalHotelRepository sucursalHotelRepository;
    private final SucursalHotelMapper sucursalHotelMapper;

    @Override
    public SucursalHotelDTO crearSucursalHotel(SucursalHotelDTO sucursalHotelDTO) {
        var sucursalHotel = sucursalHotelMapper.toEntity(sucursalHotelDTO);
        var sucursalGuardada = sucursalHotelRepository.save(sucursalHotel);
        return sucursalHotelMapper.toDTO(sucursalGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public SucursalHotelDTO obtenerSucursalHotelById(Integer idSucursal) {
        var sucursalHotel = sucursalHotelRepository.findById(idSucursal)
                .orElseThrow(() -> new ResourceNotFoundException("SucursalHotel no encontrada"));
        return sucursalHotelMapper.toDTO(sucursalHotel);
    }

    @Override
    @Transactional(readOnly = true)
    public SucursalHotelDTO obtenerSucursalHotelByNombre(String nombre) {
        var sucursalHotel = sucursalHotelRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("SucursalHotel no encontrada"));
        return sucursalHotelMapper.toDTO(sucursalHotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalHotelDTO> obtenerTodas() {
        return sucursalHotelRepository.findAll()
                .stream()
                .map(sucursalHotelMapper::toDTO)
                .toList();
    }

    @Override
    public SucursalHotelDTO actualizarSucursalHotel(Integer idSucursal, SucursalHotelDTO sucursalHotelDTO) {
        if (!sucursalHotelRepository.existsById(idSucursal)) {
            throw new ResourceNotFoundException("SucursalHotel no encontrada");
        }
        var sucursalHotelActualizada = sucursalHotelMapper.toEntity(sucursalHotelDTO);
        sucursalHotelActualizada.setIdSucursal(idSucursal);
        var sucursalGuardada = sucursalHotelRepository.save(sucursalHotelActualizada);
        return sucursalHotelMapper.toDTO(sucursalGuardada);
    }

    @Override
    public void eliminarSucursalHotel(Integer idSucursal) {
        if (!sucursalHotelRepository.existsById(idSucursal)) {
            throw new ResourceNotFoundException("SucursalHotel no encontrada");
        }
        sucursalHotelRepository.deleteById(idSucursal);
    }
}
