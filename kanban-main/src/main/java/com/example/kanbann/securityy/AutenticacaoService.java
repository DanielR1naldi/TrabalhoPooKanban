package com.example.kanbann.service.security;

import com.example.kanbann.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    private TokenService tokenService;

    @Autowired // Injeção explícita via construtor
    public AutenticacaoService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String gerarTokenParaUsuario(Usuario usuario) {
        if (usuario == null || usuario.getUsername() == null) {
            throw new IllegalArgumentException("Usuário ou username não podem ser nulos");
        }
        return tokenService.geradorToken(usuario.getUsername());
    }

    public String validarToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("O token não pode ser vazio ou nulo");
        }
        return tokenService.validaToken(token);
    }
}
