package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.ServicioDTO;
import com.sgrh.sgrh.service.ServicioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/servicios")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioDTO> crearServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicioService.crearServicio(servicioDTO));
    }

    @GetMapping("/{idServicio}")
    public ResponseEntity<ServicioDTO> obtenerServicio(@PathVariable Integer idServicio) {
        return ResponseEntity.ok(servicioService.obtenerServicioById(idServicio));
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> obtenerTodos() {
        return ResponseEntity.ok(servicioService.obtenerTodos());
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<ServicioDTO> actualizarServicio(
            @PathVariable Integer idServicio,
            @Valid @RequestBody ServicioDTO servicioDTO) {
        return ResponseEntity.ok(servicioService.actualizarServicio(idServicio, servicioDTO));
    }

    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Integer idServicio) {
        servicioService.eliminarServicio(idServicio);
        return ResponseEntity.noContent().build();
    }
}