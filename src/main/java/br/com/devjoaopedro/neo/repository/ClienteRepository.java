package br.com.devjoaopedro.neo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjoaopedro.neo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    
}
