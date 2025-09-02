package br.com.devjoaopedro.neo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devjoaopedro.neo.dto.ClienteRequestDTO;
import br.com.devjoaopedro.neo.dto.ClienteResponseDTO;
import br.com.devjoaopedro.neo.model.Cliente;
import br.com.devjoaopedro.neo.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dados) {
        var cliente = new Cliente(dados);
        repository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

}
