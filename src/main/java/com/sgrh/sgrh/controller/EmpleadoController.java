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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgrh.sgrh.dto.EmpleadoDTO;
import com.sgrh.sgrh.service.EmpleadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
@Tag(name = "Empleados", description = "Endpoints para la gestión integral de empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    // @PostMapping
    // @Operation(summary = "Crear un nuevo empleado")
    // public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
    //     return ResponseEntity.status(HttpStatus.CREATED)
    //             .body(empleadoService.crearEmpleado(empleadoDTO));
    // }

    @PostMapping
    @Operation(summary = "Crear un nuevo empleado")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
    // 1. Ejecutamos tu servicio tal cual lo tienes
    EmpleadoDTO empleadoCreado = empleadoService.crearEmpleado(empleadoDTO);
    
    // 2. Retornamos el 201 Created, agregamos el mensaje en las cabeceras y pasamos el cuerpo
    return ResponseEntity.status(HttpStatus.CREATED)
            .header("X-Status-Message", "El empleado se creo correctamente.") // <- Tu mensaje aquí
            .body(empleadoCreado);
}

    @GetMapping("/{idEmpleado}")
    @Operation(summary = "Obtener un empleado por su ID")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable Integer idEmpleado) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoById(idEmpleado));
    }

    /* * CORRECCIÓN CRÍTICA: Se cambió de @PathVariable a @RequestParam 
     * Ruta final: /api/v1/empleados/buscar?email=ejemplo@correo.com
     */
    @GetMapping("/buscar")
    @Operation(summary = "Buscar un empleado por su correo electrónico")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoByEmail(@RequestParam String email) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoByEmail(email));
    }

    @GetMapping
    @Operation(summary = "Listar todos los empleados")
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodos() {
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    @PutMapping("/{idEmpleado}")
    @Operation(summary = "Actualizar los datos de un empleado")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(
            @PathVariable Integer idEmpleado,
            @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(idEmpleado, empleadoDTO));
    }

    @DeleteMapping("/{idEmpleado}")
    @Operation(summary = "Eliminar un empleado del sistema")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}