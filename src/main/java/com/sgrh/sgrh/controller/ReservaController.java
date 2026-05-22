package com.sgrh.sgrh.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgrh.sgrh.dto.ReservaDTO;
import com.sgrh.sgrh.service.ReservaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.crearReserva(reservaDTO));
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<ReservaDTO> obtenerReserva(@PathVariable Integer idReserva) {
        return ResponseEntity.ok(reservaService.obtenerReservaById(idReserva));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerTodas() {
        return ResponseEntity.ok(reservaService.obtenerTodas());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasByCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(reservaService.obtenerReservasByCliente(idCliente));
    }

    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasByEstado(@PathVariable Integer idEstado) {
        return ResponseEntity.ok(reservaService.obtenerReservasByEstado(idEstado));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasByFechas(
            @RequestParam LocalDate fechaEntrada,
            @RequestParam LocalDate fechaSalida) {
        return ResponseEntity.ok(reservaService.obtenerReservasByFechas(fechaEntrada, fechaSalida));
    }

    @PutMapping("/{idReserva}")
    public ResponseEntity<ReservaDTO> actualizarReserva(
            @PathVariable Integer idReserva,
            @Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.actualizarReserva(idReserva, reservaDTO));
    }

    @DeleteMapping("/{idReserva}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Integer idReserva) {
        reservaService.eliminarReserva(idReserva);
        return ResponseEntity.noContent().build();
    }
}
