package br.com.devjoaopedro.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devjoaopedro.neo.dto.LoginRequestDTO;
import br.com.devjoaopedro.neo.dto.LoginResponseDTO;
import br.com.devjoaopedro.neo.dto.RegisterRequestDTO;
import br.com.devjoaopedro.neo.model.Cargo;
import br.com.devjoaopedro.neo.model.Usuario;
import br.com.devjoaopedro.neo.repository.UsuarioRepository;
import br.com.devjoaopedro.neo.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login) {
        var authToken = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
        var auth = authenticationManager.authenticate(authToken);

        var usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Cargo cargo;
        try {
            cargo = Cargo.valueOf(request.cargo().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Cargo inválido. Use 'USER' ou 'ADMIN'");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setCargo(cargo);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

}
