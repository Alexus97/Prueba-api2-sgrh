package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.ReservaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaHabitacionRepository extends JpaRepository<ReservaHabitacion, Integer> {
    List<ReservaHabitacion> findByReservaIdReserva(Integer idReserva);
    List<ReservaHabitacion> findByHabitacionIdHabitacion(Integer idHabitacion);
}
