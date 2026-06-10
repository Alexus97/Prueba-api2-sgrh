package com.sgrh.sgrh.controller;

import com.sgrh.sgrh.dto.ReservaHabitacionDTO;
import com.sgrh.sgrh.service.ReservaHabitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reserva-habitaciones")
@RequiredArgsConstructor
public class ReservaHabitacionController {

    private final ReservaHabitacionService reservaHabitacionService;

    @PostMapping
    public ResponseEntity<?> asignarHabitacion(@Valid @RequestBody ReservaHabitacionDTO dto) {
        try {
            ReservaHabitacionDTO resultado = reservaHabitacionService.asignarHabitacionAReserva(dto);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("title", "Validación de Disponibilidad");
            errorResponse.put("detail", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<ReservaHabitacionDTO>> obtenerPorReserva(@PathVariable Integer idReserva) {
        return ResponseEntity.ok(reservaHabitacionService.obtenerHabitacionesPorReserva(idReserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerHabitacion(@PathVariable Integer id) {
        reservaHabitacionService.removerHabitacionDeReserva(id);
        return ResponseEntity.noContent().build();
    }
}