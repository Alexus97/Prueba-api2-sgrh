package com.sgrh.sgrh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.EmpleadoMapper;
import com.sgrh.sgrh.repository.EmpleadoRepository;
import com.sgrh.sgrh.service.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
    if (empleadoRepository.existsByEmail(empleadoDTO.email())) {
        throw new IllegalArgumentException("El correo ya existe, intente con otro.");
    }
    
    var empleado = empleadoMapper.toEntity(empleadoDTO);
    var empleadoGuardado = empleadoRepository.save(empleado);
    return empleadoMapper.toDTO(empleadoGuardado);
}

    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO obtenerEmpleadoById(Integer idEmpleado) {
        var empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el ID: " + idEmpleado));
        return empleadoMapper.toDTO(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO obtenerEmpleadoByEmail(String email) {
        var empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el email: " + email));
        return empleadoMapper.toDTO(empleado);
    }

  @Override
public EmpleadoDTO actualizarEmpleado(Integer idEmpleado, EmpleadoDTO empleadoDTO) {
    var empleadoExistente = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el ID: " + idEmpleado));

    if (!empleadoExistente.getEmail().equals(empleadoDTO.email()) && 
        empleadoRepository.existsByEmail(empleadoDTO.email())) {
        //  CAMBIA ESTA LÍNEA TAMBIÉN:
        throw new IllegalArgumentException("El correo ya existe, intente con otro.");
    }

    // (El resto del código de actualizar se queda igual...)
    empleadoExistente.setNombreCompleto(empleadoDTO.nombreCompleto());
    empleadoExistente.setTipoDocumento(empleadoDTO.tipoDocumento());
    empleadoExistente.setNumeroDocumento(empleadoDTO.numeroDocumento());
    empleadoExistente.setCargo(empleadoDTO.cargo());
    empleadoExistente.setTelefono(empleadoDTO.telefono());
    empleadoExistente.setEmail(empleadoDTO.email());

    var empleadoGuardado = empleadoRepository.save(empleadoExistente);
    return empleadoMapper.toDTO(empleadoGuardado);
}

    @Override
    public void eliminarEmpleado(Integer idEmpleado) {
        if (!empleadoRepository.existsById(idEmpleado)) {
            throw new ResourceNotFoundException("Empleado no encontrado con el ID: " + idEmpleado);
        }
        empleadoRepository.deleteById(idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> obtenerTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(empleadoMapper::toDTO)
                .toList();
    }
}