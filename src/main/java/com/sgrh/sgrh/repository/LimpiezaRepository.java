package com.sgrh.sgrh.repository;

import com.sgrh.sgrh.entity.Limpieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimpiezaRepository extends JpaRepository<Limpieza, Integer> {
    // Al heredar de JpaRepository, ya tienes el save() y el findAll() configurados
}