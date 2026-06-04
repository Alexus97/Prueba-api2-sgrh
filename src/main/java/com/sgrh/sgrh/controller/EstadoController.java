package com.sgrh.sgrh.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.EstadoDTO;
import com.sgrh.sgrh.service.EstadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @PostMapping
    public ResponseEntity<EstadoDTO> crearEstado(@Valid @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoService.crearEstado(estadoDTO));
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<EstadoDTO> obtenerEstado(@PathVariable Integer idEstado) {
        return ResponseEntity.ok(estadoService.obtenerEstadoById(idEstado));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<EstadoDTO> obtenerEstadoByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(estadoService.obtenerEstadoByTipo(tipo));
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> obtenerTodos() {
        return ResponseEntity.ok(estadoService.obtenerTodos());
    }

    @PutMapping("/{idEstado}")
    public ResponseEntity<EstadoDTO> actualizarEstado(
            @PathVariable Integer idEstado,
            @Valid @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.actualizarEstado(idEstado, estadoDTO));
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Integer idEstado) {
        estadoService.eliminarEstado(idEstado);
        return ResponseEntity.noContent().build();
    }
}