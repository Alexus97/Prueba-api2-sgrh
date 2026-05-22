package com.sgrh.sgrh.service;

import java.time.LocalDate;
import java.util.List;

import com.sgrh.sgrh.dto.FacturaDTO;

public interface FacturaService {
    FacturaDTO crearFactura(FacturaDTO facturaDTO);

    FacturaDTO obtenerFacturaById(Integer idFactura);

    FacturaDTO actualizarFactura(Integer idFactura, FacturaDTO facturaDTO);

    void eliminarFactura(Integer idFactura);

    List<FacturaDTO> obtenerFacturasByReserva(Integer idReserva);

    List<FacturaDTO> obtenerFacturasByFecha(LocalDate fecha);

    List<FacturaDTO> obtenerTodas();
}
