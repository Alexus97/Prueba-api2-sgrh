package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.service.MantenimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<MantenimientoDTO> crearMantenimiento(@Valid @RequestBody MantenimientoDTO mantenimientoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mantenimientoService.crearMantenimiento(mantenimientoDTO));
    }

    @GetMapping("/{idMantenimiento}")
    public ResponseEntity<MantenimientoDTO> obtenerMantenimiento(@PathVariable Integer idMantenimiento) {
        return ResponseEntity.ok(mantenimientoService.obtenerMantenimientoById(idMantenimiento));
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> obtenerTodos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodos());
    }

    @PutMapping("/{idMantenimiento}")
    public ResponseEntity<MantenimientoDTO> actualizarMantenimiento(
            @PathVariable Integer idMantenimiento,
            @Valid @RequestBody MantenimientoDTO mantenimientoDTO) {
        return ResponseEntity.ok(mantenimientoService.actualizarMantenimiento(idMantenimiento, mantenimientoDTO));
    }

    @DeleteMapping("/{idMantenimiento}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable Integer idMantenimiento) {
        mantenimientoService.eliminarMantenimiento(idMantenimiento);
        return ResponseEntity.noContent().build();
    }
}