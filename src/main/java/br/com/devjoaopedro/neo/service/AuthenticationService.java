package br.com.devjoaopedro.neo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.devjoaopedro.neo.exception.UsuarioNaoEncontradoException;
import br.com.devjoaopedro.neo.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsuarioNaoEncontradoException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário " + username + " não encontrado!"));
    }

}
