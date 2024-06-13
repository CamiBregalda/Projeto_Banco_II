package com.bd.controller;

import com.bd.model.Produto;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.ProdutoResponse;
import com.bd.model.response.UserResponse;
import com.bd.service.ProdutoService;
import com.bd.service.UsuarioService;
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
    public ProdutoResponse cadastrarProduto(@RequestBody ProdutoRequest produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @GetMapping("")
    public List<ProdutoResponse> buscarProdutos(@RequestBody UserLoginDTO userDTO) {
        return produtoService.buscarProdutos(userDTO);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarProdutosPeloId(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        return produtoService.buscarProdutoPeloId(id, userDTO);
    }

    @PutMapping("/id")
    public ResponseEntity<ProdutoResponse> atualizarProdutos(@PathVariable Long id, @RequestBody ProdutoRequest produto, UserLoginDTO userDTO) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produto, userDTO));
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            produtoService.deletarProduto(id, userDTO);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
