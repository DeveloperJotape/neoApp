package br.com.devjoaopedro.neo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.devjoaopedro.neo.dto.ClienteRequestDTO;
import br.com.devjoaopedro.neo.dto.ClienteResponseDTO;
import br.com.devjoaopedro.neo.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO dados, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrarCliente(dados);
        var uri = uriBuilder.path("clientes/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable UUID id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(new ClienteResponseDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteRequestDTO dados) {
        ClienteResponseDTO atualizar = clienteService.atualizarCliente(id, dados);
        return ResponseEntity.ok(atualizar);
    }

}
