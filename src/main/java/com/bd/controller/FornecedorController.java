package com.bd.controller;

import com.bd.model.Fornecedor;
import com.bd.model.request.FornecedorRegistrationRequest;
import com.bd.model.request.FornecedorRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.FornecedorResponse;
import com.bd.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping("/cadastrar")
    public FornecedorResponse cadastrarFornecedor(@RequestBody FornecedorRegistrationRequest fornecedorRequest) {
        return fornecedorService.cadastrarFornecedor(fornecedorRequest);
    }

    @GetMapping("")
    public List<FornecedorResponse> buscarFornecedores(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return fornecedorService.buscarFornecedores(new UserLoginDTO(username, password));
    }

    @GetMapping("/{id}")
    public FornecedorResponse buscarFornecedoresPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        return fornecedorService.buscarFornecedorPeloId(id, new UserLoginDTO(username, password));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponse> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorRegistrationRequest fornecedorRequest) {
        return ResponseEntity.ok(fornecedorService.atualizarFornecedor(id, fornecedorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        try {
            fornecedorService.deletarFornecedor(id, userDTO);
            return ResponseEntity.ok("fornecedor deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar fornecedor: " + e.getMessage());
        }
    }
}
