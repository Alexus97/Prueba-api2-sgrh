package com.sgrh.sgrh.controller;

import com.sgrh.sgrh.dto.LimpiezaDTO;
import com.sgrh.sgrh.service.LimpiezaService; // Inyecta la interfaz
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/limpiezas")
@RequiredArgsConstructor
public class LimpiezaController {

    private final LimpiezaService limpiezaService; // Cambiado a la interfaz

    @PostMapping("/asignar")
    public ResponseEntity<LimpiezaDTO> asignarTarea(@Valid @RequestBody LimpiezaDTO dto) {
        LimpiezaDTO creada = limpiezaService.asignarLimpieza(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<LimpiezaDTO>> verHistorial() {
        return ResponseEntity.ok(limpiezaService.obtenerHistorialLimpieza());
    }
}