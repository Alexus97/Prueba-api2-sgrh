package com.sgrh.sgrh.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.service.LimpiezaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/limpiezas")
@RequiredArgsConstructor
public class LimpiezaController {

    private final LimpiezaService limpiezaService;

    @PostMapping
    public ResponseEntity<LimpiezaDTO> crearLimpieza(@Valid @RequestBody LimpiezaDTO limpiezaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(limpiezaService.crearLimpieza(limpiezaDTO));
    }

    @GetMapping("/{idLimpieza}")
    public ResponseEntity<LimpiezaDTO> obtenerLimpieza(@PathVariable Integer idLimpieza) {
        return ResponseEntity.ok(limpiezaService.obtenerLimpiezaById(idLimpieza));
    }

    @GetMapping
    public ResponseEntity<List<LimpiezaDTO>> obtenerTodas() {
        return ResponseEntity.ok(limpiezaService.obtenerTodas());
    }

    @PutMapping("/{idLimpieza}")
    public ResponseEntity<LimpiezaDTO> actualizarLimpieza(
            @PathVariable Integer idLimpieza,
            @Valid @RequestBody LimpiezaDTO limpiezaDTO) {
        return ResponseEntity.ok(limpiezaService.actualizarLimpieza(idLimpieza, limpiezaDTO));
    }

    @DeleteMapping("/{idLimpieza}")
    public ResponseEntity<Void> eliminarLimpieza(@PathVariable Integer idLimpieza) {
        limpiezaService.eliminarLimpieza(idLimpieza);
        return ResponseEntity.noContent().build();
    }

    // --- Endpoints para filtros de búsqueda ---

    @GetMapping("/habitacion/{idHabitacion}")
    public ResponseEntity<List<LimpiezaDTO>> obtenerPorHabitacion(@PathVariable Integer idHabitacion) {
        return ResponseEntity.ok(limpiezaService.obtenerPorHabitacion(idHabitacion));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<LimpiezaDTO>> obtenerPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(limpiezaService.obtenerPorFecha(fecha));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<LimpiezaDTO>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(limpiezaService.obtenerPorEstado(estado));
    }
}