package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByClienteIdCliente(Integer idCliente);
    List<Reserva> findByFechaEntradaAndFechaSalida(LocalDate fechaEntrada, LocalDate fechaSalida);
    List<Reserva> findByEstadoReservaIdEstado(Integer idEstado);
}
