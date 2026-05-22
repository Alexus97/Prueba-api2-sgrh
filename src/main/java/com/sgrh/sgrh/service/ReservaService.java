package com.sgrh.sgrh.service;

import java.time.LocalDate;
import java.util.List;

import com.sgrh.sgrh.dto.ReservaDTO;

public interface ReservaService {
    ReservaDTO crearReserva(ReservaDTO reservaDTO);

    ReservaDTO obtenerReservaById(Integer idReserva);

    ReservaDTO actualizarReserva(Integer idReserva, ReservaDTO reservaDTO);

    void eliminarReserva(Integer idReserva);

    List<ReservaDTO> obtenerReservasByCliente(Integer idCliente);

    List<ReservaDTO> obtenerReservasByEstado(Integer idEstado);

    List<ReservaDTO> obtenerTodas();

    List<ReservaDTO> obtenerReservasByFechas(LocalDate fechaEntrada, LocalDate fechaSalida);
}
