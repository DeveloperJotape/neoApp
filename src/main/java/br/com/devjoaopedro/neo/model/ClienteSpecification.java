package br.com.devjoaopedro.neo.model;

import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {
    
    public static Specification<Cliente> nomeContains(String nome) {
        return (root, query, cb) -> 
            nome == null ? null : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Cliente> cpfEquals(String cpf) {
        return (root, query, cb) ->
                cpf == null ? null : cb.equal(root.get("cpf"), cpf);
    }

    public static Specification<Cliente> emailContains(String email) {
        return (root, query, cb) ->
                email == null ? null : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

}
