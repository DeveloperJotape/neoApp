package br.com.devjoaopedro.neo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

    // Erro 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(EntityNotFoundException ex) {
        return respostaErro(HttpStatus.NOT_FOUND, "Cliente não encontrado", ex.getMessage());
    }

    // Erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();
        return respostaErro(HttpStatus.BAD_REQUEST, "Erro de validação", erros.toString());
    }

    // Erro 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErro500(Exception ex) {
        return respostaErro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> respostaErro(HttpStatus status, String erro, String detalhes) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("erro", erro);
        body.put("detalhes", detalhes);
        
        return ResponseEntity.status(status).body(body);
    }
    
}
