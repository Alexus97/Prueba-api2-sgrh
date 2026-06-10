// package com.sgrh.sgrh.repository;

// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.sgrh.sgrh.entity.Mantenimiento;

// @Repository
// public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
//     List<Mantenimiento> findByHabitacionIdHabitacion(Integer idHabitacion);

//     List<Mantenimiento> findByFecha(LocalDate fecha);
// }

package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
    // Permite obtener el historial de mantenimientos de una habitación específica
    List<Mantenimiento> findByHabitacionIdHabitacion(Integer idHabitacion);
}