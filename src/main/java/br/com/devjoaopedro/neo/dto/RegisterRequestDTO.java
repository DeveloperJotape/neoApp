package br.com.devjoaopedro.neo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
    @NotBlank String nome,
    @Email String email,
    @NotBlank String senha,
    @NotBlank String cargo
) {
    
}
