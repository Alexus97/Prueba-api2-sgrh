package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.AsignacionRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRolRepository extends JpaRepository<AsignacionRol, Integer> {
    List<AsignacionRol> findByEmpleadoIdEmpleado(Integer idEmpleado);
    List<AsignacionRol> findByRolIdRol(Integer idRol);
}
