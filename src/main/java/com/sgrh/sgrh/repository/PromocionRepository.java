package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    List<Promocion> findByFechaInicioBefore(LocalDate fecha);
    List<Promocion> findByFechaFinAfter(LocalDate fecha);
}
