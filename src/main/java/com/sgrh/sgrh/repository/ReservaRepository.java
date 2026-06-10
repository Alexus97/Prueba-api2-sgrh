package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    // Convención JPA: cliente (objeto) + IdCliente (llave primaria de Cliente)
    List<Reserva> findByClienteIdCliente(Integer idCliente);

    // Convención JPA: estadoReserva (objeto) + IdEstado (llave primaria de Estado)
    List<Reserva> findByEstadoReservaIdEstado(Integer idEstado);

    // Búsqueda por rango de fechas
    List<Reserva> findByFechaEntradaAndFechaSalida(LocalDate fechaEntrada, LocalDate fechaSalida);
}