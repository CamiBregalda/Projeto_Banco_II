package com.bd.controller;

import com.bd.model.Venda;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.VendaResponse;
import com.bd.service.FuncionarioService;
import com.bd.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping("/cadastrar")
    public VendaResponse cadastrarVenda(@RequestBody VendaRequest venda) {
        return vendaService.cadastrarVenda(venda);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginVenda(@RequestBody UserLoginDTO userDTO) {
        try {
            boolean authenticated = vendaService.validarVenda(userDTO);
            if (authenticated) {
                return ResponseEntity.ok("Login realizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar login: " + e.getMessage());
        }
    }

    @GetMapping("")
    public List<VendaResponse> buscarVendas(@RequestBody UserLoginDTO userDTO) {
        return vendaService.buscarVendas(userDTO);
    }

    @GetMapping("/{id}")
    public VendaResponse buscarVendaPeloId(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        return vendaService.buscarVendaPeloId(id, userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable Long id, @RequestBody VendaRequest venda, UserLoginDTO userDTO) {
        return ResponseEntity.ok(vendaService.atualizarVenda(id, venda, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVenda(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            vendaService.deletarVenda(id, userDTO);
            return ResponseEntity.ok("Venda deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar venda: " + e.getMessage());
        }
    }

}
