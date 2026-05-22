package com.sgrh.sgrh.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgrh.sgrh.entity.Limpieza;

@Repository
public interface LimpiezaRepository extends JpaRepository<Limpieza, Integer> {
    List<Limpieza> findByHabitacionIdHabitacion(Integer idHabitacion);

    List<Limpieza> findByFecha(LocalDate fecha);

    List<Limpieza> findByEstado(String estado);
}
