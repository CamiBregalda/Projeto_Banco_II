package com.bd.controller;

import com.bd.model.request.ProdutoRegistrationRequest;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.response.ProdutoResponse;
import com.bd.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ProdutoResponse cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) {
        return produtoService.cadastrarProduto(produtoRequest);
    }

    @GetMapping("")
    public List<ProdutoResponse> buscarProdutos(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return produtoService.buscarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarProdutosPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        return produtoService.buscarProdutoPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProdutos(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
