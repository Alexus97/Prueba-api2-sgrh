package com.sgrh.sgrh.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.PromocionDTO;
import com.sgrh.sgrh.service.PromocionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @PostMapping
    public ResponseEntity<PromocionDTO> crear(@Valid @RequestBody PromocionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(promocionService.crearPromocion(dto));
    }

    @GetMapping("/{idPromocion}")
    public ResponseEntity<PromocionDTO> obtenerPorId(@PathVariable Integer idPromocion) {
        return ResponseEntity.ok(promocionService.obtenerPorId(idPromocion));
    }

    @GetMapping
    public ResponseEntity<List<PromocionDTO>> obtenerTodas() {
        return ResponseEntity.ok(promocionService.obtenerTodas());
    }

    @PutMapping("/{idPromocion}")
    public ResponseEntity<PromocionDTO> actualizar(
            @PathVariable Integer idPromocion,
            @Valid @RequestBody PromocionDTO dto) {
        return ResponseEntity.ok(promocionService.actualizarPromocion(idPromocion, dto));
    }

    @DeleteMapping("/{idPromocion}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idPromocion) {
        promocionService.eliminarPromocion(idPromocion);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para listar promociones vigentes. Si no se pasa fecha, toma la actual.
    @GetMapping("/vigentes")
    public ResponseEntity<List<PromocionDTO>> obtenerVigentes(
            @RequestParam(value = "fecha", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(promocionService.obtenerVigentes(fecha));
    }
}