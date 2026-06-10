package com.sgrh.sgrh.service;

import com.sgrh.sgrh.dto.ReservaHabitacionDTO;
import java.util.List;

public interface ReservaHabitacionService {
    ReservaHabitacionDTO asignarHabitacionAReserva(ReservaHabitacionDTO detalleDTO);
    void removerHabitacionDeReserva(Integer idReservaHabitacion);
    List<ReservaHabitacionDTO> obtenerHabitacionesPorReserva(Integer idReserva);
}