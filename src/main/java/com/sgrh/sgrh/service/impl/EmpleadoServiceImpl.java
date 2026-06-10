package com.sgrh.sgrh.service.impl;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.entity.Empleado;
import com.sgrh.sgrh.mapper.EmpleadoMapper;
import com.sgrh.sgrh.repository.EmpleadoRepository;
import com.sgrh.sgrh.repository.LimpiezaRepository;
import com.sgrh.sgrh.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final LimpiezaRepository limpiezaRepository; 
    private final EmpleadoMapper empleadoMapper;

    @Override
    @Transactional
    public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
        // Regla: Correo electrónico único (Excepción corregida para el Manejador Global)
        if (empleadoRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Error: Ya existe un empleado registrado con el correo: " + dto.email());
        }
        Empleado empleado = empleadoMapper.toEntity(dto);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(empleadoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO buscarPorEmail(String email) {
        return empleadoRepository.findByEmail(email)
                .map(empleadoMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con el correo: " + email));
    }

    @Override
    @Transactional
    public EmpleadoDTO editarEmpleado(Integer id, EmpleadoDTO dto) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con el ID: " + id));

        // Regla: No permitir correos duplicados con otros empleados (Excepción corregida para evitar el error 500)
        if (!empleadoExistente.getEmail().equals(dto.email()) && empleadoRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Error: El correo '" + dto.email() + "' ya está siendo usado por otro empleado.");
        }

        // Modificar campos utilizando exclusivamente nombreCompleto
        empleadoExistente.setNombreCompleto(dto.nombreCompleto());
        empleadoExistente.setNumeroDocumento(dto.numeroDocumento());
        empleadoExistente.setTelefono(dto.telefono());
        empleadoExistente.setEmail(dto.email());
        empleadoExistente.setCargo(dto.cargo());
        empleadoExistente.setTipoDocumento(dto.tipoDocumento()); 

        Empleado actualizado = empleadoRepository.save(empleadoExistente);
        return empleadoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Integer id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con el ID: " + id));

        // Regla de Seguridad Corregida: Validar si el empleado tiene registros activos asociados en el historial de limpieza
        boolean tieneTareasActivas = limpiezaRepository.findAll().stream()
                .anyMatch(l -> l.getEmpleado() != null && l.getEmpleado().getIdEmpleado().equals(id));

        if (tieneTareasActivas) {
            throw new IllegalStateException("No se puede eliminar al empleado porque tiene asignaciones de limpieza activas en el historial.");
        }

        empleadoRepository.delete(empleado);
    }
}