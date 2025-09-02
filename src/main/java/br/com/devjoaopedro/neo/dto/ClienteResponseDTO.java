package br.com.devjoaopedro.neo.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import br.com.devjoaopedro.neo.model.Cliente;

public record ClienteResponseDTO(
    UUID id,
    String nome,
    String cpf,
    String email,
    LocalDate dataNascimento,
    Integer idade,
    String telefone,
    String endereco
) {
     public ClienteResponseDTO(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getDataNascimento(),
            calcularIdade(cliente.getDataNascimento()),
            cliente.getTelefone(),
            cliente.getEndereco()
        );
    }

    private static Integer calcularIdade(LocalDate dataNascimento) {
        if(dataNascimento == null) return null;
        return Period.between(dataNascimento, LocalDate.now()).getYears();

    }
}
