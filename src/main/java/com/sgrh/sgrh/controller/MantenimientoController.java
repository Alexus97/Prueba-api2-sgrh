package com.sgrh.sgrh.controller;

import com.sgrh.sgrh.dto.MantenimientoDTO;
import com.sgrh.sgrh.service.MantenimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<MantenimientoDTO> registrar(@Valid @RequestBody MantenimientoDTO dto) {
        MantenimientoDTO creado = mantenimientoService.registrarMantenimiento(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<MantenimientoDTO>> obtenerHistorial() {
        List<MantenimientoDTO> historial = mantenimientoService.obtenerHistorial();
        return ResponseEntity.ok(historial);
    }
}