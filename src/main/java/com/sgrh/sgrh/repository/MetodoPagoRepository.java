package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    // Si necesitas buscar por descripción exacta en el futuro:
    boolean existsByDescripcionIgnoreCase(String descripcion);
}