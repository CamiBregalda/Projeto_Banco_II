package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Login;
import com.bd.mapper.VendaMapper;
import com.bd.model.Venda;
import com.bd.model.request.UserLoginDTO;
import com.bd.model.request.VendaRegistrationRequest;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.VendaResponse;
import com.bd.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final VendaMapper vendaMapper;

    public VendaResponse cadastrarVenda(VendaRequest vendaRequest) {
        Venda venda = vendaMapper.postDtoToEntity(vendaRequest);
        return vendaMapper.entityToResponse(vendaRepository.cadastrarVenda(venda));
    }

    public String realizarVenda(long funcionario_codigo, long produto_codigo, int quantidade_venda) {
        try{
            return vendaRepository.realizarVenda(funcionario_codigo, produto_codigo, quantidade_venda);
        }
        catch (Exception e){
            throw new BusinessException("Erro ao realizar venda: " + e.getMessage());
        }
    }
    
    public List<VendaResponse> buscarVendas() {
        try {
            List<Venda> vendas = vendaRepository.buscarVendas();

            List<VendaResponse> vendaResponses = new ArrayList<>();
            for (Venda venda : vendas) {
                vendaResponses.add(vendaMapper.entityToResponse(venda));
            }

            return vendaResponses;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar vendas: " + e.getMessage());
        }
    }

    public VendaResponse buscarVendaPeloId(Long id) {
        try {
            Venda venda = vendaRepository.buscarVendaPeloId(id);
            return vendaMapper.entityToResponse(venda);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar venda: " + e.getMessage());
        }
    }

    public VendaResponse atualizarVenda(Long id, VendaRequest vendaRequest) {
        Venda venda = vendaMapper.postDtoToEntity(vendaRequest);
        vendaRepository.atualizarVenda(id, venda);

        return vendaMapper.entityToResponse(venda);
    }

    public boolean deletarVenda(Long id) {
        return vendaRepository.deletarVenda(id);
    }
}
