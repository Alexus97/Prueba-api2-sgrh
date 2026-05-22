package com.sgrh.sgrh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgrh.sgrh.entity.OpinionCliente;

@Repository
public interface OpinionClienteRepository extends JpaRepository<OpinionCliente, Integer> {
    List<OpinionCliente> findByClienteIdCliente(Integer idCliente);

    List<OpinionCliente> findByReservaIdReserva(Integer idReserva);
}
