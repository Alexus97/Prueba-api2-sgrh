// package com.sgrh.sgrh.service;

// import java.util.List;
// import com.sgrh.sgrh.dto.MantenimientoDTO;

// public interface MantenimientoService {
//     MantenimientoDTO crearMantenimiento(MantenimientoDTO mantenimientoDTO);
//     MantenimientoDTO obtenerMantenimientoById(Integer idMantenimiento);
//     List<MantenimientoDTO> obtenerTodos();
//     MantenimientoDTO actualizarMantenimiento(Integer idMantenimiento, MantenimientoDTO mantenimientoDTO);
//     void eliminarMantenimiento(Integer idMantenimiento);
// }

package com.sgrh.sgrh.service;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import java.util.List;

public interface MantenimientoService {
    MantenimientoDTO registrarMantenimiento(MantenimientoDTO dto);
    List<MantenimientoDTO> obtenerHistorial();
}