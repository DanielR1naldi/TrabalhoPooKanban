package com.example.kanbann.service;

import com.example.kanbann.controller.Login;
import com.example.kanbann.controller.dto.LoginResposta;
import com.example.kanbann.model.Usuario;
import com.example.kanbann.service.security.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {


    private final AutenticacaoService autenticacaoService;
    private final UsuarioService usuarioService;

    public LoginResposta autenticar(Login dadosLogin) {
        Usuario usuario = this.usuarioService.consultarPorUsername(dadosLogin.getUsername());

        if (!usuario.getPassword().equals(dadosLogin.getPassword())) {
            throw new RuntimeException("A senha está incorreta !");
        }

        return null;
    }
}
