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

import com.sgrh.sgrh.dto.ClienteDTO;
import com.sgrh.sgrh.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.crearCliente(clienteDTO));
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(clienteService.obtenerClienteById(idCliente));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> obtenerClienteByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clienteService.obtenerClienteByEmail(email));
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<ClienteDTO> obtenerClienteByDocumento(@PathVariable String numeroDocumento) {
        return ResponseEntity.ok(clienteService.obtenerClienteByNumeroDocumento(numeroDocumento));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosClientes());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Integer idCliente,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.actualizarCliente(idCliente, clienteDTO));
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer idCliente) {
        clienteService.eliminarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}
