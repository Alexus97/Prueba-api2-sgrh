package com.sgrh.sgrh.service;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import java.util.List;

public interface LimpiezaService {
    LimpiezaDTO asignarLimpieza(LimpiezaDTO dto);
    List<LimpiezaDTO> obtenerHistorialLimpieza();
}