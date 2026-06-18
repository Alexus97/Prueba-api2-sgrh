package com.sgrh.sgrh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgrh.sgrh.dto.TipoHabitacionDTO;
import com.sgrh.sgrh.service.TipoHabitacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tipos-habitacion")
@RequiredArgsConstructor
public class TipoHabitacionController {

    private final TipoHabitacionService tipoHabitacionService;

    @PostMapping
    public ResponseEntity<TipoHabitacionDTO> crearTipoHabitacion(@Valid @RequestBody TipoHabitacionDTO tipoHabitacionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipoHabitacionService.crearTipoHabitacion(tipoHabitacionDTO));
    }

    @GetMapping("/{idTipo}")
    public ResponseEntity<TipoHabitacionDTO> obtenerTipoHabitacion(@PathVariable Integer idTipo) {
        return ResponseEntity.ok(tipoHabitacionService.obtenerTipoHabitacionById(idTipo));
    }

    @GetMapping
    public ResponseEntity<List<TipoHabitacionDTO>> obtenerTodos() {
        return ResponseEntity.ok(tipoHabitacionService.obtenerTodos());
    }

    @PutMapping("/{idTipo}")
    public ResponseEntity<TipoHabitacionDTO> actualizarTipoHabitacion(
            @PathVariable Integer idTipo,
            @Valid @RequestBody TipoHabitacionDTO tipoHabitacionDTO) {
        return ResponseEntity.ok(tipoHabitacionService.actualizarTipoHabitacion(idTipo, tipoHabitacionDTO));
    }

    @DeleteMapping("/{idTipo}")
    public ResponseEntity<Void> eliminarTipoHabitacion(@PathVariable Integer idTipo) {
        tipoHabitacionService.eliminarTipoHabitacion(idTipo);
        return ResponseEntity.noContent().build();
    }
}