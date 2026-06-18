package com.sgrh.sgrh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgrh.sgrh.dto.ClienteDTO;
import com.sgrh.sgrh.exception.ResourceNotFoundException;
import com.sgrh.sgrh.mapper.ClienteMapper;
import com.sgrh.sgrh.repository.ClienteRepository;
import com.sgrh.sgrh.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        var cliente = clienteMapper.toEntity(clienteDTO);
        var clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClienteById(Integer idCliente) {
        var cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClienteByEmail(String email) {
        var cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClienteByNumeroDocumento(String numeroDocumento) {
        var cliente = clienteRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO) {
        var cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        var clienteActualizado = clienteMapper.toEntity(clienteDTO);
        clienteActualizado.setIdCliente(idCliente);
        var clienteGuardado = clienteRepository.save(clienteActualizado);
        return clienteMapper.toDTO(clienteGuardado);
    }

    @Override
    public void eliminarCliente(Integer idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        clienteRepository.deleteById(idCliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .toList();
    }
}
