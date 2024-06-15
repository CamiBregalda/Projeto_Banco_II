package com.bd.controller;

import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRegistrationRequest;
import com.bd.model.request.UserRequest;
import com.bd.model.response.UserResponse;
import com.bd.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public UserResponse cadastrarUsuario(@RequestBody UserRegistrationRequest userRequest) {
        return usuarioService.cadastrarUsuario(userRequest);
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

    @GetMapping
    public List<UserResponse> buscarUsuarios(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return usuarioService.buscarUsuarios();
    }

    @GetMapping("/{id}")
    public UserResponse buscarUsuarioPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String passwor) {
        return usuarioService.buscarUsuarioPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> atualizarUsuario(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
