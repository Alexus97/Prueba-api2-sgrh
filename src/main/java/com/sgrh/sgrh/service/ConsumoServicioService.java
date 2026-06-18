package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.ConsumoServicioDTO;

public interface ConsumoServicioService {
    ConsumoServicioDTO registrarConsumo(ConsumoServicioDTO dto);
    ConsumoServicioDTO obtenerPorId(Integer idConsumo);
    List<ConsumoServicioDTO> obtenerTodos();
    ConsumoServicioDTO actualizarConsumo(Integer idConsumo, ConsumoServicioDTO dto);
    void eliminarConsumo(Integer idConsumo);
    
    // Filtro estratégico para ver los gastos extras de una habitación/reserva
    List<ConsumoServicioDTO> obtenerConsumosPorReserva(Integer idReserva);
}