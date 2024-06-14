package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Login;
import com.bd.mapper.ProdutoMapper;
import com.bd.model.Produto;
import com.bd.model.Usuario;
import com.bd.model.request.ProdutoRegistrationRequest;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.UserRequest;
import com.bd.model.response.ProdutoResponse;
import com.bd.model.response.UserResponse;
import com.bd.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;


    public ProdutoResponse cadastrarProduto(ProdutoRegistrationRequest produtoRequest) {
        criarLogin(produtoRequest.getUserLoginDTO());
        Produto produto = produtoMapper.postDtoToEntity(produtoRequest.getProdutoRequest());
        return produtoMapper.entityToResponse(produtoRepository.cadastrarProduto(produto));
    }

    public List<ProdutoResponse> buscarProdutos(UserLoginDTO userDTO) {
        try {
            List<Produto> produtos = produtoRepository.buscarProdutos();

            List<ProdutoResponse> produtoResponses = new ArrayList<>();
            for (Produto produto : produtos) {
                produtoResponses.add(produtoMapper.entityToResponse(produto));
            }
            return produtoResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public ProdutoResponse buscarProdutoPeloId(Long id, UserLoginDTO userDTO) {
        try {
            criarLogin(userDTO);
            System.out.println(id);
            Produto produto = produtoRepository.buscarProdutoPeloId(id);
            System.out.println(produto);
            return produtoMapper.entityToResponse(produto);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRegistrationRequest produtoRequest) {
        criarLogin(produtoRequest.getUserLoginDTO());
        Produto produto = produtoMapper.postDtoToEntity(produtoRequest.getProdutoRequest());
        produtoRepository.atualizarProduto(id, produto);
        produto.setPro_codigo(Math.toIntExact(id));
        return produtoMapper.entityToResponse(produto);
    }

    public boolean deletarProduto(Long id, UserLoginDTO userDTO) {
        criarLogin(userDTO);
        return produtoRepository.deletarProduto(id);

    }

    private void criarLogin(UserLoginDTO userDTO) {
        Login login = Login.getInstance();
        login.setUser(userDTO.getUsername());
        login.setSenha(userDTO.getPassword());
    }

}
