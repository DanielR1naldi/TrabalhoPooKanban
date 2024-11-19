package com.example.kanbann.service.security;

import com.example.kanbann.model.Usuario;
import com.example.kanbann.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class UsuarioDetalhesService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username));

        // Retorna um objeto User com as credenciais e roles (empty list, já que o código não está utilizando roles diretamente)
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.emptyList());
    }
}

