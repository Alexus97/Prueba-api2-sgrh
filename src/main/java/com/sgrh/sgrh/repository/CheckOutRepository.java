package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Integer> {
    List<CheckOut> findByReservaIdReserva(Integer idReserva);
    List<CheckOut> findByEmpleadoIdEmpleado(Integer idEmpleado);
}
