package com.sgrh.sgrh.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgrh.sgrh.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    
    // Verifica si el correo existe (Para las reglas de la HU)
    boolean existsByEmail(String email);
    
    // Busca por email (Para el endpoint de buscar)
    Optional<Empleado> findByEmail(String email);

    
}