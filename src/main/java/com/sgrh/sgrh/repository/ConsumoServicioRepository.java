package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.ConsumoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoServicioRepository extends JpaRepository<ConsumoServicio, Integer> {
    List<ConsumoServicio> findByReservaIdReserva(Integer idReserva);
    List<ConsumoServicio> findByFecha(LocalDate fecha);
}
