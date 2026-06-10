package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    
    // Busca por el número de habitación 
    List<Habitacion> findByNumero(Integer numero);
    
    // sucursalHotel (objeto) + IdSucursal (su llave primaria)
    List<Habitacion> findBySucursalHotelIdSucursal(Integer idSucursal);
    
    // estado (objeto) + idEstado (su llave primaria)
    List<Habitacion> findByEstadoIdEstado(Integer idEstado);

    /**
     * HU: No se deben mostrar habitaciones no disponibles en el proceso de reserva.
     * Este método permite buscar directamente por el nombre del estado (ej: "Disponible", "Mantenimiento").
     * Spring Data JPA caminará automáticamente de Habitacion -> Estado -> tipo.
     */
    List<Habitacion> findByEstadoTipo(String tipoEstado);
}