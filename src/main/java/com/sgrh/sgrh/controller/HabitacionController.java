package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.HabitacionDTO;
import com.sgrh.sgrh.service.HabitacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @PostMapping
    public ResponseEntity<HabitacionDTO> crearHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(habitacionService.crearHabitacion(habitacionDTO));
    }

    @GetMapping("/{idHabitacion}")
    public ResponseEntity<HabitacionDTO> obtenerHabitacion(@PathVariable Integer idHabitacion) {
        return ResponseEntity.ok(habitacionService.obtenerHabitacionById(idHabitacion));
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>> obtenerTodas() {
        return ResponseEntity.ok(habitacionService.obtenerTodas());
    }

    @PutMapping("/{idHabitacion}")
    public ResponseEntity<HabitacionDTO> actualizarHabitacion(
            @PathVariable Integer idHabitacion,
            @Valid @RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.ok(habitacionService.actualizarHabitacion(idHabitacion, habitacionDTO));
    }

    @DeleteMapping("/{idHabitacion}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Integer idHabitacion) {
        habitacionService.eliminarHabitacion(idHabitacion);
        return ResponseEntity.noContent().build();
    }
}