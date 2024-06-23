package com.bd.controller;

import com.bd.model.Venda;
import com.bd.model.request.FuncionarioRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.VendaRegistrationRequest;
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
    public VendaResponse cadastrarVenda(@RequestBody VendaRequest vendaRequest) {
        return vendaService.cadastrarVenda(vendaRequest);
    }

    @GetMapping("")
    public List<VendaResponse> buscarVendas(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return vendaService.buscarVendas();
    }

    @GetMapping("/{id}")
    public VendaResponse buscarVendaPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        return vendaService.buscarVendaPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable Long id, @RequestBody VendaRequest vendaRequest) {
        return ResponseEntity.ok(vendaService.atualizarVenda(id, vendaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVenda(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
            vendaService.deletarVenda(id);
            return ResponseEntity.ok("Venda deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar venda: " + e.getMessage());
        }
    }

}
