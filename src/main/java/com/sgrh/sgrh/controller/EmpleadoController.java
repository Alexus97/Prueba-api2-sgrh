package com.sgrh.sgrh.controller;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?> registrarEmpleado(@Valid @RequestBody EmpleadoDTO dto) {
        try {
            EmpleadoDTO nuevoEmpleado = empleadoService.crearEmpleado(dto);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearEstructuraError(e.getMessage(), "/api/v1/empleados"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer id, @Valid @RequestBody EmpleadoDTO dto) {
        try {
            EmpleadoDTO editado = empleadoService.editarEmpleado(id, dto);
            return ResponseEntity.ok(editado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearEstructuraError(e.getMessage(), "/api/v1/empleados/" + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable Integer id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.ok("Confirmación: El empleado con ID " + id + " ha sido eliminado correctamente del sistema.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearEstructuraError(e.getMessage(), "/api/v1/empleados/" + id));
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodos() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    @GetMapping("/buscar")
    public ResponseEntity<EmpleadoDTO> obtenerPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(empleadoService.buscarPorEmail(email));
    }

    // Método auxiliar para construir el JSON que el frontend necesita leer (.detail)
    private ProblemDetail crearEstructuraError(String mensaje, String ruta) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, mensaje);
        pd.setTitle("Bad Request");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }
}