package com.sgrh.sgrh.controller;

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

import com.sgrh.sgrh.dto.SucursalHotelDTO;
import com.sgrh.sgrh.service.SucursalHotelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sucursales")
@RequiredArgsConstructor
public class SucursalHotelController {
    private final SucursalHotelService sucursalHotelService;

    @PostMapping
    public ResponseEntity<SucursalHotelDTO> crearSucursalHotel(@Valid @RequestBody SucursalHotelDTO sucursalHotelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sucursalHotelService.crearSucursalHotel(sucursalHotelDTO));
    }

    @GetMapping("/{idSucursal}")
    public ResponseEntity<SucursalHotelDTO> obtenerSucursalHotel(@PathVariable Integer idSucursal) {
        return ResponseEntity.ok(sucursalHotelService.obtenerSucursalHotelById(idSucursal));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<SucursalHotelDTO> obtenerSucursalHotelByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(sucursalHotelService.obtenerSucursalHotelByNombre(nombre));
    }

    @GetMapping
    public ResponseEntity<List<SucursalHotelDTO>> obtenerTodas() {
        return ResponseEntity.ok(sucursalHotelService.obtenerTodas());
    }

    @PutMapping("/{idSucursal}")
    public ResponseEntity<SucursalHotelDTO> actualizarSucursalHotel(
            @PathVariable Integer idSucursal,
            @Valid @RequestBody SucursalHotelDTO sucursalHotelDTO) {
        return ResponseEntity.ok(sucursalHotelService.actualizarSucursalHotel(idSucursal, sucursalHotelDTO));
    }

    @DeleteMapping("/{idSucursal}")
    public ResponseEntity<Void> eliminarSucursalHotel(@PathVariable Integer idSucursal) {
        sucursalHotelService.eliminarSucursalHotel(idSucursal);
        return ResponseEntity.noContent().build();
    }
}

