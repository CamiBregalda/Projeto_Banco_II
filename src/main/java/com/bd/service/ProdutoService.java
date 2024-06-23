package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.mapper.ProdutoMapper;
import com.bd.model.Produto;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;


    public ProdutoResponse cadastrarProduto(ProdutoRequest produtoRequest) {
        Produto produto = produtoMapper.postDtoToEntity(produtoRequest);
        return produtoMapper.entityToResponse(produtoRepository.cadastrarProduto(produto));
    }

    public List<ProdutoResponse> buscarProdutos() {
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

    public ProdutoResponse buscarProdutoPeloId(Long id) {
        try {
            Produto produto = produtoRepository.buscarProdutoPeloId(id);
            return produtoMapper.entityToResponse(produto);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public ProdutoResponse buscarProdutoPeloNome(String descricao) {
        try {
            Produto produto = produtoRepository.buscarProdutoPeloNome(descricao);
            return produtoMapper.entityToResponse(produto);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public List<ProdutoResponse> buscarProdutosPeloNome(String descricao) {
        try {
            return produtoRepository.buscarProdutos().stream()
                    .filter(produto -> produto.getPro_descricao().contains(descricao))
                    .map(produtoMapper::entityToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar produtos: " + e.getMessage());
        }
    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest produtoRequest) {
        Produto produto = produtoMapper.postDtoToEntity(produtoRequest);
        produtoRepository.atualizarProduto(id, produto);
        produto.setPro_codigo(Math.toIntExact(id));
        return produtoMapper.entityToResponse(produto);
    }

    public boolean deletarProduto(Long id) {
        return produtoRepository.deletarProduto(id);

    }
}
