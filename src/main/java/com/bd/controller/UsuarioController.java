package com.bd.controller;

import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UserRequest userDTO) {
        try {
            usuarioService.cadastrarUsuario(userDTO);
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody UserLoginDTO userDTO) {
        try {
            boolean authenticated = usuarioService.validarUsuario(userDTO);
            if (authenticated) {
                return ResponseEntity.ok("Login realizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar login: " + e.getMessage());
        }
    }
}
