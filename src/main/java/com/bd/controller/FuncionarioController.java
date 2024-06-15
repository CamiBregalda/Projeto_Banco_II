package com.bd.controller;

import com.bd.model.Funcionario;
import com.bd.model.request.FuncionarioRegistrationRequest;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.UserResponse;
import com.bd.service.FuncionarioService;
import com.bd.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public FuncionarioResponse cadastrarFuncionario(@RequestBody FuncionarioRegistrationRequest funcionarioRegistrationRequest) {
        return funcionarioService.cadastrarFuncionario(funcionarioRegistrationRequest);
    }
/*
    @PostMapping("/login")
    public ResponseEntity<String> loginFuncionario(@RequestBody UserLoginDTO userDTO) {
        try {
            boolean authenticated = funcionarioService.validarFuncionario(userDTO);
            if (authenticated) {
                return ResponseEntity.ok("Login realizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar login: " + e.getMessage());
        }
    }*/

    @GetMapping("")
    public List<FuncionarioResponse> buscarFuncionarios(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return funcionarioService.buscarFuncionarios();
    }

    @GetMapping("/{id}")
    public FuncionarioResponse buscarFuncionarioPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        return funcionarioService.buscarFuncionarioPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequest funcionarioRequest) {
        return ResponseEntity.ok(funcionarioService.atualizarFuncionario(id, funcionarioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/realizar-backup")
    public ResponseEntity<String> deletarItem() {
        try {
            funcionarioService.realizarBackup();
            return ResponseEntity.ok("Backup realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar backup: " + e.getMessage());
        }
    }
}
