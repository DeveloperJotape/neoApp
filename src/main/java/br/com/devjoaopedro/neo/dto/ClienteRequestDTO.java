package br.com.devjoaopedro.neo.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(

    @Schema(description = "Nome completo do cliente", example = "João Pedro Nascimento")
    @NotBlank
    String nome,

    @Schema(description = "CPF do cliente. Recebe 11 dígitos", example = "78455698721")
    @NotBlank @Pattern(regexp = "\\d{11}")
    String cpf,

    @Schema(description = "Email do cliente", example = "joao@gmail.com")
    @NotBlank @Email
    String email,

    @Schema(description = "Data de nascimento no modelo americano YYYY-mm-dd", example = "2002-11-04")
    @NotNull
    LocalDate dataNascimento,

    @Schema(description = "Telefone do cliente", example = "61986530748")
    @Pattern(regexp = "\\d{11}")
    String telefone,

    @Schema(description = "Endereço do cliente", example = "Rua X casa Y")
    String endereco
) {
    
}
