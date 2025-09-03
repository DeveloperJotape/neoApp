package br.com.devjoaopedro.neo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteUpdateDTO(
        @NotBlank(message = "O nome não pode ser vazio") String nome,

        @NotBlank(message = "O CPF é obrigatório") @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos") String cpf,

        @Email(message = "O e-mail deve ser válido") String email,

        LocalDate dataNascimento,

        String telefone,

        String endereco) {

}
