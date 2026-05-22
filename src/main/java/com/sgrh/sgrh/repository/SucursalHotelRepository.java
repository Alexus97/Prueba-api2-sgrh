package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.SucursalHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SucursalHotelRepository extends JpaRepository<SucursalHotel, Integer> {
    Optional<SucursalHotel> findByNombre(String nombre);
}
