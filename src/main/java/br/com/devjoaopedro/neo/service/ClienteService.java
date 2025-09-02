package br.com.devjoaopedro.neo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<ClienteResponseDTO> listarClientes() {
        return repository.findAll().stream().map(ClienteResponseDTO::new).toList();
    }

    public Page<ClienteResponseDTO> listarClientesPaginado(Pageable pageable) {
        return repository.findAll(pageable).map(ClienteResponseDTO::new);
    }

    public Cliente buscarPorId(UUID id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(UUID id, ClienteRequestDTO dados) {
        Cliente cliente = repository.getReferenceById(id);

        cliente.setNome(dados.nome());
        cliente.setCpf(dados.cpf());
        cliente.setEmail(dados.email());
        cliente.setDataNascimento(dados.dataNascimento());
        cliente.setTelefone(dados.telefone());
        cliente.setEndereco(dados.endereco());

        repository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    public void deletarCliente(UUID id) {
        Cliente cliente = repository.getReferenceById(id);
        repository.delete(cliente);
    }

}
