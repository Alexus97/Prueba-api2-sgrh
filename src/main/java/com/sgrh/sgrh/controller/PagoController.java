package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.PagoDTO;
import com.sgrh.sgrh.service.PagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoDTO> registrarPago(@Valid @RequestBody PagoDTO pagoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoService.registrarPago(pagoDTO));
    }

    @GetMapping("/{idPago}")
    public ResponseEntity<PagoDTO> obtenerPago(@PathVariable Integer idPago) {
        return ResponseEntity.ok(pagoService.obtenerPagoById(idPago));
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> obtenerTodos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @PutMapping("/{idPago}")
    public ResponseEntity<PagoDTO> actualizarPago(
            @PathVariable Integer idPago,
            @Valid @RequestBody PagoDTO pagoDTO) {
        return ResponseEntity.ok(pagoService.actualizarPago(idPago, pagoDTO));
    }

    @DeleteMapping("/{idPago}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer idPago) {
        pagoService.eliminarPago(idPago);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorReserva(@PathVariable Integer idReserva) {
        return ResponseEntity.ok(pagoService.obtenerPagosPorReserva(idReserva));
    }
}