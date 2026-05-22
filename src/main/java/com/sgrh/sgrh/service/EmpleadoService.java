package com.sgrh.sgrh.service;

import java.util.List;

import com.sgrh.sgrh.dto.EmpleadoDTO;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    EmpleadoDTO obtenerEmpleadoById(Integer idEmpleado);

    EmpleadoDTO obtenerEmpleadoByEmail(String email);

    EmpleadoDTO actualizarEmpleado(Integer idEmpleado, EmpleadoDTO empleadoDTO);

    void eliminarEmpleado(Integer idEmpleado);

    List<EmpleadoDTO> obtenerTodos();
}
