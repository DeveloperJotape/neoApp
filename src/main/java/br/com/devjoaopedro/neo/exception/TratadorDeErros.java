package br.com.devjoaopedro.neo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

    // Erro 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(EntityNotFoundException ex) {
        return respostaErro(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage());
    }

    // Erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErro400(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getFieldErrors().stream().map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();
        return respostaErro(HttpStatus.BAD_REQUEST, "Erro de validação", erros.toString());
    }

    // Erro 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErro500(Exception ex) {
        return respostaErro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", ex.getMessage());
    }

    // Erro 404
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<?> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        var erro = Map.of(
                "erro", "Usuário não encontrado",
                "detalhes", ex.getMessage(),
                "status", 404,
                "timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Erro na desserialização: " + ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwt(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Token expirado. Por favor, faça login novamente.");
    }

    private ResponseEntity<Map<String, Object>> respostaErro(HttpStatus status, String erro, Object detalhes) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("erro", erro);
        body.put("detalhes", detalhes);

        return ResponseEntity.status(status).body(body);
    }

}
