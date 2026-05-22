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
import org.springframework.web.bind.annotation.RestController;

import com.sgrh.sgrh.dto.FacturaDTO;
import com.sgrh.sgrh.service.FacturaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@Valid @RequestBody FacturaDTO facturaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(facturaService.crearFactura(facturaDTO));
    }

    @GetMapping("/{idFactura}")
    public ResponseEntity<FacturaDTO> obtenerFactura(@PathVariable Integer idFactura) {
        return ResponseEntity.ok(facturaService.obtenerFacturaById(idFactura));
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> obtenerTodas() {
        return ResponseEntity.ok(facturaService.obtenerTodas());
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasByReserva(@PathVariable Integer idReserva) {
        return ResponseEntity.ok(facturaService.obtenerFacturasByReserva(idReserva));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasByFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(facturaService.obtenerFacturasByFecha(fecha));
    }

    @PutMapping("/{idFactura}")
    public ResponseEntity<FacturaDTO> actualizarFactura(
            @PathVariable Integer idFactura,
            @Valid @RequestBody FacturaDTO facturaDTO) {
        return ResponseEntity.ok(facturaService.actualizarFactura(idFactura, facturaDTO));
    }

    @DeleteMapping("/{idFactura}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Integer idFactura) {
        facturaService.eliminarFactura(idFactura);
        return ResponseEntity.noContent().build();
    }
}
