package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    
    // Filtro nativo mediante convención de nombres: fechaInicio <= fecha AND fechaFin >= fecha
    List<Promocion> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin);
}