package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.ConsumoServicioDTO;
import com.sgrh.sgrh.service.ConsumoServicioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/consumos")
@RequiredArgsConstructor
public class ConsumoServicioController {

    private final ConsumoServicioService consumoServicioService;

    // 1. Registrar un nuevo consumo (cargo extra)
    @PostMapping
    public ResponseEntity<ConsumoServicioDTO> registrarConsumo(@Valid @RequestBody ConsumoServicioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consumoServicioService.registrarConsumo(dto));
    }

    // 2. Obtener un consumo específico por su ID
    @GetMapping("/{idConsumo}")
    public ResponseEntity<ConsumoServicioDTO> obtenerPorId(@PathVariable Integer idConsumo) {
        return ResponseEntity.ok(consumoServicioService.obtenerPorId(idConsumo));
    }

    // 3. Obtener la lista de todos los consumos registrados en el hotel
    @GetMapping
    public ResponseEntity<List<ConsumoServicioDTO>> obtenerTodos() {
        return ResponseEntity.ok(consumoServicioService.obtenerTodos());
    }

    // 4. Actualizar los datos de un consumo existente (ej. corregir la cantidad)
    @PutMapping("/{idConsumo}")
    public ResponseEntity<ConsumoServicioDTO> actualizarConsumo(
            @PathVariable Integer idConsumo,
            @Valid @RequestBody ConsumoServicioDTO dto) {
        return ResponseEntity.ok(consumoServicioService.actualizarConsumo(idConsumo, dto));
    }

    // 5. Eliminar un consumo (en caso de error del recepcionista)
    @DeleteMapping("/{idConsumo}")
    public ResponseEntity<Void> eliminarConsumo(@PathVariable Integer idConsumo) {
        consumoServicioService.eliminarConsumo(idConsumo);
        return ResponseEntity.noContent().build();
    }

    // 6. Filtrar todos los consumos extras de una sola reserva (Ideal para la cuenta final)
    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<ConsumoServicioDTO>> obtenerConsumosPorReserva(@PathVariable Integer idReserva) {
        return ResponseEntity.ok(consumoServicioService.obtenerConsumosPorReserva(idReserva));
    }
}