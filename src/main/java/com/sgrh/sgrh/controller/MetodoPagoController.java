package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.MetodoPagoDTO;
import com.sgrh.sgrh.service.MetodoPagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> crear(@Valid @RequestBody MetodoPagoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(metodoPagoService.crearMetodoPago(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(metodoPagoService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> obtenerTodos() {
        return ResponseEntity.ok(metodoPagoService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody MetodoPagoDTO dto) {
        return ResponseEntity.ok(metodoPagoService.actualizarMetodoPago(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}