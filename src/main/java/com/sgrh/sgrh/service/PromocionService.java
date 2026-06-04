package com.sgrh.sgrh.service;

import java.time.LocalDate;
import java.util.List;
import com.sgrh.sgrh.dto.PromocionDTO;

public interface PromocionService {
    PromocionDTO crearPromocion(PromocionDTO dto);
    PromocionDTO obtenerPorId(Integer idPromocion);
    List<PromocionDTO> obtenerTodas();
    PromocionDTO actualizarPromocion(Integer idPromocion, PromocionDTO dto);
    void eliminarPromocion(Integer idPromocion);
    List<PromocionDTO> obtenerVigentes(LocalDate fecha);
}