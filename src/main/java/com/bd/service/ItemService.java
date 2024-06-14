package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Login;
import com.bd.mapper.ItemMapper;
import com.bd.model.Item;
import com.bd.model.request.ItemRegistrationRequest;
import com.bd.model.request.ItemRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.response.ItemResponse;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemResponse cadastrarItem(ItemRegistrationRequest itemRequest) {
        criarLogin(itemRequest.getUserLoginDTO());
        Item item =itemMapper.postDtoToEntity(itemRequest.getItemRequest());
        System.out.println(item);
        return itemMapper.entityToResponse(itemRepository.cadastrarItem(item));
    }

    public List<ItemResponse> buscarItens(UserLoginDTO userDTO) {
        try {
            List<Item> itens = itemRepository.buscarItens();

            List<ItemResponse> itemResponses = new ArrayList<>();
            for (Item item : itens) {
                itemResponses.add(itemMapper.entityToResponse(item));
            }
            return itemResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar item: " + e.getMessage());
        }
    }

    public ItemResponse buscarItemPeloId(Long id, UserLoginDTO userDTO) {
        try {
            Item item = itemRepository.buscarItemPeloId(id);
            return itemMapper.entityToResponse(item);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar item: " + e.getMessage());
        }
    }

    public ItemResponse atualizarItem(Long id, ItemRegistrationRequest itemRequest) {
        criarLogin(itemRequest.getUserLoginDTO());
        Item item = itemMapper.postDtoToEntity(itemRequest.getItemRequest());
        itemRepository.atualizarItem(id, item);

        return itemMapper.entityToResponse(item);
    }

    public boolean deletarItem(Long id, UserLoginDTO userDTO) {
        return itemRepository.deletarItem(id);
    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }
}
