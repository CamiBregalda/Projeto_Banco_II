package com.bd.controller;

import com.bd.model.request.ItemRegistrationRequest;
import com.bd.model.request.ItemRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.response.ItemResponse;
import com.bd.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/cadastrar")
    public ItemResponse cadastrarItem(@RequestBody ItemRegistrationRequest itemRequest) {
        return itemService.cadastrarItem(itemRequest);
    }

    @GetMapping("")
    public List<ItemResponse> buscarItens(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return itemService.buscarItens();
    }

    @GetMapping("/{id}")
    public ItemResponse buscarItemPeloId(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        return itemService.buscarItemPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> atualizarItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
        return ResponseEntity.ok(itemService.atualizarItem(id, itemRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarItem(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
           itemService.deletarItem(id);
            return ResponseEntity.ok("Item deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar item: " + e.getMessage());
        }
    }


}
