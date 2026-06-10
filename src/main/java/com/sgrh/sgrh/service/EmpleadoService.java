package com.sgrh.sgrh.service;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO dto);
    List<EmpleadoDTO> listarEmpleados();
    EmpleadoDTO buscarPorEmail(String email);
    
    // NUEVOS MÉTODOS para las HU actuales
    EmpleadoDTO editarEmpleado(Integer id, EmpleadoDTO dto);
    void eliminarEmpleado(Integer id);
}