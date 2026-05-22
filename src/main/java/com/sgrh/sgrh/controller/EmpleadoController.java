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

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.service.EmpleadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleadoService.crearEmpleado(empleadoDTO));
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable Integer idEmpleado) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoById(idEmpleado));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoByEmail(@PathVariable String email) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodos() {
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(
            @PathVariable Integer idEmpleado,
            @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(idEmpleado, empleadoDTO));
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}
