package com.sgrh.sgrh.service;

import java.util.List;
import com.sgrh.sgrh.dto.PagoDTO;

public interface PagoService {
    PagoDTO registrarPago(PagoDTO pagoDTO);
    PagoDTO obtenerPagoById(Integer idPago);
    List<PagoDTO> obtenerTodos();
    PagoDTO actualizarPago(Integer idPago, PagoDTO pagoDTO);
    void eliminarPago(Integer idPago);
    List<PagoDTO> obtenerPagosPorReserva(Integer idReserva);
}