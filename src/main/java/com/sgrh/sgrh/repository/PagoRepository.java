package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByReservaIdReserva(Integer idReserva);
    List<Pago> findByEmpleadoIdEmpleado(Integer idEmpleado);
    List<Pago> findByFechaPago(LocalDate fechaPago);
}
