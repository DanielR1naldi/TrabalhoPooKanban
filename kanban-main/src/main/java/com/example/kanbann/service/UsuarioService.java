package com.example.kanbann.service;

import com.example.kanbann.model.Usuario;
import com.example.kanbann.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    // Construtor explícito
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return this.repository.save(usuario);
    }

    public Usuario consultarPorUsername(String username) {
        Optional<Usuario> usuario = this.repository.findByUsername(username);

        if (usuario.isEmpty()) {
            throw new RuntimeException("Não foi possível achar o usuário");
        }

        return usuario.get();
    }
}
