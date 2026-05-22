package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    List<Habitacion> findByNumero(Integer numero);
    List<Habitacion> findBySucursalHotelIdSucursal(Integer idSucursal);
    List<Habitacion> findByEstadoHabitacionIdEstado(Integer idEstado);
}
