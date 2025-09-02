package br.com.devjoaopedro.neo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(
    @NotBlank
    String nome,
    @NotBlank
    String cpf,
    @NotBlank @Email
    String email,
    @NotNull
    LocalDate dataNascimento,
    String telefone,
    String endereco
) {
    
}
