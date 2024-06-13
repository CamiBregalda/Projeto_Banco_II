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
    public List<FornecedorResponse> buscarFornecedores(@RequestBody UserLoginDTO userDTO) {
        return fornecedorService.buscarFornecedores(userDTO);
    }

    @GetMapping("/{id}")
    public FornecedorResponse buscarFornecedoresPeloId(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        return fornecedorService.buscarFornecedorPeloId(id, userDTO);
    }

    @PutMapping
    public ResponseEntity<FornecedorResponse> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorRegistrationRequest fornecedorRequest) {
        return ResponseEntity.ok(fornecedorService.atualizarFornecedor(id, fornecedorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            fornecedorService.deletarFornecedor(id, userDTO);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
