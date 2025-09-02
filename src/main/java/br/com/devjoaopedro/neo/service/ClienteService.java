package br.com.devjoaopedro.neo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.devjoaopedro.neo.dto.ClienteRequestDTO;
import br.com.devjoaopedro.neo.dto.ClienteResponseDTO;
import br.com.devjoaopedro.neo.model.Cliente;
import br.com.devjoaopedro.neo.model.ClienteSpecification;
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

    public Page<ClienteResponseDTO> listarClientes(String nome, String cpf, String email, Pageable pageable) {
        Specification<Cliente> spec = Specification
            .where(ClienteSpecification.nomeContains(nome))
            .and(ClienteSpecification.cpfEquals(cpf))
            .and(ClienteSpecification.emailContains(email));
        
        return repository.findAll(spec, pageable).map(ClienteResponseDTO::new);
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
