package br.com.devjoaopedro.neo.model;

import java.time.LocalDate;
import java.util.UUID;

import br.com.devjoaopedro.neo.dto.ClienteRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;

    public Cliente(ClienteRequestDTO cliente) {
        this.nome = cliente.nome();
        this.cpf = cliente.cpf();
        this.email = cliente.email();
        this.dataNascimento = cliente.dataNascimento();
        this.telefone = cliente.telefone();
        this.endereco = cliente.endereco();
    }

}
