package com.bd.controller;

import com.bd.model.request.ItemRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRegistrationRequest;
import com.bd.model.response.ItemResponse;
import com.bd.model.response.UserResponse;
import com.bd.service.ItemService;
import com.bd.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Itens")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/cadastrar")
    public ItemResponse cadastrarItem(@RequestBody ItemRequest item) {
        return itemService.cadastrarItem(item);
    }

    @GetMapping("")
    public List<ItemResponse> buscarItens(@RequestBody UserLoginDTO userDTO) {
        return itemService.buscarItens(userDTO);
    }

    @GetMapping("/{id}")
    public ItemResponse buscarItemPeloId(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        return itemService.buscarItemPeloId(id, userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> atualizarItem(@PathVariable Long id, @RequestBody ItemRequest item, UserLoginDTO userDTO) {
        return ResponseEntity.ok(itemService.atualizarItem(id, item, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarItem(@PathVariable Long id, @RequestBody UserLoginDTO userDTO) {
        try {
           itemService.deletarItem(id, userDTO);
            return ResponseEntity.ok("Item deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar item: " + e.getMessage());
        }
    }


}
